package com.yun.dao;

import java.util.List;

public interface GenericDao<T> {
	public int add(T element);
	
	public int delete(String element);
	
	public int deleteAll();
	
	public T get(String element);
	
	public List<T> getAll();

	int update(T element);
}
