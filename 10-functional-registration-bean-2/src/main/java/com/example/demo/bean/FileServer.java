package com.example.demo.bean;

public class FileServer implements Server{
	@Override
	public void start() {
		System.out.println("File Server");
	}
}
