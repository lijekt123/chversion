package com.chversion.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.chversion.domain.BoardVo;

//@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.chversion.mappers.BoardMapper";
	
	public BoardDaoImpl() {}
	
	@Override
	public void create(BoardVo vo) throws Exception {
		session.insert(namespace+".create", vo); 
	}

	@Override
	public BoardVo read(int bno) throws Exception {
		return session.selectOne(namespace+".read", bno);
	}

	@Override
	public void update(BoardVo vo) throws Exception {
		session.update(namespace+".update", vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		session.delete(namespace+".delete", bno);
	}

	@Override
	public List<BoardVo> listAll() throws Exception {
		return session.selectList(namespace+".listAll");
	}
}