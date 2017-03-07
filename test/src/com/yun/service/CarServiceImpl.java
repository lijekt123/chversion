package com.yun.service;

import java.util.List;

import com.yun.dao.CarDao;
import com.yun.dto.Car;

public class CarServiceImpl implements CarService {
	private CarDao dao;
	
	public CarServiceImpl(CarDao dao) {
		this.dao = dao;
	}
	
	@Override
	public int add(Car car) {
		int x = -1;
		
		if(dao.checkCarNumber(car.getCarNumber()) == 0){
			x = dao.add(car);
		}
		
		return x;
	}

	@Override
	public int delete(String carNumber) {
		int x = -1;
		
		if(dao.checkCarNumber(carNumber) > 0){
			x = dao.delete(carNumber);
		}
		
		return x;
	}

	@Override
	public int deleteAll() {
		int x = -1;
		
		if(dao.getCount() > 0){
			x = dao.deleteAll();
		}
		
		return x;
	}

	@Override
	public Car get(String carNumber) {
		
		return dao.get(carNumber);
	}

	@Override
	public List<Car> getAll() {
		List<Car> list = null;
		
		if(dao.getCount() > 0){
			list = dao.getAll();
		}
		
		return list;
	}
	
	@Override
	public int update(Car car) {
		int x = -1;
		
//		if(dao.checkCarNumber(car.getCarName()) > 0){
			x = dao.update(car);
//		}
		
		return x;
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}

	@Override
	public int getCountBySpot(String spot) {
		return dao.getCountBySpot(spot);
	}

	@Override
	public List<Car> getAllBySpot(String spot) {
		List<Car> list = null;
		
		if(getCountBySpot(spot) > 0){
			list = dao.getAllBySpot(spot);
		}
		
		return list;
	}

	@Override
	public int checkCarNumber(String carNumber) {
			
		
		return dao.checkCarNumber(carNumber);
	}
}
