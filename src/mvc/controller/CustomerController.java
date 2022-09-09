<<<<<<< HEAD
package mvc.controller;

import mvc.view.FailView;
import mvc.view.MenuView;
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
			String catchUserId = customerService.findId(phonNum);
			
	    MenuView.printMenuForMember(); //로그인하러 가기 
		
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
		
		MenuView.printMenuForMember(); //로그인하러 가기
		
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
		int customer = customerService.register(userId, userPw, userName, phoneNum, email, pinNum, 0);
		
		MenuView.printMenuForMember(); //로그인하러 가기 
		
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	
	/**
	  * 비번 인수로 받아 개인정보 변경 
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
			String myStamp = customerService.myStamp(userId);
			
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
	  * 아이디 인수로 받아 내가 쓴 별 보기 
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
	
	
	
	
	
	
	
=======
package mvc.controller;

import mvc.view.FailView;
import mvc.view.MenuView;
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
			String catchUserId = customerService.findId(phonNum);
			
	    MenuView.printMenuForMember(); //로그인하러 가기 
		
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
		
		MenuView.printMenuForMember(); //로그인하러 가기
		
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
		int customer = customerService.register(userId, userPw, userName, phoneNum, email, pinNum, 0);
		
		MenuView.printMenuForMember(); //로그인하러 가기 
		
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	
	/**
	  * 비번 인수로 받아 개인정보 변경 
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
			String myStamp = customerService.myStamp(userId);
			
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
	  * 아이디 인수로 받아 내가 쓴 별 보기 
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
	
	
	
	
	
	
	
>>>>>>> 1af28d95296b0673b837018b49db91bf6fa558cd
