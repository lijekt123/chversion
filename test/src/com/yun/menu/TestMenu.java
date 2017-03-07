package com.yun.menu;

import java.util.List;
import java.util.Scanner;

import com.yun.dto.Car;
import com.yun.dto.Member;
import com.yun.dto.Rent;
import com.yun.factory.Factory;
import com.yun.service.CarService;
import com.yun.service.MemberService;
import com.yun.service.RentService;

public class TestMenu {
	Factory factory = new Factory();
	CarService carSer = factory.createCarService(factory.createCarDao());
	MemberService memSer = factory.createMemberService(factory.createMemberDao());
	RentService rentSer = factory.createRentService(factory.createRentDao());

	final int MNGPW = 1234;

	Scanner sc = new Scanner(System.in);
	String loginID;

	public static void main(String[] args) {
		TestMenu menu = new TestMenu();
		menu.intro();
	}

	public void intro() {
		System.out.println("1.사용자메뉴 2.관리자메뉴 3.회원가입 0.EXIT");
		int menu = sc.nextInt();
		switch (menu) {
		case 1:
			int x = login();

			if (x == 1) {
				userMenu();
			} else {
				System.out.println("등록 된 회원이 아닙니다.");
				intro();
			}
			break;

		case 2:
			int y = mnglogin();
			if (y == MNGPW) {
				managerMenu();
			} else {
				System.out.println("잘못 된 PW 입니다.");
				intro();
			}
			break;
		case 3:
			joinUser();
			break;
		case 0:
			System.exit(0);

		default:
			System.out.println("잘못 된 메뉴 입니다");
		}
	}

	private boolean checkId(String inputUserID) {
		int x = memSer.checkId(inputUserID);
		if (x > 0) {
			System.out.println("중복된 아이디 입니다.");
			return true;
		} else {
			return false;
		}
	}

	private void joinUser() {

		String inputUserID, inputUserPW, inputUserName, inputUserPhone, inputUserBirth;
		boolean checkId = false;

		do {
			System.out.println("아이디 입력 : ");
			inputUserID = sc.next();

			checkId = checkId(inputUserID);
		} while (checkId);

		System.out.println("패스워드 입력 : ");
		inputUserPW = sc.next();
		System.out.println("이름 입력 : ");
		inputUserName = sc.next();
		System.out.println("전화번호 입력 : ");
		inputUserPhone = sc.next();
		System.out.println("생년월일(8자리)입력");
		inputUserBirth = sc.next();

		Member member = new Member(inputUserID, inputUserPW, inputUserName, inputUserPhone, inputUserBirth);

		int x = memSer.add(member);
		if (x == 1) {
			System.out.println("회원 가입에 성공하셨습니다.");
			System.out.println("입력 하신 정보는 다음과 같습니다.");
			System.out.println("───────────────────────────────────────────");
			inputMember(inputUserID); // DB테이블에 있는 내용을 출력 해줘야 함 오류
		} else {
			System.out.println("회원 가입에 실패 하셨습니다.");
		}
	}// userJoin()

	private void inputMember(String inputUserID) {
		Member inputMember = memSer.get(inputUserID);

		System.out.println(inputMember.getPassword());
		System.out.println(inputMember.getPassword());
		System.out.println(inputMember.getName());
		System.out.println(inputMember.getPhoneNumber());
		System.out.println(inputMember.getBirth());
		intro();
	}

	private void getMember() { 
		Member inputMember = memSer.get(loginID);

		System.out.println(inputMember.getPassword());
		System.out.println(inputMember.getPassword());
		System.out.println(inputMember.getName());
		System.out.println(inputMember.getPhoneNumber());
		System.out.println(inputMember.getBirth());
		intro();
	}

	private int mnglogin() {

		System.out.println("패스워드를 입력하세요 : ");
		int pw = sc.nextInt();

		return pw;
	}

	public int login() {
		System.out.println("아이디를 입력하세요 : ");
		String id = sc.next();

		System.out.println("패스워드 입력하세요: ");
		String pw = sc.next();
		Member getMember = memSer.get(id);

		if (getMember == null) {
			System.out.println("등록된 아이디가 아닙니다.");
			return -1;
		}

		if (pw.equals(getMember.getPassword())) {
			loginID = id;
			return 1;
		} else
			return -1;

	}

