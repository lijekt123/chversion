package com.yun.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yun.dao.RentDao;
import com.yun.dao.RentSeqDao;
import com.yun.dao.RentSeqDaoImpl;
import com.yun.dto.Car;
import com.yun.dto.Member;
import com.yun.dto.Rent;
import com.yun.factory.Factory;
import com.yun.service.CarService;
import com.yun.service.MemberService;
import com.yun.service.RentService;

public class RentServiceTest {
	Factory factory = new Factory();
	RentDao dao;
	List<Rent> rentList;
	RentService rentService;
	MemberService memberService;
	CarService carService;
	RentSeqDao rentSeqDao;
	List<Member> memberList;
	List<Car> carList;
	
	public void addCarAndMember(){
		for(int i=0; i<carList.size(); i++){
			carService.add(carList.get(i));
		}
		
		for(int i=0; i<memberList.size(); i++){
			memberService.add(memberList.get(0));
		}
	}
	
	public void deleteAllCarAndMember(){
		carService.deleteAll();
		memberService.deleteAll();
	}
	
	@Before
	public void setUp(){
		dao = factory.createRentDao();
//		service
		memberService = factory.createMemberService(factory.createMemberDao());
		carService = factory.createCarService(factory.createCarDao());
		rentService = factory.createRentService(factory.createRentDao());
		
		rentSeqDao = new RentSeqDaoImpl();
		
//		테스트를 위한 List
		memberList = new ArrayList<Member>();
		carList = new ArrayList<Car>();

		rentService.deleteAll();
		
		rentSeqDao.dropSeq();
		rentSeqDao.createSeq();
		
		carList = Arrays.asList(
				new Car("10가 1000", "현대", "가솔린", 1000, "아반떼", "골드", "중준형", "서울"),
				new Car("20나 2000", "기아", "가솔린", 2000, "모하비", "실버", "suv", "대구"),
				new Car("30다 3000", "쌍용", "디젤", 3000, "체어맨", "플래티넘", "대형", "서울"),
				new Car("40라 4000", "벤츠", "디젤", 4000, "E-클래스", "골드", "대형", "대구")
		);
		
		memberList = Arrays.asList(
				new Member("aaaa", "1111", "조조", "010-111-1111", "050101"),
				new Member("bbbb", "2222", "유비", "010-222-2222", "030202"),
				new Member("cccc", "3333", "손견", "010-333-3333", "080303"),
				new Member("dddd", "4444", "조운", "011-444-4444", "000404")
		);
		
		rentList = Arrays.asList(
					new Rent("2016-10-05 06:19", "0016-10-06 06:19", 0, "1111", "10가 1000", memberList.get(0), carList.get(0)),
					new Rent("2016-10-07 06:20", "0016-10-08 16:20", 1, "1111", "10가 1000", memberList.get(1), carList.get(1)),
					new Rent("2016-10-09 06:20", "0016-10-10 16:20", 0, "1111", "10가 1000", memberList.get(2), carList.get(2)),
					new Rent("2016-10-11 06:20", "0016-10-12 16:20", 0, "1111", "10가 1000", memberList.get(0), carList.get(3))			
		);
		
		deleteAllCarAndMember();
		
		addCarAndMember();
	}
	
//	@Test
//	public void totalPrice(){		
//		
//	}

	
	@Test
	public void addAndGet() {
		rentService.add(rentList.get(0), memberList.get(0).getId(), carList.get(0).getCarNumber());
		Rent getRent = rentService.get(memberList.get(0).getId(), 1000);
		
//		System.out.println(getRent.getRegDate());
		verifyRent(rentList.get(0), getRent);
	}

	
	@Test
	public void getAll(){
		addForTest();		
		
		List<Rent> getRentList = dao.getAll();
	
		for(int i=0; i<getRentList.size(); i++){
			verifyRent(rentList.get(i), getRentList.get(i));
		}
	}

	public void addForTest(){
		for(int i=0; i<rentList.size(); i++){
			dao.add(rentList.get(i), rentList.get(i).getMember().getId(), rentList.get(i).getCar().getCarNumber());
		}
	}
	
//	@Test
//	public void getAndDelete(){
//		
//	
//	}

	public void verifyRent(Rent rent, Rent getRent){
		assertThat(rent.getRentSpot(), is(getRent.getRentSpot()));
		assertThat(rent.getReturnSpot(), is(getRent.getReturnSpot()));
		assertThat(rent.getInsurance(), is(getRent.getInsurance()));
		assertThat(rent.getStartDate(), is(getRent.getStartDate()));
		assertThat(rent.getEndDate(), is(getRent.getEndDate()));
		
		assertThat(rent.getCar().getCarName(), is(getRent.getCar().getCarName()));
		assertThat(rent.getCar().getCarSpot(), is(getRent.getCar().getCarSpot()));
		assertThat(rent.getMember().getId(), is(getRent.getMember().getId()));
		assertThat(rent.getMember().getName(), is(getRent.getMember().getName()));
	}
	
}
