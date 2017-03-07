package com.yun.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yun.dto.Member;
import com.yun.factory.Factory;
import com.yun.service.CarService;
import com.yun.service.MemberService;
import com.yun.service.RentService;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Factory factory = new Factory();  
	CarService carService = factory.createCarService(factory.createCarDao());
	MemberService memberService = factory.createMemberService(factory.createMemberDao());
	RentService rentService = factory.createRentService(factory.createRentDao());
	
    public Controller() {
        super();
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		intro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		intro(request, response);
		
	}
	
	private void intro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		String viewPage = null;
		int x = -1;
		System.out.println(request.getParameter("phoneNumber"));
		if(com.equals("/joinMember.do")){
			x = memberService.add(
					new Member(
							request.getParameter("id"), 
							request.getParameter("password"), 
							request.getParameter("name"), 
							request.getParameter("phoneNumber"), 
							request.getParameter("birth")
					)
			);
			
			if(x == 1){
				viewPage = "welcome.jsp";
			}else{
				viewPage = "failJoin.jsp";
			}
		}else if(com.equals("/checkId.do")){
			x = memberService.checkId(request.getParameter("id"));
			request.setAttribute("result", x);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
