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
		System.out.println("1.����ڸ޴� 2.�����ڸ޴� 3.ȸ������ 0.EXIT");
		int menu = sc.nextInt();
		switch (menu) {
		case 1:
			int x = login();

			if (x == 1) {
				userMenu();
			} else {
				System.out.println("��� �� ȸ���� �ƴմϴ�.");
				intro();
			}
			break;

		case 2:
			int y = mnglogin();
			if (y == MNGPW) {
				managerMenu();
			} else {
				System.out.println("�߸� �� PW �Դϴ�.");
				intro();
			}
			break;
		case 3:
			joinUser();
			break;
		case 0:
			System.exit(0);

		default:
			System.out.println("�߸� �� �޴� �Դϴ�");
		}
	}

	private boolean checkId(String inputUserID) {
		int x = memSer.checkId(inputUserID);
		if (x > 0) {
			System.out.println("�ߺ��� ���̵� �Դϴ�.");
			return true;
		} else {
			return false;
		}
	}

	private void joinUser() {

		String inputUserID, inputUserPW, inputUserName, inputUserPhone, inputUserBirth;
		boolean checkId = false;

		do {
			System.out.println("���̵� �Է� : ");
			inputUserID = sc.next();

			checkId = checkId(inputUserID);
		} while (checkId);

		System.out.println("�н����� �Է� : ");
		inputUserPW = sc.next();
		System.out.println("�̸� �Է� : ");
		inputUserName = sc.next();
		System.out.println("��ȭ��ȣ �Է� : ");
		inputUserPhone = sc.next();
		System.out.println("�������(8�ڸ�)�Է�");
		inputUserBirth = sc.next();

		Member member = new Member(inputUserID, inputUserPW, inputUserName, inputUserPhone, inputUserBirth);

		int x = memSer.add(member);
		if (x == 1) {
			System.out.println("ȸ�� ���Կ� �����ϼ̽��ϴ�.");
			System.out.println("�Է� �Ͻ� ������ ������ �����ϴ�.");
			System.out.println("��������������������������������������������������������������������������������������");
			inputMember(inputUserID); // DB���̺� �ִ� ������ ��� ����� �� ����
		} else {
			System.out.println("ȸ�� ���Կ� ���� �ϼ̽��ϴ�.");
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

		System.out.println("�н����带 �Է��ϼ��� : ");
		int pw = sc.nextInt();

		return pw;
	}

	public int login() {
		System.out.println("���̵� �Է��ϼ��� : ");
		String id = sc.next();

		System.out.println("�н����� �Է��ϼ���: ");
		String pw = sc.next();
		Member getMember = memSer.get(id);

		if (getMember == null) {
			System.out.println("��ϵ� ���̵� �ƴմϴ�.");
			return -1;
		}

		if (pw.equals(getMember.getPassword())) {
			loginID = id;
			return 1;
		} else
			return -1;

	}

	private void managerMenu() {
		System.out.println("1.ȸ������ 2.������� 3.�ڵ������� 4.��������");
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
			System.out.println("�߸� �� �޴��Դϴ�");
		}
		return;
	}

	private void carMngMenu() {
		System.out.print("1.�����˻� 2.������� 3.�������� 4.������������ 5.���ư���: ");
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
			System.out.println("�߸��ȸ޴��Դϴ�.");
		}

	}

	private void updateCar() {
		String Brand, oil, name, option, category, spot;
		int price;
		String carNumber = getCarList();

		System.out.println("1. �����ϱ� 2. ���ư���");
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

			System.out.println("�Է¹��� carNumber : " + carNumber);

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
			System.out.println("�뿩���� : ");
			String spot = sc.next();
			getCars = carSer.getAllBySpot(spot);		
			if (checkAllCar(getCars)) {					
				System.out.println("�������� �ʴ� �뿩�����Դϴ�");
			}
		} while (checkAllCar(getCars));
		for (int i = 0; i < getCars.size(); i++) {
			System.out.println(getCars.get(i));
		}

		System.out.println("������ȣ �Է� : ");
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

		System.out.println("������ �ڵ�����ȣ�� �Է��ϼ��� : ");
		String carNumber = sc.next();
		Car getCar = carSer.get(carNumber);

		if (getCar.getCarNumber().equals(carNumber)) {
			int x = carSer.delete(carNumber);
			if (x == 1) {
				System.out.println("������������");
				intro();
			}
		} else {
			System.out.println("�ڵ��� ���� ���� ����");
			carMngMenu();
		}
	}

	private boolean checkCarID(String inputCarNumber) {
		int x = carSer.checkCarNumber(inputCarNumber);
		if (x > 0) {
			System.out.println("�ߺ��� Car�ѹ� �Դϴ�");
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
			System.out.print("������ȣ �Է� : ");
			inputCarNumber = sc.next();
			checkCar = checkCarID(inputCarNumber);
		} while (checkCar);

		System.out.print("�������� �Է�(-small  -mid  -big) : ");
		inputCarCategory = sc.next();

		System.out.print("�����귣�� �Է�(-hyundai  -samsung  -chevrolet) : ");
		inputBrand = sc.next();

		System.out.print("�������� �Է� : ");
		inputCarName = sc.next();

		System.out.print("�����ɼ� �Է�(-silver  -gold   -platinum) : ");
		inputCarOption = sc.next();

		System.out.print("�������� �Է�(-petrol  -diesel  -LPG) : ");
		inputOil = sc.next();

		System.out.print("�����Ҽ����� �Է� : ");
		inputCarSpot = sc.next();

		System.out.println("���� ���� �Է� : ");
		inputHourPrice = sc.nextInt();

		Car car = new Car(inputCarNumber, inputBrand, inputOil, inputHourPrice, inputCarName, inputCarOption,
				inputCarCategory, inputCarSpot);
		int x = carSer.add(car);
		if (x == 1) {
			System.out.println("���� ��Ͽ� �����ϼ̽��ϴ�.");
			System.out.println("�Է� �Ͻ� ������ ������ �����ϴ�.");
			System.out.println("��������������������������������������������������������������������������������������");
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
		System.out.print("1.����(��.��.��)�� 2.�귣�庰 3.��ü���� 4.���ư��� : ");
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
			System.out.println("�߸��ȸ޴��Դϴ�.");
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
					System.out.println(">>>>> ���� �귣�庰 �˻� <<<<<");
					System.out.println("��������������������������������������������������������������������");
					System.out.println(getCars.get(i));
				}else{
					System.out.println("���� ���� �ʴ� �귣�� �Դϴ�.");
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
				System.out.println(">>>> ���� ������ �˻� <<<<");
				System.out.println("����������������������������������������������������������������");			
				System.out.println(getCars.get(i));
			}	
		}	
		searchCar();
	}

	private void rentMngMenu() {
		System.out.print("1.������Ȳ���� 2.���� �� ���� �˻� 3.���ư���: ");
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
			System.out.println("�߸��� �޴� �Դϴ�");
		}
	}

	private void searchRentBySpot() {
		List<Car> getCars = carSer.getAll();
		System.out.println("���� �Է� :  ");
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
		System.out.print("1.ȸ������ȸ 2.ȸ������Ʈ��� 3.���ư���: ");
		int inputMenu = sc.nextInt();
		switch (inputMenu) {
		case 1:
			System.out.println("���Ե� ȸ�� �� : " + memSer.getCount());
			memberMngMenu();
		case 2:
			System.out.println(" >>>>>>>>>> ȸ������Ʈ <<<<<<<<<<< ");
			List<Member> getMembers = memSer.getAll();

			for (int i = 0; i < getMembers.size(); i++) {
				System.out.println(getMembers.get(i));
			}
			managerMenu();
			break;
		case 3:
			managerMenu();
		default:
			System.out.println("�߸��� �޴��Դϴ�.");
			break;
		}
	}

	private void userMenu() {
		System.out.println("1.ȸ���������� 2.ȸ��Ż�� 3.��Ʈ���� 4.��������");
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
			System.out.println("�߸��� �޴� �Դϴ�");
		}
	}

	private void rentReservation() {
		
		String startDate, endDate, rentSpot, returnSpot;
		int insurance;
		
		System.out.println("�뿩 �Ͻ�   :  ");
		startDate = sc.next();
		
		System.out.println("�ݳ� �Ͻ�   :  ");
		endDate = sc.next();
		
		System.out.println("�뿩 ����   :  ");
		rentSpot = sc.next();
		
		System.out.println("�ݳ� ����   :  ");
		returnSpot = sc.next();
		
		List<Car> getCars = carSer.getAllBySpot(rentSpot);
		if(getCars != null){
			for(int i=0; i<getCars.size(); i++){
				System.out.println(i+1 + "��" + getCars.get(i));
			}
		}else{
			System.out.println("�ش� ������ �ڵ����� �����ϴ�.");
		}
		
		System.out.println("��ȣ�� �Է��ϼ���  : ");
		int number = sc.nextInt();
		
		Car selectCar = null;
		
		if(getCars.size() <= number){
			System.out.println("��ȣ�� �� �Է��ϼ��� : ");
		}else{
			selectCar = getCars.get(number-1);
		}
		
		System.out.println("������ �ڵ��� : " + selectCar);
		
		
		System.out.println("���� ����   :  ");
		insurance = sc.nextInt();
		
		int price = selectCar.totalPrice(startDate, endDate, insurance);
		System.out.println("��Ʈ ��� : " + price +"���Դϴ�");
		
		Rent rent = new Rent(startDate, endDate, insurance, rentSpot, returnSpot);
	    rentSer.add(rent, loginID, selectCar.getCarNumber());
		intro();	
	}

	private void deleteMember() {
		getMember();
		System.out.print("������ Ż�� �Ͻðڽ��ϱ�?(y/n) : ");
		String question = sc.next();
		if (question.equals("y")) {
			int x = memSer.delete(loginID);
			if (x == 1) {
				System.out.println("ȸ�� Ż�� ���� �ϼ̽��ϴ�.");
				intro();
			}
		} else {
			System.out.println("ȸ�� Ż�� ���� �ϼ̽��ϴ�.");
			userMenu();
		}
	}

	private void updateMember() {
		getMember();

		String password, name, phoneNumber, birth;

		System.out.println("1.�����ϱ� 2.���ư���");
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