package com.yun.dto;

public class Rent {
	private int rentNumber;
	private String startDate;
	private String endDate;
	private String regDate;
	private int insurance;
	private String rentSpot;
	private String returnSpot;
	private int rentState;
	private Member member;
	private Car car;
	
	public Rent() {}
	
	// testAdd
	public Rent(String startDate, String endDate, int insurance, String rentSpot, String returnSpot, Member member, Car car) {
		this.rentNumber = 0;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regDate = null;
		this.insurance = insurance;
		this.rentSpot = rentSpot;
		this.returnSpot = returnSpot;
		this.rentState = 0;
		this.member = member;
		this.car = car;
	}
	
	// add
	public Rent(String startDate, String endDate, int insurance, String rentSpot, String returnSpot) {
		this.rentNumber = 0;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regDate = null;
		this.insurance = insurance;
		this.rentSpot = rentSpot;
		this.returnSpot = returnSpot;
		this.rentState = 0;
		this.member = null;
		this.car = null;
	}
	
	// get
	public Rent(int rentNumber, String startDate, String endDate, String regDate, int insurance, String rentSpot, String returnSpot, Member member, Car car) {
		this.rentNumber = rentNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regDate = regDate;
		this.insurance = insurance;
		this.rentSpot = rentSpot;
		this.returnSpot = returnSpot;
		this.rentState = 0;
		this.member = member;
		this.car = car;
	}
	
	public int getRentNumber() {
		return rentNumber;
	}
	public void setRentNumber(int rentNumber) {
		this.rentNumber = rentNumber;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getInsurance() {
		return insurance;
	}
	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}
	public String getRentSpot() {
		return rentSpot;
	}
	public void setRentSpot(String rentSpot) {
		this.rentSpot = rentSpot;
	}
	public String getReturnSpot() {
		return returnSpot;
	}
	public void setReturnSpot(String returnSpot) {
		this.returnSpot = returnSpot;
	}
	public int getRentState() {
		return rentState;
	}
	public void setRentState(int rentState) {
		this.rentState = rentState;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	
}
