package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<ProductInventory> listAllProducts(){
		return productInventoryDao.findAll();
	}
	
	
// debutQuantity(long productId, int quantity, String username)
	@Transactional
	public String debutQuantity(long productId, int debutQuantity, String username) {
		ProductInventory beforeDebutProduct = findProductInventoryById(productId);
		if(beforeDebutProduct.getQuantity() < debutQuantity) {
			throw new OutOfStockException();
		}
		beforeDebutProduct
			.setQuantity(beforeDebutProduct.getQuantity() - debutQuantity);
		beforeDebutProduct = 
				productInventoryDao.saveAndFlush(beforeDebutProduct);
		ProductInventoryAudit productInventoryAudit = 
				new ProductInventoryAudit(username, debutQuantity,InventoryStatus.DEBUT);
		productInventoryAudit.setProductInventory(beforeDebutProduct); 
		productInventoryAuditDao.save(productInventoryAudit);
		return "success debut product";
	}
	
	private ProductInventory findProductInventoryById(Long id) {
		return productInventoryDao.findById(id).orElseThrow(ProductNotFoundException::new);
	}
	
}
