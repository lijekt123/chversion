package com.yun.service;

import java.util.List;

public interface GenericService<T> {
	
	public int add(T element);
	
	public int delete(String element);
		
	public T get(String element);
	
	public List<T> getAll();

	public int update(T element);
	
	public int deleteAll();
}
