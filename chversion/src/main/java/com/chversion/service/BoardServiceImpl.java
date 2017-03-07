package com.chversion.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chversion.domain.BoardVo;
import com.chversion.persistence.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDao dao;
	
	@Override
	public void regist(BoardVo board) throws Exception {
		dao.create(board);
	}

	@Override
	public BoardVo read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVo board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVo> listAll() throws Exception {
		return dao.listAll();
	}

}
