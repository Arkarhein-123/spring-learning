package com.solt.pattern.prototype;

public class MyProto implements IPrototype{
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
