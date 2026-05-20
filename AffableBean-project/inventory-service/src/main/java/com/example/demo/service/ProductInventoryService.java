package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.RabbitMqConfig;
import com.example.demo.dao.InventoryCompansateDao;
import com.example.demo.dao.ProductInventoryAuditDao;
import com.example.demo.dao.ProductInventoryDao;
import com.example.demo.entity.InventoryCompansate;
import com.example.demo.entity.InventoryStatus;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductInventory;
import com.example.demo.entity.ProductInventoryAudit;
import com.example.demo.exception.OutOfStockException;
import com.example.demo.exception.ProductNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ProductInventoryService {
	@Autowired
	private ProductInventoryAuditDao productInventoryAuditDao;
	@Autowired
	private ProductInventoryDao productInventoryDao;
	@Autowired
	private InventoryCompansateDao inventoryCompansateDao;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public List<ProductInventory> listAllProductInventory() {
		return productInventoryDao.findAll();
	}

	public record InventoryRequestBody(String username, String accountNumber, double totalAmount, String orderCode,
			Map<String, Integer> products) {}

	public record InventoryFailRequest(String orderId, String message) {}
	
	public record PaymentRequest(String fromUser, String fromAccountNumber, double amount, String orderCode) {}
	
	public record PaymentFailDto(String orderId, String message) {}

	@RabbitListener(queues = "order.queue")
	@Transactional // ဒီ Transaction က လုပ်ငန်းစဉ်တစ်ခုလုံး အောင်မြင်မှ သိမ်းဖို့ဖြစ်ပါတယ်
	public void handleRabbitMQInventoryRequestFromOrder(InventoryRequestBody request) {
		System.out.println("Inventory Debut Rabbit Listener::" + request);
		
		// RabbitMQ မက်ဆေ့ခ်ျ ထပ်ခါထပ်ခါ ဝင်လာခြင်း (Duplicate) ကို ကာကွယ်ဖို့ အရင်ဆုံး စစ်ဆေးမယ်
		if (inventoryCompansateDao.existsByOrderId(request.orderCode)) {
			System.out.println("Order " + request.orderCode + " က အလုပ်လုပ်ပြီးသားဖြစ်လို့ ထပ်မလုပ်တော့ပါ။");
			return; 
		}

		Map<String, Integer> products = request.products();
		String orderCode = request.orderCode;
		List<Product> productList = new ArrayList<>();
		
		boolean isAllStockAvailable = true;
		String errorMessage = "";

		// အဆင့် (၁) - ပစ္စည်းအားလုံး တကယ်ရှိမရှိ အရင်ပတ်စစ်မယ် (Database ထဲမှာ ချက်ချင်းမနှုတ်သေးဘူး)
		for (String key : products.keySet()) {
			ProductInventory productInventory = productInventoryDao.findByName(key)
					.orElse(null);
			
			if (productInventory == null) {
				isAllStockAvailable = false;
				errorMessage = "Product not found: " + key;
				break;
			}
			
			int requestedQuantity = products.get(key);
			if (productInventory.getQuantity() < requestedQuantity) {
				isAllStockAvailable = false;
				errorMessage = "OutOfStock for product: " + key;
				break; // ပစ္စည်းတစ်ခုခု လိုတာနဲ့ loop ကို ရပ်မယ်
			}
			
			// ပစ္စည်းရှိရင် ယာယီစာရင်းထဲ ထည့်ထားမယ်
			Product product = new Product(productInventory.getProductId(), key, requestedQuantity);
			productList.add(product);
		}

		// အဆင့် (၂) - ပစ္စည်းအားလုံး အဆင်ပြေမှ တကယ် နှုတ်ပြီး ဝင်ငွေတောင်းမယ်
		if (isAllStockAvailable) {
			for (Product prod : productList) {
				ProductInventory productInventory = productInventoryDao.findByName(prod.name()).get();
				productInventory.setQuantity(productInventory.getQuantity() - prod.quantity());
				
				ProductInventoryAudit audit = new ProductInventoryAudit(request.username, prod.quantity(), InventoryStatus.DEBUT);
				audit.setProductInventory(productInventory);
				productInventoryAuditDao.save(audit);
			}

			// Compensation data ကို "Pending" အနေနဲ့ သိမ်းမယ်
			InventoryCompansate inventoryCompansate = new InventoryCompansate();
			inventoryCompansate.setOrderId(orderCode);
			inventoryCompansate.setProducts(productList);
			inventoryCompansate.setStatus("Pending");
			inventoryCompansateDao.save(inventoryCompansate);
			
			// Payment Request ပို့မယ်
			var paymentRequest = new PaymentRequest(request.username, request.accountNumber, request.totalAmount, request.orderCode);
			rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY_INVENTORY_SUCCESS, paymentRequest);
			System.out.println("Inventory Success. Payment Request Sent.");
			
		} else {
			// အဆင့် (၃) - တကယ်လို့ Stock မလောက်ရင် Error မပစ်တော့ဘဲ တရားဝင် Fail ဖြစ်ကြောင်း သတင်းပို့မယ်
			System.out.println("Inventory Process Failed: " + errorMessage);
			
			var ob = new InventoryFailRequest(orderCode, errorMessage);
			rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, "inventory.outofstock", ob);
			
			// တမင်တကာ `throw e` မလုပ်တော့ပါဘူး။ ဒါမှ Message က Queue ထဲကနေ ထွက်သွားမှာပါ။
		}
	}

	@RabbitListener(queues = "payment.fail.queue") 
	@Transactional
	public void inventoryCompansateFromPaymentFail(PaymentFailDto paymentFailDto) {
		System.out.println("Inventory Compensation Started for Order: " + paymentFailDto.orderId);
		
		Optional<InventoryCompansate> compansateOptional = inventoryCompansateDao.findByOrderId(paymentFailDto.orderId);
		
		if (compansateOptional.isPresent()) {
			InventoryCompansate inventoryCompansate = compansateOptional.get();
			
			// 'Compansate' ဖြစ်ပြီးသားဆိုရင် ထပ်မလုပ်ဖို့ သေချာစစ်မယ်
			if ("Compansate".equals(inventoryCompansate.getStatus())) {
				System.out.println("Order " + paymentFailDto.orderId + " က Compensation လုပ်ပြီးသားဖြစ်ပါတယ်။ စာရင်းထပ်မပေါင်းပါ။");
				return;
			}
			
			List<Product> compansateList = inventoryCompansate.getProducts();
			for (Product product : compansateList) {
				ProductInventory productInventory = productInventoryDao.findByName(product.name()).get();
				
				int currentQuantity = productInventory.getQuantity();		
				int updatedQuantity = currentQuantity + product.quantity(); // နှုတ်သွားတဲ့ စာရင်းကို ပြန်ပေါင်းထည့်ခြင်း
				
				productInventory.setQuantity(updatedQuantity);
				
				ProductInventoryAudit productInventoryAudit = new ProductInventoryAudit();
				productInventoryAudit.setDebutQuantity(product.quantity());
				productInventoryAudit.setInventoryStatus(InventoryStatus.COMPANSATE);
				productInventoryAudit.setProductInventory(productInventory);
				productInventoryAuditDao.save(productInventoryAudit);
			}
			
			// status ကို 'Compansate' လို့ ပြောင်းပြီး Database ထဲ သိမ်းမယ်
			inventoryCompansate.setStatus("Compansate");
			inventoryCompansateDao.save(inventoryCompansate);
			System.out.println("Inventory Compensation Success for Order: " + paymentFailDto.orderId);
			//
			var comDto=new InventoryCompansationDto(
					paymentFailDto.orderId,
					paymentFailDto.message);
			rabbitTemplate.convertAndSend(RabbitMqConfig
					.EXCHANGE,
					RabbitMqConfig.INVENTORY_COMPANSATION_BINDING_KEY,
					comDto);
			
		} else {
			System.out.println("No compensation record found for Order: " + paymentFailDto.orderId);
		}
	}
	public record InventoryCompansationDto(String orderId,String messsage) {};

	public String debutQuentity(long productId, int debutQuentity, String username) {
		ProductInventory beforeDebutProduct = findProductInventoryById(productId);
		if (beforeDebutProduct.getQuantity() < debutQuentity) {
			throw new OutOfStockException();
		}
		beforeDebutProduct.setQuantity(beforeDebutProduct.getQuantity() - debutQuentity);
		beforeDebutProduct = productInventoryDao.saveAndFlush(beforeDebutProduct);
		ProductInventoryAudit productInventoryAudit = new ProductInventoryAudit(username, debutQuentity, InventoryStatus.DEBUT);
		productInventoryAudit.setProductInventory(beforeDebutProduct);
		productInventoryAuditDao.save(productInventoryAudit);
		return "success debut produt";
	}

	private ProductInventory findProductInventoryById(long id) {
		return productInventoryDao.findById(id).orElseThrow(ProductNotFoundException::new);
	}
}