	private void managerMenu() {
		System.out.println("1.회원관리 2.예약관리 3.자동차관리 4.메인으로");
		int input = sc.nextInt();
		switch (input) {
		case 1:
			memberMngMenu();
			break;
		case 2:
			rentMngMenu();
			break;
		case 3:
			carMngMenu();
			break;
		case 4:
			intro();
		default:
			System.out.println("잘못 된 메뉴입니다");
		}
		return;
	}

	private void carMngMenu() {
		System.out.print("1.차량검색 2.차량등록 3.차량삭제 4.차량정보수정 5.돌아가기: ");
		int inputMenu = sc.nextInt();
		switch(inputMenu) {
		case 1:
			searchCar();
			break;

		case 2:
			joinCar();
			break;

		case 3:
			deleteCar();
			break;
			
		case 4:
			updateCar();
			break;
			
		case 5:
			managerMenu();
			break;
		default:
			System.out.println("잘못된메뉴입니다.");
		}

	}

	private void updateCar() {
		String Brand, oil, name, option, category, spot;
		int price;
		String carNumber = getCarList();

		System.out.println("1. 수정하기 2. 돌아가기");
		int input = sc.nextInt();

		if (input == 1) {
			System.out.println("Brand       :  ");
			Brand = sc.next();
			System.out.println("oil         :  ");
			oil = sc.next();
			System.out.println("price       :  ");
			price = sc.nextInt();
			System.out.println("name        :  ");
			name = sc.next();
			System.out.println("option      :  ");
			option = sc.next();
			System.out.println("category    :  ");
			category = sc.next();
			System.out.println("Spot        :  ");
			spot = sc.next();

			System.out.println("입력받은 carNumber : " + carNumber);

			Car car = new Car(carNumber, Brand, oil, price, name, option, category, spot);
			carSer.update(car);
		}
		managerMenu();
	}

	public boolean checkAllCar(List<Car> cars) {

		return cars == null;
	}

	private String getCarList() {
		List<Car> getCars;
		do {
			System.out.println("대여지점 : ");
			String spot = sc.next();
			getCars = carSer.getAllBySpot(spot);		
			if (checkAllCar(getCars)) {					
				System.out.println("존재하지 않는 대여지점입니다");
			}
		} while (checkAllCar(getCars));
		for (int i = 0; i < getCars.size(); i++) {
			System.out.println(getCars.get(i));
		}

		System.out.println("차량번호 입력 : ");
		String modification = sc.next();

		Car modificationCar = carSer.get(modification);
		System.out.println(modificationCar);
		return modification;
	}

	private void deleteCar() {
		
		List<Car> getCars = carSer.getAll();

		for (int i = 0; i < getCars.size(); i++) {
			getCar(getCars.get(i).getCarNumber());
		}

		System.out.println("삭제할 자동차번호를 입력하세요 : ");
		String carNumber = sc.next();
		Car getCar = carSer.get(carNumber);

		if (getCar.getCarNumber().equals(carNumber)) {
			int x = carSer.delete(carNumber);
			if (x == 1) {
				System.out.println("차량삭제성공");
				intro();
			}
		} else {
			System.out.println("자동차 정보 삭제 실패");
			carMngMenu();
		}
	}

	private boolean checkCarID(String inputCarNumber) {
		int x = carSer.checkCarNumber(inputCarNumber);
		if (x > 0) {
			System.out.println("중복된 Car넘버 입니다");
			return true;
		} else {
			return false;
		}
	}

