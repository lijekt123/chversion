package com.yun.dto;

public class Member {
	private String id;
	private String password;
	private String name;
	private String phoneNumber;
	private String birth;
	
	public Member() {}
	
	public Member(String id, String password, String name, String phoneNumber, String birth) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birth = birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	@Override
	public String toString() {
		String str = "[";
		str += "아이디 : " + id + ", 패스워드 : " + password + ", 이름 : " + name + ", 휴대폰 : " + phoneNumber + ", 생일 : " + birth;
		
		return str + "]";
	}
	
}
