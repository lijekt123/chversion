package com.coolcuy.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.coolcuy.dto.CardDto;
import com.coolcuy.dto.LicenseDto;
import com.coolcuy.dto.MemberDto;
import com.coolcuy.dto.MemberJoinDto;
import com.coolcuy.service.MemberJoinService;
import com.coolcuy.service.MemberJoinServiceImpl;
import com.coolcuy.service.MemberService;
import com.coolcuy.service.MemberServiceImpl;

public class MemberJoinServiceTest {
	List<MemberJoinDto> joins = new ArrayList<MemberJoinDto>();
	List<CardDto> cards = new ArrayList<CardDto>();
	List<MemberDto> members = new ArrayList<MemberDto>();
	MemberJoinService service = new MemberJoinServiceImpl();
	MemberService memberService = new MemberServiceImpl();
	
	@Before
	public void setup(){
		service.deleteAll();
		
		cards = Arrays.asList(
				new CardDto("111-456-7890-00", "대구은행", "2011-10-11","851130","1234","aong@naver.com"),
				new CardDto("222-456-7891-00", "농협", "2011-10-11","851130","1234","b@naver.com"),
				new CardDto("333-456-7892-00", "우리은행", "2011-10-11","851130","1234","c@gmail.com")
				);
		
		members = Arrays.asList(
				new MemberDto("aong@naver.com", "01011112222", "해봉이", "1111", "12345", 
						"대구 신암동 해봉이동네", "한국 아이티 교육원", "대구, 서울", new LicenseDto("aong@naver.com", "04-025005-11", "1종보통", "1",
						"2010-01-01", "2017-07-07")),
				new MemberDto("b@naver.com", "0104445555", "홍길동", "222", "54321", 
						"경북경산시", "영대", "경북, 서울", new LicenseDto("b@naver.com", "11-025005-44", "2종보통", "1",
						"2011-01-01", "2018-07-07")),
				new MemberDto("c@gmail.com", "01011112222", "해봉이", "333", "32132", 
						"울릉도", "IBM", "울릉도, 서울", new LicenseDto("c@gmail.com", "03-023215-55", "1종보통", "2",
						"2015-05-05", "2027-01-02"))
				);
		
		joins = Arrays.asList(
					new MemberJoinDto(members.get(2), cards.get(2)),
					new MemberJoinDto(members.get(1), cards.get(1)),
					new MemberJoinDto(members.get(0), cards.get(0))
				);
	}
	
	@Test
	public void addAndGet(){
		addByTest();
	}
	
	public void addByTest(){
		for(MemberJoinDto join : joins){
			service.add(join);
		}
	}	
}
