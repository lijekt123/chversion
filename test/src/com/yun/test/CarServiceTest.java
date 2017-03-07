package com.yun.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yun.dao.CarDao;
import com.yun.dto.Car;
import com.yun.factory.Factory;
import com.yun.service.CarService;

public class CarServiceTest {
	Factory factory = new Factory();
	CarService service;
	CarDao dao;
	List<Car> cars;
	
	@Before
	public void setUp(){
		dao = factory.createCarDao();
		service = factory.createCarService(dao);
		
		service.deleteAll();
		
		cars = Arrays.asList(
					new Car("10가 1000", "현대", "가솔린", 1000, "아반떼", "골드", "중준형", "서울"),
					new Car("20나 2000", "기아", "가솔린", 2000, "모하비", "실버", "suv", "대구"),
					new Car("30다 3000", "쌍용", "디젤", 3000, "체어맨", "플래티넘", "대형", "서울"),
					new Car("40라 4000", "벤츠", "디젤", 4000, "E-클래스", "골드", "대형", "대구")
				);
	}
	
	@Test
	public void getCountBySpot(){
		addForTest();
		
		int x = service.getCountBySpot(cars.get(0).getCarSpot());
		
		assertThat(x, is(2));
	}
	
	@Test
	public void update(){
		service.add(cars.get(0));
		
		Car updateCar = new Car(cars.get(0).getCarNumber(), "321321", "111", 100, "carName", "carOption", "carCategory", "carSpot");
		service.update(updateCar);
		
		Car getCar = service.get(cars.get(0).getCarNumber());
		
		verifyCar(updateCar, getCar);
	}
	
	@Test
	public void addAndGet(){		
		service.add(cars.get(0));
		
		Car getCar = service.get(cars.get(0).getCarNumber());
		
		verifyCar(cars.get(0), getCar);
	}
	
	public void verifyCar(Car car, Car getCar){
		assertThat(getCar.getCarNumber(), is(car.getCarNumber()));
		assertThat(getCar.getCarName(), is(car.getCarName()));
		assertThat(getCar.getCarCategory(), is(car.getCarCategory()));
		assertThat(getCar.getCarOption(), is(car.getCarOption()));
		assertThat(getCar.getCarSpot(), is(car.getCarSpot()));
		assertThat(getCar.getOil(), is(car.getOil()));
		assertThat(getCar.getHourPrice(), is(car.getHourPrice()));		
	}
	
	@Test
	public void delete(){
		addForTest();
		
		service.delete(cars.get(0).getCarNumber());
		
		assertThat(service.getCount(), is(3));
	}
	
	@Test
	public void checkCarNumber(){
		addForTest();
		
		
		int x = 0;
		x = service.checkCarNumber(cars.get(0).getCarNumber());
		assertThat(x, is(1));	
		
//		for(int i=0; i<cars.size(); i++){
//		
//			x = service.checkCarNumber(cars.get(i).getCarNumber());
//			assertThat(x, is(1));
//		}
	}
	
	@Test
	public void getAllAndCount(){
		addForTest();
		
		List<Car> getCars = service.getAll();
		
		for(int i=0; i<cars.size(); i++){
			verifyCar(cars.get(i), getCars.get(i));
		}
		
		assertThat(service.getCount(), is(4));
	}
	
	public void addForTest(){
		for(int i=0; i<cars.size(); i++){
			service.add(cars.get(i));
		}
	}

	@Test
	public void getAllSpotCar(){
		addForTest();
		List<Car> getSpotCars = service.getAllBySpot(cars.get(0).getCarSpot());
		
		assertThat(getSpotCars.size(), is(2));

		verifyCar(cars.get(0), getSpotCars.get(0));
		verifyCar(cars.get(2), getSpotCars.get(1));
	}
}