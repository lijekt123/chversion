package com.yun.service;

import java.util.List;

import com.yun.dto.Car;
import com.yun.dto.Rent;

public interface RentService {
	public int add(Rent rent, String id, String carNumber);
	
	public int delete(int rentNumber);
	
	public int deleteAll();
	
	public Rent get(String id, int rentNumber);
	
	public List<Rent> getAll();

	int update(Rent rent, String carNumber);
	
	public int getTodayCount();
	
	public List<Rent> getToday();
	
	public List<Car>checkCar(String startDate, String endDate, String rentSpot, String returnSpot);
}
