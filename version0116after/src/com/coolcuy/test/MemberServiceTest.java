package com.coolcuy.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coolcuy.dto.LicenseDto;
import com.coolcuy.dto.MemberDto;
import com.coolcuy.exception.DuplicateException;
import com.coolcuy.exception.NotFoundMemberExecption;
import com.coolcuy.service.MemberServiceImpl;

public class MemberServiceTest {
	MemberServiceImpl service = new MemberServiceImpl();
	List<MemberDto> members = new ArrayList<MemberDto>();
	
	@Before
	public void setUp() {
		service.deleteAll();
		
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
	}

	@After
	public void end() {}
	
	@Test
	public void addAndGet(){
		for(MemberDto member : members){
			service.add(member);
		}
		
		for(int i=0; i<members.size(); i++){
			MemberDto getMember = service.get(members.get(i).getEmail());
			validate(getMember, members.get(i));
		}
	}
	
	@Test
	public void addAndDelete(){
		addByTest();
		
		service.delete(members.get(0).getEmail());
		
		int count = service.getCount();
		
		assertThat(count, is(members.size()-1));
	}
	
	@Test
	public void getAll(){
		addByTest();
		
		List<MemberDto> getMembers = service.getAll();
		
		for(int i=0; i<members.size(); i++){
			validate(members.get(i), getMembers.get(i));
		}
	}
		
	@Test(expected = DuplicateException.class)
	public void duplicate(){
		addByTest();
		service.checkDuplicate(members.get(0).getEmail());
	}
	
	public void addByTest(){
		for(MemberDto member : members){
			service.add(member);
		}
	}
	
	@Test
	public void update(){
		addByTest();
		
		MemberDto updateMember = new MemberDto("aong@naver.com", "01011112222", "봉봉이", "1111", "12345", 
				"대구 신암동 해봉이동네", "한국 아이티 교육원", "대구, 서울", new LicenseDto("aong@naver.com", "04-025005-11", "1종보통", "1",
				"2010-01-01", "2017-07-07"));
		
		MemberDto updatedMember = service.update(updateMember);
		
		validate(updatedMember, updateMember);
	}
	
	@Test
	public void checkPassword(){
		addByTest();
		
		int x = service.checkPassword(members.get(0).getEmail(), members.get(0).getPassword());
		assertThat(x, is(1));
	}
	
	@Test(expected = NotFoundMemberExecption.class)
	public void failCheckPassword(){
		addByTest();
		
		service.checkPassword(members.get(0).getEmail(), "잘못된비밀번호");
	}

	public void validate(MemberDto getMember, MemberDto member) {
		assertThat(getMember.getEmail(), is(member.getEmail()));
		assertThat(getMember.getPhoneNumber(), is(member.getPhoneNumber()));
		assertThat(getMember.getName(), is(member.getName()));
		assertThat(getMember.getPassword(), is(member.getPassword()));
		assertThat(getMember.getRating(), is(member.getRating()));
		assertThat(getMember.getZipCode().trim(), is(member.getZipCode()));
		assertThat(getMember.getRoadAddr(), is(member.getRoadAddr()));
		assertThat(getMember.getDetailAddr(), is(member.getDetailAddr()));
		
		LicenseDto license = member.getLicenseDto();
		LicenseDto getLicense = getMember.getLicenseDto();
		
		assertThat(getLicense.getEmail(), is(license.getEmail()));		
		assertThat(getLicense.getLicenseNumber(), is(license.getLicenseNumber()));		
		assertThat(getLicense.getLicenseType(), is(license.getLicenseType()));		
		assertThat(getLicense.getIssuDate(), is(license.getIssuDate()));
		assertThat(getLicense.getExpiryDate(), is(license.getExpiryDate()));
	}
}