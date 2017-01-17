package com.coolcuy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coolcuy.dao.MemberDao;
import com.coolcuy.dao.MemberDaoImpl;
import com.coolcuy.dto.CardDto;
import com.coolcuy.dto.LicenseDto;
import com.coolcuy.dto.MemberDto;
import com.coolcuy.dto.MemberJoinDto;
import com.coolcuy.service.MemberJoinService;
import com.coolcuy.service.MemberJoinServiceImpl;

public class JoinHandler implements CommandHandler{
	
	private final String FORM_VIEW = "mypage02.jsp";
	private MemberJoinService joinService = new MemberJoinServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse respose) {
		// 사용자가 요청한 데이터를 가공해서 넣어야 한다.
		System.out.println("name");
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("emailValue"));
		System.out.println(request.getParameter("password"));
		System.out.println(request.getParameter("zeroPhone"));
		System.out.println(request.getParameter("firPhone"));
		System.out.println(request.getParameter("secPhone"));
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("password"));
		System.out.println(request.getParameter("zipAddr"));
		System.out.println(request.getParameter("roadAddr"));
		System.out.println(request.getParameter("detailAddr"));
		System.out.println(request.getParameter("primaryArea"));
		System.out.println(request.getParameter("licenseType"));
		System.out.println(request.getParameter("licenseNumber1"));
		System.out.println(request.getParameter("licenseNumber2"));
		System.out.println(request.getParameter("licenseNumber3"));
		System.out.println(request.getParameter("licenseNumber4"));
		System.out.println(request.getParameter("expiryDate1"));
		System.out.println(request.getParameter("expiryDate2"));
		System.out.println(request.getParameter("expiryDate3"));
		System.out.println(request.getParameter("issuDate1"));
		System.out.println(request.getParameter("issuDate2"));
		System.out.println(request.getParameter("issuDate3"));
		System.out.println(request.getParameter("birth"));
		System.out.println(request.getParameter("cardPassword"));
		System.out.println(request.getParameter("cardNumber1"));
		System.out.println(request.getParameter("cardNumber2"));
		System.out.println(request.getParameter("cardNumber3"));
		System.out.println(request.getParameter("cardNumber4"));
		System.out.println(request.getParameter("cardExpiryDate1"));
		System.out.println(request.getParameter("cardExpiryDate2"));
		System.out.println(request.getParameter("birth"));
		System.out.println(request.getParameter("cardPassword"));
		System.out.println(request.getParameter("gender"));
		
		String email=request.getParameter("email")+"@"+request.getParameter("emailValue");
		String phoneNumber=request.getParameter("zeroPhone")+"-"+request.getParameter("firPhone")+"-"+request.getParameter("secPhone");
		String licenseNumber=request.getParameter("licenseNumber1")+request.getParameter("licenseNumber2")+request.getParameter("licenseNumber3")+request.getParameter("licenseNumber4");
		String expiryDate=request.getParameter("expiryDate1")+"-"+request.getParameter("expiryDate2")+"-"+request.getParameter("expiryDate3");
		String issuDate=request.getParameter("issuDate1")+"-"+request.getParameter("issuDate2")+"-"+request.getParameter("issuDate3");
		String cardNumber=request.getParameter("cardNumber1")+request.getParameter("cardNumber2")+request.getParameter("cardNumber3")+request.getParameter("cardNumber4");
		String cardExpiryDate="17-"+request.getParameter("cardExpiryDate1")+"-"+request.getParameter("cardExpiryDate2");
		String regDate="일단이걸로";
		
		System.out.println(email);
		System.out.println(phoneNumber);
		System.out.println(licenseNumber);
		System.out.println(expiryDate);
		System.out.println(issuDate);
		System.out.println(cardNumber);
		System.out.println(cardExpiryDate);
		System.out.println(regDate);

		LicenseDto license = new LicenseDto(
				email, 
				licenseNumber, 
				request.getParameter("licenseType"), 
				request.getParameter("gender"), 
				issuDate,
				expiryDate);
	
		MemberDto member = new MemberDto(
				email,
				phoneNumber, 
				request.getParameter("name"), 
				request.getParameter("password"), 
				request.getParameter("zipAddr"), 
				request.getParameter("roadAddr"), 
				request.getParameter("detailAddr"), 
				request.getParameter("primaryArea"),
				license);
		
		CardDto card = new CardDto(
				cardNumber,
				request.getParameter("cardType"),
				cardExpiryDate,
				request.getParameter("birth"),
				request.getParameter("cardPassword"),
				request.getParameter("regDate"),
				email);
		
		try{
			joinService.add(new MemberJoinDto(member, card));
		}catch(Exception e){
			e.printStackTrace();
			return FORM_VIEW;
		}
		return "/index.do";
	}
}