package com.solt.pattern.prototype;


public interface IPrototype extends Cloneable{
	
	Object clone() throws CloneNotSupportedException;
}
