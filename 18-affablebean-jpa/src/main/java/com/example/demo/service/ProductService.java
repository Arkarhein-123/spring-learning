package com.example.demo.service;



import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.Category;
import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerOrder;
import com.example.demo.entity.CustomerOrderProduct;
import com.example.demo.entity.Product;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	private CategoryDao categoryDao;


	
	@Transactional
	public void createDb() {
		Category category1 = new Category();
		category1.setName("Fruit");
		Category category2 = new Category();
		category2.setName("Meat");
		
		
		Product product1 = new Product("Apple",6000,"Fresh Red Apple",LocalDate.now());
		Product product2 = new Product("Orange",5000,"Juicy Orange",LocalDate.now());
		Product product3 = new Product("Chicken",10000,"Fresh Meat",LocalDate.now());
		Product product4 = new Product("Fish Grill",25000,"Five Star Fish grill",LocalDate.now());
		
		Customer customer1 = new Customer("Arkar Hein","Arkar@gmail.com","099123423","Mandalay");
		Customer customer2 = new Customer("Mary","mary@gmail.com","09232342","Yangon");
		
		category1.addProduct(product1);
		category1.addProduct(product2);
		category2.addProduct(product3);
		category2.addProduct(product4);
		
		CustomerOrder customerOrder1 = new CustomerOrder(2000,LocalDate.now(),123);
		customer1.addCustomerorder(customerOrder1);
		CustomerOrderProduct orderProduct = new CustomerOrderProduct();
		customerOrder1.addCustomerOrderProduct(orderProduct);
		product1.addCustomerOrderProduct(orderProduct);
		
		CustomerOrder customerOrder2 = new CustomerOrder(1000,LocalDate.now(),124);
		customer1.addCustomerorder(customerOrder2);
		CustomerOrderProduct orderProduct2 = new CustomerOrderProduct();
		customerOrder2.addCustomerOrderProduct(orderProduct2);
		product2.addCustomerOrderProduct(orderProduct2);
		
		
		CustomerOrder customerOrder3 = new CustomerOrder(2000,LocalDate.now(),125);
		customer1.addCustomerorder(customerOrder3);
		CustomerOrderProduct orderProduct3 = new CustomerOrderProduct();
		customerOrder3.addCustomerOrderProduct(orderProduct3);
		product3.addCustomerOrderProduct(orderProduct3);
		
		CustomerOrder customerOrder4 = new CustomerOrder(1500,LocalDate.now(),126);
		customer2.addCustomerorder(customerOrder4);
		CustomerOrderProduct orderProduct4 = new CustomerOrderProduct();
		customerOrder4.addCustomerOrderProduct(orderProduct4);
		product4.addCustomerOrderProduct(orderProduct4);
		
		categoryDao.save(category1);
		categoryDao.save(category2);
	}
	
}
