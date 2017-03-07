package com.yun.service;

import com.yun.dto.Member;

public interface MemberService extends GenericService<Member>{
	public int getCount();
	public int checkId(String id);
}
