package com.yun.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yun.dto.Member;
import com.yun.factory.Factory;
import com.yun.service.MemberService;

public class MemberServiceTest {
	Factory factory = new Factory();
	MemberService service;
	List<Member> members;
		
	@Before
	public void setUp(){
		service = factory.createMemberService(factory.createMemberDao());
		
		service.deleteAll();
		
		members = Arrays.asList(
				new Member("aaaa", "1111", "조조", "010-111-1111", "050101"),
				new Member("bbbb", "2222", "유비", "010-222-2222", "030202"),
				new Member("cccc", "3333", "손견", "010-333-3333", "080303"),
				new Member("dddd", "4444", "조운", "011-444-4444", "000404")
		);
	}
	
	@Test
	public void addAndGet() {
		service.add(members.get(0));
		
		Member getMember = service.get("aaaa");
		
		verifyMember(members.get(0), getMember);
	}
	
	public void verifyMember(Member member, Member getMember){
		assertThat(member.getId(), is(getMember.getId()));
		assertThat(member.getPassword(), is(getMember.getPassword()));
		assertThat(member.getName(), is(getMember.getName()));
		assertThat(member.getPhoneNumber(), is(getMember.getPhoneNumber()));
		assertThat(member.getBirth(), is(getMember.getBirth()));
	}
	
	@Test
	public void getAllAndCount(){
		addForTest();
		
		List<Member> getMembers = service.getAll();
		
		for(int i=0; i<members.size(); i++){
			verifyMember(members.get(i), getMembers.get(i));
		}
		
		assertThat(service.getCount(), is(4));
	}
	
	public void addForTest(){		
		for(int i=0; i<members.size(); i++){
			service.add(members.get(i));
		}
	}
	
	@Test
	public void update(){
		service.add(members.get(0));
		
		Member member = new Member("aaaa", "1010", "관우", "010-111-1111", "101201");
		
		service.update(member);
		
		Member getMember = service.get("aaaa");
		
		verifyMember(member, getMember);
	}
}
