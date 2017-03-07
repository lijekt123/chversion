package com.yun.factory;

import com.yun.dao.CarDao;
import com.yun.dao.CarDaoImpl;
import com.yun.dao.MemberDao;
import com.yun.dao.MemberDaoImpl;
import com.yun.dao.RentDao;
import com.yun.dao.RentDaoImpl;
import com.yun.service.CarService;
import com.yun.service.CarServiceImpl;
import com.yun.service.MemberService;
import com.yun.service.MemberServiceImpl;
import com.yun.service.RentService;
import com.yun.service.RentServiceImpl;

public class Factory {	
	public MemberDao createMemberDao(){
		return new MemberDaoImpl();
	}
	
	public MemberService createMemberService(MemberDao dao){
		return new MemberServiceImpl(dao);
	}
	
	public CarDao createCarDao(){
		return new CarDaoImpl();
	}
	
	public CarService createCarService(CarDao dao){
		return new CarServiceImpl(dao);
	}
	
	public RentDao createRentDao(){
		return new RentDaoImpl();
	}
	
	public RentService createRentService(RentDao dao){
		return new RentServiceImpl(dao);
	}
	
}