	private void joinCar() {

		String inputCarNumber, inputBrand, inputOil, inputCarName, inputCarOption, inputCarCategory, inputCarSpot;
		int inputHourPrice;
		boolean checkCar;

		do {
			System.out.print("차량번호 입력 : ");
			inputCarNumber = sc.next();
			checkCar = checkCarID(inputCarNumber);
		} while (checkCar);

		System.out.print("차량유형 입력(-small  -mid  -big) : ");
		inputCarCategory = sc.next();

		System.out.print("차량브랜드 입력(-hyundai  -samsung  -chevrolet) : ");
		inputBrand = sc.next();

		System.out.print("차량차종 입력 : ");
		inputCarName = sc.next();

		System.out.print("차량옵션 입력(-silver  -gold   -platinum) : ");
		inputCarOption = sc.next();

		System.out.print("차량연료 입력(-petrol  -diesel  -LPG) : ");
		inputOil = sc.next();

		System.out.print("차량소속지점 입력 : ");
		inputCarSpot = sc.next();

		System.out.println("차량 가격 입력 : ");
		inputHourPrice = sc.nextInt();

		Car car = new Car(inputCarNumber, inputBrand, inputOil, inputHourPrice, inputCarName, inputCarOption,
				inputCarCategory, inputCarSpot);
		int x = carSer.add(car);
		if (x == 1) {
			System.out.println("차량 등록에 성공하셨습니다.");
			System.out.println("입력 하신 정보는 다음과 같습니다.");
			System.out.println("───────────────────────────────────────────");
			getCar(inputCarNumber);
		}
		carMngMenu();
	}

	private void getCar(String inputCarNumber) {
		Car getCar = carSer.get(inputCarNumber);
		System.out.println(getCar);

		System.out.println("Car Number      : " + getCar.getCarNumber());
		System.out.println("Car Brand       : " + getCar.getBrand());
		System.out.println("Car Oil         : " + getCar.getOil());
		System.out.println("Car Price       : " + getCar.getHourPrice());
		System.out.println("Car CarName     : " + getCar.getCarName());
		System.out.println("Car CarOption   : " + getCar.getCarOption());
		System.out.println("Car CarCategory : " + getCar.getCarCategory());
		System.out.println("Car CarSpot     : " + getCar.getCarSpot());
	}

	private void searchCar() {
		System.out.print("1.유형(소.중.대)별 2.브랜드별 3.전체보기 4.돌아가기 : ");
		int searchMenu = sc.nextInt();
		switch (searchMenu) {
		case 1:
			searchCarCategory();			
			break;
		case 2:
			searchCarBrand();
			break;
		case 3:
			searchCarAll();			
			break;
		case 4:
			managerMenu();
		default:
			System.out.println("잘못된메뉴입니다.");
		}
		managerMenu();
	}

	private void searchCarAll() {
		List<Car> getCars = carSer.getAll();
		for (int i = 0; i < getCars.size(); i++) {
			System.out.println(getCars.get(i));
		}		
	}

	private void searchCarBrand() {
		String carBrand;
		System.out.println("Car Brand (-Hyundai -samsung -chevrolet : ");
		carBrand = sc.next();
		List<Car> getCars = carSer.getAll();
		for (int i = 0; i < getCars.size(); i++) {
				if(carBrand.equals(getCars.get(i).getBrand())){
					System.out.println(">>>>> 차량 브랜드별 검색 <<<<<");
					System.out.println("──────────────────────────────────");
					System.out.println(getCars.get(i));
				}else{
					System.out.println("존재 하지 않는 브랜드 입니다.");
					searchCarBrand();
				}
		}
		searchCar();	
	}

	private void searchCarCategory() {
		String carCategory;
		System.out.println("Car Category (-small, -mid, -big) : ");
		carCategory = sc.next();
		List<Car> getCars = carSer.getAll(); 
		
		for (int i = 0; i < getCars.size(); i++) {
			if(carCategory.equals(getCars.get(i).getCarCategory())){ 
				System.out.println(">>>> 차량 유형별 검색 <<<<");
				System.out.println("────────────────────────────────");			
				System.out.println(getCars.get(i));
			}	
		}	
		searchCar();
	}

	private void rentMngMenu() {
		System.out.print("1.예약현황보기 2.지점 별 예약 검색 3.돌아가기: ");
		int inputMenu = sc.nextInt();
		switch (inputMenu) {
		case 1:
			getRent();
			break;
		case 2:
			searchRentBySpot();
			break;
		case 3:
			managerMenu();
		default:
			System.out.println("잘못된 메뉴 입니다");
		}
	}

