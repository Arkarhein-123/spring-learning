package com.solt.pattern.singleton;

public class Main {
	public static void main(String[] args) {
		MyClass ob1 = MyClass.getInstance();
		MyClass ob2 = MyClass.getInstance();
		
		System.out.println(ob1);
		System.out.println(ob2);
		 String isSame = ob1 == ob2 ? "Same Same" : "but different";
		 
		 System.out.println("Are ob1 and ob2 the same? : " + isSame);
	}
}
