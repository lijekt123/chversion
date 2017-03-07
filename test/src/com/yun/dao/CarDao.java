package com.yun.dao;

import java.util.List;

import com.yun.dto.Car;

public interface CarDao extends GenericDao<Car>{
	public int getCount();
	public int getCountBySpot(String spot);
	public List<Car> getAllBySpot(String spot);
	public int checkCarNumber(String carNumber);
}
