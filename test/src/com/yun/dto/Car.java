package com.yun.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Car {
	private String carNumber;
	private String brand;
	private String oil;
	private int hourPrice;
	private String carName;
	private String carOption;
	private String carCategory;
	private String carSpot;
	
	public Car() {}
	
	public Car(String carNumber, String brand, String oil, int hourPrice, String carName, String carOption, String carCategory, String carSpot) {
		this.carNumber = carNumber;
		this.brand = brand;
		this.oil = oil;
		this.hourPrice = hourPrice;
		this.carName = carName;
		this.carOption = carOption;
		this.carCategory = carCategory;
		this.carSpot = carSpot;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOil() {
		return oil;
	}

	public void setOil(String oil) {
		this.oil = oil;
	}

	public int getHourPrice() {
		return hourPrice;
	}

	public void setHourPrice(int hourPrice) {
		this.hourPrice = hourPrice;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarOption() {
		return carOption;
	}

	public void setCarOption(String carOption) {
		this.carOption = carOption;
	}

	public String getCarCategory() {
		return carCategory;
	}

	public void setCarCategory(String carCategory) {
		this.carCategory = carCategory;
	}

	public String getCarSpot() {
		return carSpot;
	}

	public void setCarSpot(String carSpot) {
		this.carSpot = carSpot;
	}

	@Override
	public String toString() {
		return "Car [carNumber=" + carNumber + ", rentState=" 
				+ brand + ", oil=" + oil + ", hourPrice="
				+ hourPrice + ", carName=" + carName + ", carOption="
				+ carOption + ", carCategory=" + carCategory + ", carSpot="
				+ carSpot + "]";
	}
	
	/*public static void main(String[] args) {
		Car car = new Car();
		car.setHourPrice(1000);
		int x = car.totalPrice("2016년10월10일10시10분", "2016년10월11일10시10분", 1);
		System.out.println(x);
	}*/
	
	public int totalPrice(String startDate, String returnDate, int insurance){
	      int addPrice = 0;
	      
	      if(insurance == 1){
	         addPrice += 10000;
	      }
	      
	      DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
	      
	      Date sDate = null;
	      Date eDate = null;
	      long tempDate = 0;
	      long total = 0;
	      
	      int hour = 0;
	      int  result= 0;
	      
	      try {
	         sDate = format.parse(startDate);
	         eDate = format.parse(returnDate);
	    
	         tempDate = eDate.getTime() - sDate.getTime();

	         total = tempDate / (60*60*1000);         
	         hour = (int)total;
	         
	         result = hour * hourPrice + addPrice;
	         
	      } catch (ParseException e) {
	         e.printStackTrace();
	      }
	/*      
	      System.out.println("보험 가격 : " + addPrice);
	      System.out.println("시작 날짜 : " + startDate);
	      System.out.println("반납 날짜 : " + returnDate);
	      System.out.println("시간 차이 : " + hour);
	      System.out.println("대여 시간당 가격 : " + hourPrice);
	      System.out.println("총 가격 : " + result);*/
	      
	      return result;
	   }
	
}
