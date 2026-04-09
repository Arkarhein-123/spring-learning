package com.solt.pattern.singleton;


public class MyClass {
	private static MyClass myClass;
	private MyClass() {
		Object o;
	}
	
	public static MyClass getInstance() {
		if(myClass == null) {
			myClass = new MyClass();
		}
		return myClass;
	}
}

