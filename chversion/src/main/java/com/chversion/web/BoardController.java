package com.chversion.web;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chversion.domain.BoardVo;
import com.chversion.service.BoardService;


@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVo board, Model model) throws Exception {
		logger.info("regist get ...........");
	}

	 @RequestMapping(value = "/register", method = RequestMethod.POST)
	 public String registPOST(BoardVo board, Model model) throws Exception{
	 logger.info("regist post .............");
	 logger.info(board.toString());
	
	 service.regist(board);
	
	 model.addAttribute("result", "success");
	
	 return "/board/success";
	 }

}
