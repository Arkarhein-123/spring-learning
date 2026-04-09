package com.example.demo.bean;

public class DbServer implements Server {
	@Override
	public void start() {
		System.out.println("Db Server...");
	}
}
