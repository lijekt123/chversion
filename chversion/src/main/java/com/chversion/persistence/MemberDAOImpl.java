package com.chversion.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.springframework.stereotype.Repository;

import com.chversion.domain.MemberVo;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace =
			"com.chversion.mappers.memberMapper";
	
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace+".getTime");
	}

	@Override
	public void insertMember(MemberVo vo) {
		sqlSession.insert(namespace+".insertMember", vo);
	}

	@Override
	public MemberVo readMember(String userid) throws Exception {
		return (MemberVo)
				sqlSession.selectOne(namespace+".selectMember", userid);
	}

	@Override
	public MemberVo readWithPW(String userid, String pw) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("userid", userid);
		paramMap.put("userpw", pw);
		
		return sqlSession.selectOne(namespace+".readWithPW", paramMap);
	}

	@Override
	public void deleteAll() throws Exception {
		sqlSession.delete(namespace+".deleteMember");
		
	}
}
