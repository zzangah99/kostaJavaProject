package mvc.controller;

import mvc.view.EndView;
import mvc.view.FailView;
import mvc.view.MenuView;

import java.sql.SQLException;
import mvc.dto.Customer;
import mvc.service.CustomerService;
import mvc.service.OrderService;

public class CustomerController {
	static CustomerService customerService = new CustomerService();
	
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
			EndView.printMessage("================ 환영합니다! 회원가입이 완료되었습니다! d=(´▽｀)=b ================");//성공 
			MenuView.printMenuForMember();//성공
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage("이미 등록된 회원입니다. 다시 입력해주세요. (・̑◡・̑)");
			}
		}
	
	/**
	  * 마이페이지가기 
	  * */
	public static void myPage(String userId) {
		try {
			Customer customer = customerService.myPage(userId);
			if(userId!=null) {
			MenuView.printMenuForMember();//성공 
			}
			}catch (Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
		}
	
	/**
	  * 마이페이지->개인정보 보여주기 
	  * */
	//public static void userInfoChange(String userId, String userPw) {
	public static void userInfoChange(String userId, String userPw) {
		try {
			Customer customer = customerService.userInfoChange(userId, userPw);
			EndView.userInfoChange(userId, customer);//성공
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 마이페이지->개인정보 변경->닉네임 
	 */
	public static void userInfoChangeName(String userId, String userName) {
		try {
			Customer customer = new Customer();
			customerService.userInfoChangeName(userId, userName);
			EndView.printMessage("닉네임이 " +"<"+userName+">"+ "으로 변경되었습니다.");//성공 
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
			EndView.printMessage("휴대폰 번호가 " +"<"+phoneNum+">"+ "으로 변경되었습니다.");//성공 
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
			EndView.printMessage("비밀번호가 " +"<"+userPw+">"+ "으로 변경되었습니다.");//성공 
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
			EndView.printMessage("이메일이 " +"<"+email+">"+ "으로 변경되었습니다.");//성공 
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
}
	
	
	
	
	
	
	
