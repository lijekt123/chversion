package com.chversion.web;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chversion.domain.BoardVo;
import com.chversion.persistence.BoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoTest {
	
	@Inject
	private BoardDao dao;
	
	@Test
	public void testCreate() throws Exception{
		BoardVo board = new BoardVo();
		board.setTitle("새로운 글을 넣습니다.");
		board.setContent("새로운 글");
		board.setWrite("user00");
		
		dao.create(board);
		
		BoardVo vo = dao.read(1);
		System.out.println(vo);
		
		BoardVo updateBoard = new BoardVo();
		updateBoard.setBno(1);
		updateBoard.setTitle("업데이트 글");
		updateBoard.setContent("업데이트 글");
		
		dao.update(updateBoard);
		
		BoardVo updatedBoard = dao.read(1);
		System.out.println(updatedBoard);
		
		dao.delete(1);
	}
	
	@Test
	public void testList() throws Exception{
		List<BoardVo> list = dao.listAll();
		
		for(BoardVo vo : list){
			System.out.println(vo);
		}
	}
	
	

}
