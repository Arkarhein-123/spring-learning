package com.solt.pattern.prototype;

public class Main {
	public static void main(String[] args) {
		MyProto original = new MyProto();
		original.setName("Min Phay Phay");
		
		
		MyProto clone = (MyProto) original.clone();
		System.out.println("Original : "+ original.getName());
		System.out.println("Clone: "+ clone.getName());
		
		System.out.println("--------------------------------------");
		
		clone.setName("Min May May");
		System.out.println("Original : "+original.getName());
		System.out.println("After Changing clone : "+clone.getName());
	}
}
