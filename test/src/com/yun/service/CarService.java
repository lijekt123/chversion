package com.yun.service;

import java.util.List;

import com.yun.dto.Car;

public interface CarService extends GenericService<Car>{
	public int getCount();
	public int getCountBySpot(String spot);
	public List<Car> getAllBySpot(String spot);
	public int checkCarNumber(String carNumber);
}
