package mvc.controller;

import mvc.view.FailView;
import mvc.view.MenuView;
import mvc.dto.Customer;
import mvc.service.CustomerService;

public class CustomerController {
	static CustomerService customerService = new CustomerService();

	/**
	  * 로그인
	  * */
	public static void login(String userId, String userPw) {
		try {
		Customer customer = customerService.login(userId, userPw);
		
		MenuView.printUserMenu(userId);//로그인 되면 userId 가지고 메뉴 띄워줌 
		
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	  * 아이디 찾기 
	  * */
	public static void findId(String phonNum) {
		try {
			Customer customer = customerService.findId(phonNum);
			
	    MenuView.printMenuForMember(); //찾은 아이디로 다시 로그인하러 가기 
		
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	  * 비밀번호 찾기 
	  * */
	public static void findPw(String userId, String phonNum) {
		try {
		Customer customer = customerService.findPw(userId,phonNum);
		
		MenuView.printMenuForMember(); //찾은 비번으로 다시 로그인하러 가기
		
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	  * 회원가입하기 
	  * */
	public static void register(String userId, String userPw, String phonNum) {
		try {
		Customer customer = customerService.register(userId,userPw,phonNum);
		
		MenuView.printMenuForMember();
		
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	  * 마이페이지 가기 
	  * */
	public static void myPage(String userId) {
		System.out.println("반갑습니다." +userId+ "님 !");
		
		try {
		Customer customer = customerService.myPage(userId);
		MenuView.printUserMyPage(userId);
		
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	  * 개인정보 변경 
	  * */
	public static void userInfoChange(String userId) {
		// TODO Auto-generated method stub
		
	}

	/**
	  * 최근주문내역 조회 
	  * */
	public static void selectOrderReent(String userId) {
		// TODO Auto-generated method stub
		
	}

	/**
	  * 나만의 메뉴 
	  * */
	public static void myMenu(String userId) {
		// TODO Auto-generated method stub
		
	}

	/**
	  * 스탬프 조회 
	  * */
	public static void myStamp(String userId) {
		// TODO Auto-generated method stub
		
	}

	/**
	  * 쿠폰 조회 
	  * */
	public static void myCp(String userId) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	  * 내가 쓴 리뷰보기 
	  * */
	public static void myReview(String userId) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
}
