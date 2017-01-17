<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/mypage01.css">
<!-- <link rel="stylesheet" href="/version1/css/mypage01.css"> -->
<title>Insert title here</title>
</head>
<body>
 <div id="wrap">
	    <header id="header">
	        <div id="top_line">
	        </div>
	        <div id="top_back">
	            <div id="small_nav">
	                <a href="#">로그인</a><span>|</span>
	                <a href="#">회원가입</a><span>|</span>
	                <a href="#">마이페이지</a>
	            </div>
	        </div>
	
	        <nav>
	            <ul>
	                <li id="logo"><a href="#">SAMPLE LOGO</a></li>
	                <li class="nav_text"><a href="#">서비스안내</a></li>
	                <li class="nav_text"><a href="#">요금안내</a></li>
	                <li class="nav_text"><a href="#">고객센터</a></li>
	                <li class="nav_text"><a href="#">이벤트/쿠폰</a></li>
	                <li id="nav_text_color"><a href="#">실시간예약</a></li>
	            </ul>
	        </nav>
	        <div class="clear"></div>
	        <div id="nav_line"></div>
	    </header>
	
	    <section id="row1">
	        <div id="row1_text01">마이페이지</div>
	        <div id="row1_text02">
	            <ul>
	                <li>
	                    <a href="#"><img src="imag/member_img02.png"></a>
	                </li>
	                <li class="text02_up"><a href="#">Home</a></li>
	                <li class="text02_up"><a>></a></li>
	                <li class="text02_up"><a href="#">마이페이지</a></li>
	            </ul>
	        </div>
	    </section>
	
	    <section id="row2">
	        <aside>
	            <div class="row2_hd"><a href="#">마이페이지</a></div>
	            <ul>
	                <li><a href="#">내정보</a></li>
	                <li><a href="#">예약내역</a></li>
	                <li><a href="#">내쿠폰</a></li>
	                <li><a href="#">결제내역</a></li>
	            </ul>
	        </aside>
	        <div class="row2_con">
	            <h2>기본정보</h2>
	            <div class="row2_con_01">이름</div>
	            <div class="row2_con_back">
	                <div class="back_text">홍길동</div>
	            </div>
	            <div class="row2_con_01">E-mail</div>
	            <div class="row2_con_back">
	                <div class="back_text">abcdefg@naver.com</div>
	            </div>
	            <div class="row2_con_01">비밀번호</div>
	            <div class="row2_con_back">
	                <div class="row2_con_in01">
	                    <input type="password">
	                </div>
	            </div>
	            <div class="row2_con_01">비밀번호확인</div>
	            <div class="row2_con_back">
	                <div class="row2_con_in01">
	                    <input type="password">
	                </div>
	                <a href="#"><div id="pas_bt">변경</div></a>
	            </div>
	            <div class="row2_con_01">휴대폰번호</div>
	            <div class="row2_con_back">
	                <div id="row2_con_phon">
	                    <input type="text">
	                    <div class="phon_tx1">-</div>
	                    <input type="text">
	                    <div class="phon_tx1">-</div>
	                    <input type="text">
	                    <a href="#"><div id="phon_bt">변경</div></a>
	                </div>
	            </div>
	            <div id="row2_con_02">주소</div>
	            <div id="row2_con_addback">
	                <div id="row2_con_add">
	                    <input type="text">
	                    <div class="add_tex1">-</div>
	                    <input type="text">
	                    <a href="#"><div id="add_bt">주소찾기</div></a>
	                </div>
	                <div class="clear"></div>
	                <div id="row2_con_add2">
	                    <input type="text">
	                </div>
	            </div>
	            <div class="clear"></div>
	            <div class="row2_con_01">주이용지역</div>
	            <div class="row2_con_back">
	                    <input type="checkbox"><span class="con_area">서울</sapn>
	                    <input type="checkbox"><span class="con_area" id="area01">인천/경기</span>
	                    <input type="checkbox"><span class="con_area">대구/경북</span>
	                    <input type="checkbox"><span class="con_area">부산/경남</span>
	                    <input type="checkbox"><span class="con_area">광주/전라</span>
	                    <input type="checkbox"><span class="con_area">강원</span>
	                    <input type="checkbox"><span class="con_area">제주</span>
	            </div>
	        </div>
	        <div class="clear"></div>
	        <div class="row2_con" id="con02">
	            <h2>운전면허 정보</h2>
	            <div class="row2_con_01">면허종류</div>
	            <div class="row2_con_back">
	                <input type="checkbox"><span class="con_area">1종보통</span>
	                <input type="checkbox"><span class="con_area">2종보통</span>
	                <input type="checkbox"><span class="con_area">1종대형</span>
	            </div>
	            <div class="row2_con_01">면허번호</div>
	            <div class="row2_con_back">
	                <div id="row2_con_num">
	                    <input type="text">
	                    <input type="text">
	                    <input type="text">
	                    <input type="text">
	                </div>
	            </div>
	            <div class="row2_con_01">적성검사만료</div>
	            <div class="row2_con_back">
	                <div id="row2_con_num02">
	                    <input type="text">
	                    <input type="text">
	                    <input type="text">
	                </div>
	            </div>
	            <div class="row2_con_01">면허발급일</div>
	            <div class="row2_con_back">
	                <div id="row2_con_num03">
	                    <input type="text">
	                    <input type="text">
	                    <input type="text">
	                </div>
	            </div>
	        </div>
	        
	        <div class="row2_con" id="con03">
	            <h2>결제카드</h2>
	            <div id="row2_con04">
	                <a href="#"><div class="con04_card" id="card01">농협카드 (2016.11.15)</div></a>
	                <a href="#"><div class="con04_card" id="card02">BC카드 (2017.01.22)</div></a>
	            </div>
	            <a href="#"><div id="ok_bt">확인</div></a>
	            <a href="#"><div id="card_bt">결제카드 추가</div></a>
	            <a href="#"><div id="ex_bt">탈퇴하기</div></a>
	        </div>
	    </section>
	</div>

</body>
</html>