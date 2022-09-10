package mvc.controller;

import mvc.view.EndView;
import mvc.view.FailView;
import mvc.view.MenuView;

import java.sql.SQLException;

import mvc.dto.Customer;
import mvc.dto.MyStar;
import mvc.service.CustomerService;

public class CustomerController {
	static CustomerService customerService = new CustomerService();
//컨드롤러에서는 성공했을 때 어떻게 나와야하는지 여부 -> view로간다/failview로 간다 
	
	/**
	 * 로그인 
	 */
	public static void login(String userId, String userPw) {
		try {
		Customer customer = customerService.login(userId, userPw);
		MenuView.printUserMenu(userId);//성공 
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 아이디 찾기 
	 */
	public static void findId(String phonNum) {
		try {
			String catchUserId = customerService.findId(phonNum);
	    MenuView.printMenuForMember();//성공
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
		String catchUserPw = customerService.findPw(userId, phonNum);
		MenuView.printMenuForMember();//성공
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	  * 회원가입
	  * */
	public static void register(String userId, String userPw, String userName, String phoneNum, String pinNum, String email,
			int stamp) {
		try {
			int customer = customerService.register(userId, userPw, userName, phoneNum, email, pinNum, stamp);
			MenuView.printMenuForMember();//성공
			}catch(Exception e) {
				System.out.println("이미 등록된 회원입니다. 테스트1");  
				//e.printStackTrace();
				//FailView.errorMessage(e.getMessage());
			}
		}
	
	/**
	  * 마이페이지가기 
	  * */
	public static void myPage(String userId) {
		try {
			Customer customer = customerService.myPage(userId);
			if(userId==null) {
			MenuView.printMenuForMember();
			}
			}catch (Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
		}
	
	/**
	  * 마이페이지->개인정보 보여주기 
	  * */
	public static void userInfoChange(String userId, String userPw) {
		try {
			Customer customer = customerService.userInfoChange(userId, userPw);
			EndView.userInfoChange(customer);//성공
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			//MenuView.printUserMyPage(userId);
		}
	}

	/**
	 * 마이페이지->개인정보 변경->닉네임 
	 */
	public static void userInfoChangeName(String userId, String userName) {
		try {
			customerService.userInfoChangeName(userId, userName);
			EndView.printMessage("닉네임 변경에 성공했습니다.");//성공 
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 마이페이지->개인정보 변경->폰번호(not null) 
	 */
	public static void userInfoChangePhoneNum(String userId, String phoneNum) {
		try {
			customerService.userInfoChangePhoneNum(userId, phoneNum);
			EndView.printMessage("핸드폰번호 변경에 성공했습니다.");//성공 
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 마이페이지->개인정보 변경->비번 
	 */
	public static void userInfoChangePw(String userId, String userPw) {
		try {
			customerService.userInfoChangePw(userId, userPw);
			EndView.printMessage("비밀번호 변경에 성공했습니다.");//성공 
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 마이페이지->개인정보 변경->이메일 
	 */
	public static void userInfoChangeEmail(String userId, String email) {
		try {
			customerService.userInfoChangeEmail(userId, email);
			EndView.printMessage("이메일 변경에 성공했습니다.");//성공 
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/** 아직 못함 
	 *  마이페이지->최근주문내역 
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
	
	/** 아직 못함 
	  * 마이페이지->나만의 메뉴보기 
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

	/** 아직 못함  
	  * 마이페이지->스탬프 조회(not null)   
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
	
	
	
	
	
}
	
	
	
	
	
	
	
