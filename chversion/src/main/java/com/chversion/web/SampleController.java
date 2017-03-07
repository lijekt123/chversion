package com.chversion.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chversion.domain.ProductVO;

@Controller
public class SampleController {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(SampleController.class);

	@RequestMapping("doA")
	public void doA(){
		logger.info("doA called..................");
	}
	
	@RequestMapping("doB")
	public void doB(){
		logger.info("doB called..................");
	}
	
	@RequestMapping("doC")
	public String doC(@ModelAttribute("msg") String msg){
		logger.info("doC called..................");
		
		return "result";
	}
	
	@RequestMapping("/doD")
	public String doD(Model model){
		ProductVO product = new ProductVO("yun", 10000);
		
		logger.info("doD");
		
		model.addAttribute(product);
		
		return "pd";
	}
	
	
//	일단 보류
//	@RequestMapping("/doE")
//	public String doF(RedirectAttributes rttr){
//		logger.info("doE called but redirect to /doF...........");
//		
//		rttr.addFlashAttribute("msg", "This is wenfknwek");
//		return "redirect:/doF";
//	}
//	
//	@RequestMapping(value = "/doF", method = RequestMethod.GET)
//	public void doF(@ModelAttribute String msg){
//		logger.info("doF called..........." + msg);
//	}
	
		
	@RequestMapping("/doJ")
	public @ResponseBody ProductVO doJSON(){
		ProductVO vo = new ProductVO("샘플", 30000);
		System.out.println("test");
		
		return vo;
	}
}
