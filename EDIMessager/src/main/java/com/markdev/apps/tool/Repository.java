package com.markdev.apps.tool;

public interface Repository<E,T> {

	public T get(E key);
	
}