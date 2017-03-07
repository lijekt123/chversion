package com.chversion.persistence;

import java.util.List;

import com.chversion.domain.BoardVo;

public interface BoardDao {
	public void create(BoardVo vo) throws Exception;
	public BoardVo read(int bno) throws Exception;
	public void update(BoardVo vo) throws Exception;
	public void delete(int bno) throws Exception;
	public List<BoardVo> listAll() throws Exception;
}
