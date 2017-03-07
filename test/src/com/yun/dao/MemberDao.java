package com.yun.dao;

import com.yun.dto.Member;

public interface MemberDao extends GenericDao<Member>{
	public int getCount();
	public int checkId(String id);
}
