package com.chversion.service;

import java.util.List;

import com.chversion.domain.BoardVo;

public interface BoardService {
	
	public void regist(BoardVo board)throws Exception;
	
	public BoardVo read(int bno)throws Exception;
	
	public void modify(BoardVo board)throws Exception;
	
	public void remove(int bno)throws Exception;
	
	public List<BoardVo> listAll() throws Exception;

}
