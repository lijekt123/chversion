package com.yun.service;

import java.util.List;

import com.yun.dao.RentDao;
import com.yun.dto.Car;
import com.yun.dto.Rent;
import com.yun.factory.Factory;

public class RentServiceImpl implements RentService{
	Factory factory = new Factory();
	RentDao dao;
	
	public RentServiceImpl(RentDao dao) {
		this.dao = dao;
	}
	
	@Override
	public int add(Rent rent, String id, String carNumber) {
		
		return dao.add(rent, id, carNumber);
	}

	@Override
	public int delete(int rentNumber) {

		return dao.delete(rentNumber);
	}

	@Override
	public int deleteAll() {

		return dao.deleteAll();
	}

	@Override
	public Rent get(String id, int rentNumber) {

		return dao.get(id, rentNumber);
	}

	@Override
	public List<Rent> getAll() {

		return dao.getAll();
	}

	@Override
	public int update(Rent rent, String carNumber) {

		return dao.update(rent, carNumber);
	}

	@Override
	public int getTodayCount() {

		return dao.getTodayCount();
	}

	@Override
	public List<Rent> getToday() {

		return dao.getToday();
	}

	@Override
	public List<Car> checkCar(String startDate, String endDate, String rentSpot, String returnSpot) {

		return dao.checkCar(startDate, endDate, rentSpot, returnSpot);
	}
}