	private void searchRentBySpot() {
		List<Car> getCars = carSer.getAll();
		System.out.println("지점 입력 :  ");
		String rentSpot=sc.next();
		for (int i = 0; i < getCars.size(); i++) {
			if(getCars.get(i).getCarSpot().equals(rentSpot)){
				System.out.println(getCars.get(i));
			}			
		}
	}

	private void getRent() {
		List<Rent> getRents = rentSer.getAll();
		for (int i = 0; i < getRents.size(); i++) {
			System.out.println(getRents.get(i));			
		}		
	}

	private void memberMngMenu() {
		System.out.print("1.회원수조회 2.회원리스트출력 3.돌아가기: ");
		int inputMenu = sc.nextInt();
		switch (inputMenu) {
		case 1:
			System.out.println("가입된 회원 수 : " + memSer.getCount());
			memberMngMenu();
		case 2:
			System.out.println(" >>>>>>>>>> 회원리스트 <<<<<<<<<<< ");
			List<Member> getMembers = memSer.getAll();

			for (int i = 0; i < getMembers.size(); i++) {
				System.out.println(getMembers.get(i));
			}
			managerMenu();
			break;
		case 3:
			managerMenu();
		default:
			System.out.println("잘못된 메뉴입니다.");
			break;
		}
	}

	private void userMenu() {
		System.out.println("1.회원정보수정 2.회원탈퇴 3.렌트예약 4.메인으로");
		int input = sc.nextInt();
		switch (input) { 
		case 1:
			updateMember();
			break;			
		case 2:
			deleteMember();
			break;			
		case 3:
			rentReservation();
			break;
		case 4:
			intro();
		default:
			System.out.println("잘못된 메뉴 입니다");
		}
	}

	private void rentReservation() {
		
		String startDate, endDate, rentSpot, returnSpot;
		int insurance;
		
		System.out.println("대여 일시   :  ");
		startDate = sc.next();
		
		System.out.println("반납 일시   :  ");
		endDate = sc.next();
		
		System.out.println("대여 지점   :  ");
		rentSpot = sc.next();
		
		System.out.println("반납 지점   :  ");
		returnSpot = sc.next();
		
		List<Car> getCars = carSer.getAllBySpot(rentSpot);
		if(getCars != null){
			for(int i=0; i<getCars.size(); i++){
				System.out.println(i+1 + "번" + getCars.get(i));
			}
		}else{
			System.out.println("해당 지점에 자동차가 없습니다.");
		}
		
		System.out.println("번호를 입력하세요  : ");
		int number = sc.nextInt();
		
		Car selectCar = null;
		
		if(getCars.size() <= number){
			System.out.println("번호를 제 입력하세요 : ");
		}else{
			selectCar = getCars.get(number-1);
		}
		
		System.out.println("선택한 자동차 : " + selectCar);
		
		
		System.out.println("보험 여부   :  ");
		insurance = sc.nextInt();
		
		int price = selectCar.totalPrice(startDate, endDate, insurance);
		System.out.println("렌트 비용 : " + price +"원입니다");
		
		Rent rent = new Rent(startDate, endDate, insurance, rentSpot, returnSpot);
	    rentSer.add(rent, loginID, selectCar.getCarNumber());
		intro();	
	}

	private void deleteMember() {
		getMember();
		System.out.print("정말로 탈퇴 하시겠습니까?(y/n) : ");
		String question = sc.next();
		if (question.equals("y")) {
			int x = memSer.delete(loginID);
			if (x == 1) {
				System.out.println("회원 탈퇴에 성공 하셨습니다.");
				intro();
			}
		} else {
			System.out.println("회원 탈퇴에 실패 하셨습니다.");
			userMenu();
		}
	}

	private void updateMember() {
		getMember();

		String password, name, phoneNumber, birth;

		System.out.println("1.수정하기 2.돌아가기");
		int input = sc.nextInt();

		if (input == 1) {
			System.out.print("Member Name  : ");
			password = sc.next();

			System.out.print("Member PW    : ");
			name = sc.next();

			System.out.print("Member Birth : ");
			phoneNumber = sc.next();

			System.out.print("Member Phone : ");
			birth = sc.next();
			Member member = new Member(loginID, password, name, phoneNumber, birth);
			memSer.update(member);

			userMenu();
		}
		userMenu();
	}

}