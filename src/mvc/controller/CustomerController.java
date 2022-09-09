<<<<<<< HEAD
=======
package mvc.controller;

import mvc.view.EndView;
import mvc.view.FailView;
import mvc.view.MenuView;

import java.sql.SQLException;

import mvc.dto.Customer;
import mvc.service.CustomerService;

public class CustomerController {
	static CustomerService customerService = new CustomerService();
//컨드롤러에서는 성공했을 때 어떻게 나와야하는지 여부 -> view로간다/failview로 간다 
	
	
	/**
	 * 아이디, 비번 인수로 받아 로그인  
	 */
	public static void login(String userId, String userPw) {
		try {
		Customer customer = customerService.login(userId, userPw);
		
		MenuView.printUserMenu(userId);//성공 로그인 메뉴고르러 가기  
		
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
			String catchUserId = customerService.findId(phonNum);
			
	    MenuView.printMenuForMember(); //성공 로그인하러 가기  
		
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
		String catchUserPw = customerService.findPw(userId, phonNum);
		
		MenuView.printMenuForMember(); //성공 로그인하러 가기  
		
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	  * 아이디, 비번, 닉네임, 폰번호, 주민번호 인수로 받아 회원가입
	  * */
	public static void register(String userId, String userPw, String userName, String phoneNum, String pinNum, String email,
			int stamp) {
		try {
		int customer = customerService.register(userId, userPw, userName, phoneNum, email, pinNum, stamp);
		
		
		MenuView.printMenuForMember(); //성공 로그인하러 가기 
		
		}catch(Exception e) {
			//e.printStackTrace();
			
			//FailView.errorMessage(e.getMessage());
			System.out.println("--이미 등록된 회원입니다.--");  

		}
	}

	
	/**
	  * 마이페이지  
	  * 비번 인수로 받아 개인정보 변경 
	  * */
	public static void userInfoChange(String userPw) {
		try {
		int customer = customerService.userInfoChange(userPw);
		
		EndView.userInfoChange(customer);
		
		}catch(Exception e) {
			//e.printStackTrace();
		
			FailView.errorMessage(e.getMessage());
			//System.out.println("--비밀번호 오류입니다.--");
			
		}

	}

	/** 
	 *  마이페이지 
	  * 아이디 인수로 받아 최근주문내역 조회 
	  * */
	public static void selectOrderRecent(String userId) {
		try {
			Customer customer = customerService.selectOrderRecent(userId);
			
			EndView.selectOrderRecent(customer);
			
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
		
	}

	/** 
	  * 마이페이지 
	  * 아이디 인수로 받아 나만의 메뉴 
	  * */
	public static void myMenu(String userId) {
		try {
			Customer customer = customerService.myMenu(userId);
			
			EndView.myMenu(customer);
			
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
	}

	/**
	  * 마이페이지  
	  * 아이디 인수로 받아 스탬프 조회 
	  * */
	public static void myStamp(String userId) {
		try {
			int myStamp = customerService.myStamp(userId);
			
			EndView.myStamp(myStamp);
			
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
	}

	
	/**
	  * 아이디 인수로 받아 내가 쓴 별 보기 
	  * */
	public static void myStar(String userId) {
		try {
			int myStar = customerService.myStar(userId);
			
			EndView.myStar();
			
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
	}

	/**
	  * 아이디 인수로 받아 마이페이지가기 
	  * */
	public static void myPage(String userId) {
		try {
			Customer customer = customerService.myPage(userId);
			
			MenuView.printUserMyPage(userId);//아이디가 있어야 마이페이지감  

			}catch (Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			
			}
		
	}
	
}
	
	
	
	
	
	
	
>>>>>>> da39303894f84cf203d021d702f2bf752d5c6d83
