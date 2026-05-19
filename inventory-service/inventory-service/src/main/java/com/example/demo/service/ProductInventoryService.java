package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.RabbitMqConfig;
import com.example.demo.dao.ProductInventoryAuditDao;
import com.example.demo.dao.ProductInventoryDao;
import com.example.demo.entity.InventoryStatus;
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
	
	public List<ProductInventory> listAllProductInventory(){
		return productInventoryDao.findAll();
	}
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public record InventoryRequestBody(
			String username,
			String accountNumber,
			double totalAmount,
			String orderCode,
			Map<String,Integer> products) {}
	
	public record InventoryFailRequest(String orderId) {}
	
	
	@RabbitListener(queues = "order.queue") @Transactional
	public void handleRabbitMQInventoryRequestFromOrder(InventoryRequestBody
			request) {
		System.out.println("Inventory Debut Rabbit Listener::"+
			request);
		Map<String,Integer> products=request.products();
		String orderCode = request.orderCode;
		
		for(String key:products.keySet()) {
			ProductInventory productInventory=productInventoryDao
					.findByName(key)
					.orElseThrow();
			try {
				if(productInventory.getQuantity() < products.get(key)) {
					System.out.println("OutOfStock!"); //Rabbit -OrderCancel
					throw new RuntimeException();
				}else {
					productInventory.setQuantity(productInventory.getQuantity() 
							- products.get(key));
					ProductInventoryAudit audit=
							new ProductInventoryAudit(request.username,
									products.get(key),InventoryStatus.DEBUT);
					audit.setProductInventory(productInventory);
					productInventoryAuditDao.save(audit);
					var paymentRequest=new PaymentRequest(request.username,
							request.accountNumber, request.totalAmount, request.orderCode);
					rabbitTemplate.convertAndSend(
							RabbitMqConfig.EXCHANGE,
							RabbitMqConfig.ROUTING_KEY_INVENTORY_SUCCESS,
							paymentRequest);
				}
			}catch(Exception e) {
				var ob = new InventoryFailRequest(orderCode);
				rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,"inventory.outofstock",ob);
			}
	
			System.out.println("%s items: %s."
					.formatted(key,products.get(key)));
		}
	}
	
	public record PaymentRequest(
			String fromUser,
			String fromAccountNumber,
			double amount,
			String orderCode
			) {}
	
	
	// debutQuentity(long productId,int debutQuentity,String username)
	public String debutQuentity(
			long productId,
			int debutQuentity,
			String username) {
		ProductInventory beforeDebutProduct
			=findProductInventoryById(productId);
		if(beforeDebutProduct.getQuantity() < debutQuentity) {
			throw new OutOfStockException();
		}
		beforeDebutProduct
			.setQuantity(beforeDebutProduct.getQuantity()-debutQuentity);
		beforeDebutProduct=productInventoryDao
				.saveAndFlush(beforeDebutProduct);
		ProductInventoryAudit productInventoryAudit=
				new ProductInventoryAudit(username, debutQuentity, InventoryStatus.DEBUT);
		productInventoryAudit.setProductInventory(beforeDebutProduct);
		productInventoryAuditDao.save(productInventoryAudit);
		return "success debut produt";
	}
	
	private ProductInventory findProductInventoryById(long id) {
		return productInventoryDao.findById(id)
				.orElseThrow(ProductNotFoundException::new);
	}
	
	

}
