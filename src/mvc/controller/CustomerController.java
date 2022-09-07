package mvc.controller;

import mvc.view.FailView;
import mvc.view.MenuView;
import mvc.dto.Customer;
import mvc.service.CustomerService;

public class CustomerController {
	static CustomerService customerService = new CustomerService();

	/**
	 * 아이디, 비번 인수로 받아 로그인  
	 */
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
	 * 폰번호 인수로 받아 아이디 찾기 
	 */
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
	  * 아이디, 폰번호 인수로 받아 비밀번호 찾기 
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
	  * 아이디, 비번, 폰번호 인수로 받아 회원가입
	  * */
	public static void register(String userId, String userPw, String phonNum) {
		try {
		int customer = customerService.register(userId,userPw,phonNum);
		
		MenuView.printMenuForMember();
		
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	
	/**
	  * 아이디 인수로 받아 개인정보 변경 
	  * */
	public static void userInfoChange(String userId) {
		try {
		int customer = customerService.userInfoChange(userId);
		
		MenuView.printMenuForMember();
		
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	  * 아이디 인수로 받아 최근주문내역 조회 
	  * */
	public static void selectOrderRecent(String userId) {
		try {
			Customer customer = customerService.selectOrderRecent(userId);
			
			MenuView.printMenuForMember();
			
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
		
	}

	/**
	  * 아이디 인수로 받아 나만의 메뉴 
	  * */
	public static void myMenu(String userId) {
		try {
			Customer customer = customerService.myMenu(userId);
			
			MenuView.printMenuForMember();
			
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
	}

	/**
	  * 아이디 인수로 받아 스탬프 조회 
	  * */
	public static void myStamp(String userId) {
		try {
			Customer customer = customerService.myStamp(userId);
			
			MenuView.printMenuForMember();
			
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
	}

	/**
	  * 아이디 인수로 받아 쿠폰 조회 
	  * */
	public static void myCp(String userId) {
		try {
			Customer customer = customerService.myCp(userId);
			
			MenuView.printMenuForMember();
			
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
	}
	
	/**
	  * 아이디 인수로 받아 내가 쓴 리뷰보기 
	  * */
	public static void myStar(String userId) {
		try {
			Customer customer = customerService.myStar(userId);
			
			MenuView.printMenuForMember();
			
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
	}
	
	
	
	
	
	
	
	
	
}
