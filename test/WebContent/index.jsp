<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/test/css/index.css">


<script type="text/javascript">
	
</script>
</head>
<body>
<!--------------------------------------------메인 화면 메뉴 -------------------------------------------->
    <div id="top_wrap">

        <header>
            <h1 id="title"><span id="teamName">TUTICUS</span> RentCar</h1>
        </header>

        <nav>
            <ul id="menu">
                <li class="dropdown"><a href="#">HOME</a>
                </li>
                <li class="dropdown"><a href="#login_wrap">로그인</a>
                </li>
                <li class="dropdown"><a href="#join_wrap">회원가입</a>
                </li>
                <li class="dropdown"><a href="#reserve_box">예약하기</a>
                </li>
                <li class="dropdown"><a href="#login_wrap">예약확인</a>
            </ul>
        </nav>
        <!-------------------------------------------빠른 예약------------------------------------------->
        <div id="quick">
            <ul>
                <li class="quick_menu"><span id="rentDate">대여일자</span>
                    <input class="select" type="date">
                </li>
                <li class="quick_menu">반납일자
                    <input class="select" type="date">
                </li>
                <li class="quick_menu">대여지점
                    <select class="select">
                        <option value="seoul">서울</option>
                        <option value="daejeon">대전</option>
                        <option value="daegu">대구</option>
                        <option value="pusan">부산</option>
                        <option value="gwangju">광주</option>
                    </select>
                </li>
                <li class="quick_menu">반납지점
                    <select class="select">
                        <option value="seoul">서울</option>
                        <option value="daejeon">대전</option>
                        <option value="daegu">대구</option>
                        <option value="pusan">부산</option>
                        <option value="gwangju">광주</option>
                    </select>
                </li>
                <li class="quick_menu">
                    <button id="quick_btn">빠른예약</button>
                </li>
            </ul>
        </div>
    </div>

    <!---------------------------------------------- 로 그 인 ---------------------------------------------->
    <aside>
        <img class="fixed" src="/test/img/home_img.jpg">
        <h1><span id="welcome">WELCOME TO<br></span><span id="tuticus">TUTICUS RENTCAR</span></h1>
        <div id="login_wrap">
            <div>
                <fieldset class="login_form">
                    <legend class="blind">로그인</legend>
                    <div class="input_row" id="id_area">
                        <span class="input_box">
                                                    <a>
                        <input type="submit" title="로그인" alt="로그인" tabindex="12" value="로그인" class="btn_global" onclick="#">
                        </a>
						<label for="id" id="label_id_area" class="lbl" style='display:none' >아이디</label>
						<input type="text" id="id" name="id" tabindex="7" accesskey="L" placeholder="아이디" class="int" maxlength="41" value=""><br/>

				        <label for="pw" id="label_pw_area"  class="lbl" style='display:none'>비밀번호</label>
						<input type="password" id="pw" name="pw" tabindex="8" placeholder="비밀번호" class="int" maxlength="16">

                    </span>
                    </div>

                    <br/>

                    <div class="check_info">
                        <a target="_blank" href="#" onclick="#">아이디 찾기</a> <span class="bar">|</span>
                        <a target="_blank" href="#" onclick="#">비밀번호 찾기</a> <span class="bar">|</span>
                        <a target="_blank" href="#join_wrap">회원가입</a>
                    </div>
                </fieldset>
            </div>
        </div>
    </aside>

    <!--------------------------------------------- 회원가입 --------------------------------------------->
    
    <form action="joinMember.do" method="post">
	    <div id="join_wrap">
	        <table class="join_table">
	            <tr>
	                <td class="join_menu">아이디</td>
	                <td class="join_input">
	                    <input type="text" name="id"><span id="id1">※최소 3자이상 입력 하세요</span>
	                    <br><span id="id2">영문자, 숫자, _만 입력가능. 최소 3자이상 입력 하세요</span>
	                </td>
	            </tr>
	            <tr>
	                <td class="join_menu">패스워드</td>
	                <td class="join_input">
	                    <input type="password" name="password">
	                </td>
	            </tr>
	            <tr>
	                <td class="join_menu">패스워드확인</td>
	                <td class="join_input">
	                    <input type="password" name="passwordCheck">
	                </td>
	            </tr>
	            <tr>
	                <td class="join_menu">이름</td>
	                <td class="join_input">
	                    <input type="text" name="name">(공백없이 한글만입력 가능)
	                </td>
	            </tr>
	            <tr>
	                <td class="join_menu">생년월일</td>
	                <td class="join_input">
	                    <input type="date" name="birth">
	                </td>
	            </tr>
	            <tr>
	                <td class="join_menu">핸드폰번호</td>
					
	                <td class="join_input">
	                    <input type="text" name="phoneNumber" placeholder="000-000-000">
	                </td>
	            </tr>
	        </table>
	        <button>회원가입</button>
	    </div>
    </form>
    
    <!---------------------------------------------- 예약하기 ---------------------------------------------->
    <div id="reserve_box">
        <table class="style_col_01">
            <tbody>
                <tr>
                    <td>
                        <div class="mb20">
                            <p class="brown_txt mb10"><strong>대여일시</strong></p>
                            <br/>
                            <p>
                                <input name="sDate_" title="대여일 선택" type="date" brith="birth" />
                            </p>
                            <p>
                                <select name="sHour" id="sHour" title="대여시간" style="width: 106px;" class="customSelect customSelect3">
                                    <option value="00">00시</option>
                                    <option value="01">01시</option>
                                    <option value="02">02시</option>
                                    <option value="03">03시</option>
                                    <option value="04">04시</option>
                                    <option value="05">05시</option>
                                    <option value="06">06시</option>
                                    <option value="07">07시</option>
                                    <option value="08">08시</option>
                                    <option value="09">09시</option>
                                    <option value="10">10시</option>
                                    <option value="11">11시</option>
                                    <option value="12">12시</option>
                                    <option value="13">13시</option>
                                    <option value="14">14시</option>
                                    <option value="15">15시</option>
                                    <option value="16">16시</option>
                                    <option value="17">17시</option>
                                    <option value="18">18시</option>
                                    <option value="19">19시</option>
                                    <option value="20">20시</option>
                                    <option value="21">21시</option>
                                    <option value="22">22시</option>
                                    <option value="23">23시</option>
                                </select>
                            </p>
                        </div>
                        <div class="mb20">
                            <p class="brown_txt mb10"><strong>반납일시</strong></p>
                            <br/>
                            <p>
                                <input name="sDate_" title="대여일 선택" type="date" brith="birth" />
                            </p>
                            <p>
                                <select name="eHour" id="eHour" title="반납시간" style="width: 106px;" class="customSelect customSelect3">
                                    <option value="00">00시</option>
                                    <option value="01">01시</option>
                                    <option value="02">02시</option>
                                    <option value="03">03시</option>
                                    <option value="04">04시</option>
                                    <option value="05">05시</option>
                                    <option value="06">06시</option>
                                    <option value="07">07시</option>
                                    <option value="08">08시</option>
                                    <option value="09">09시</option>
                                    <option value="10">10시</option>
                                    <option value="11">11시</option>
                                    <option value="12">12시</option>
                                    <option value="13">13시</option>
                                    <option value="14">14시</option>
                                    <option value="15">15시</option>
                                    <option value="16">16시</option>
                                    <option value="17">17시</option>
                                    <option value="18">18시</option>
                                    <option value="19">19시</option>
                                    <option value="20">20시</option>
                                    <option value="21">21시</option>
                                    <option value="22">22시</option>
                                    <option value="23">23시</option>
                                </select>
                            </p>
                        </div>
                    </td>
                    <td>
                        <div class="re_area">
                            <p class="brown_txt mb5" style="position:relative; height:30px;">
                                <b>대여 지점</b>
                                <!-- 20150508 추가 -->

                            </p>

                            <p class="select_place mb5" id="rent_set2">
                                <select id="selRentBranch" title="대여지점" style="width: 218px;text-overflow:ellipsis;white-space:nowrap;word-wrap:normal;" class="customSelect customSelect3 selectRentBranch">
                                    <option value="">대여지점선택</option>
                                    <option value="seoul">서울</option>
                                    <option value="daejeon">대전</option>
                                    <option value="daegu">대구</option>
                                    <option value="pusan">부산</option>
                                    <option value="gwangju">광주</option>
                                </select>
                            </p>

                            <p class="brown_txt mb5"><b>반납 지점</b></p>
                            <p class="select_place mb5" id="return_set2">
                                <select id="selReturnBranch" title="반납지점" style="width: 218px;text-overflow:ellipsis;white-space:nowrap;word-wrap:normal;" class="customSelect customSelect3">
                                    <option>반납지점선택</option>
                                    <option value="seoul">서울</option>
                                    <option value="daejeon">대전</option>
                                    <option value="daegu">대구</option>
                                    <option value="pusan">부산</option>
                                    <option value="gwangju">광주</option>
                                </select>
                            </p>
                            <ul class="txt_green">
                                <li>ㆍ예약 시 해당지점 영업시간 확인 바랍니다.</li>
                                <li>ㆍ대여 및 반납지점 선택 시 활성화 되어 있지
                                    <br/>않은 지점은 대여가 불가합니다.</li>
                            </ul>
                        </div>

                    </td>

                    <td class="last">
                        <div id="mysteryGubun" class="mCont">
                            <div class="mb5">
                                <p class="brown_txt mb5"><b>자동차 유형</b></p>
                                <select name="carSize" id="carSize" title="자동차 유형 선택" style="width: 230px;" class="customSelect customSelect3">
                                    <option value="">자동차 유형을 선택하세요</option>
                                    <option value="">경차</option>
                                    <option value="">소형</option>
                                    <option value="">중형</option>
                                    <option value="">대형</option>
                                    <option value="">승합</option>
                                    <option value="">SUV/RV</option>
                                    <option value="">수입차</option>
                                </select>
                            </div>
                            <div class="car_brand_Box">
                                <ul id="carListArea">
                                </ul>
                            </div>
                        </div>
                    </td>

                    <!-- 브랜드 무조건 전체 조회  -->
                    <input type="hidden" name="brandCode" id="brandCode" value="ALL" />
                </tr>
                <td>
                    <div class="result_Box">
                        <ul>
                            <li><b class="red_txt">대여 :</b> <span id="rentDateView"></span></li>
                            <li><b class="red_txt">반납 :</b> <span id="returnDateView"></span></li>
                        </ul>
                    </div>
                </td>
                <td>
                    <div class="car_coverage_Box">
                        <input id="coverage_check" type="checkbox" name="MAILING" value="male">
                        <p>보험 가입을 하겠습니까?</p>
                        <p>(가입시 체크)</p>
                    </div>
                </td>
                <td>
                    <div class="car_moneysum_Box">
                        <ul id="carMoneysum">
                            <a>￦ :</a>
                        </ul>
                    </div>
                </td>
            </tbody>
        </table>
    </div>
    <article>웰컴</article>
    <article>소개글</article>
    <!---------------------------------------------- 바 닥 글 ---------------------------------------------->
    <footer>
        <p>Tuticus RentCar 2016 </p>
    </footer>
</body>
</html>