package mvc.view;

import java.util.Scanner;
import mvc.view.MenuView;
import mvc.controller.AdminController;
import mvc.controller.CustomerController;
import mvc.session.UserSession;
import mvc.controller.CustomerController;
import mvc.session.UserSessionSet;
import mvc.session.UserSession;
import mvc.controller.GoodsController;

import mvc.session.UserSessionSet;
import mvc.controller.GoodsController;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);

	public static void menu() {
		while (true) {
			//UserSessionSet us = UserSessionSet.getInstance();
			// System.out.println(ss.getSet());

			MenuView.printMenu();

			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				MenuView.printMenuForMember(); // 회원 접속
				break;
			case 2:

				// 회원이면 printUserMenu(회원ID)
				// 비회원이면 printUserMenu(null)
				MenuView.printUserMenu(null);// 비회원은 바로 메인 메뉴 선택으로
				break;
			case 3:
				MenuView.printMenuForAdmin();// 관리자 접속
				break;

			case 9:
				System.exit(0);
			}
		}

	}

	public static void printMenu() {
		System.out.println("=== Cafe ===");
		System.out.println("접속 유형을 선택해주세요");
		System.out.println("1. 회원으로 주문하기   |   2. 비회원으로 주문하기   |  3. 관리자 접속   |  9. 종료");

	}

	public static void printMenuForMember() { // 회원접속
		while (true) {
			System.out.println("=== 회원으로 주문하기 ===");
			System.out.println("메뉴를 선택해주세요");
			System.out.println("1. 로그인   |   2. 아이디 찾기   |  3. 비밀번호 찾기   |  4. 회원가입 하기   |  9. 돌아가기");

			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				MenuView.login();
				break;
			case 2:
				MenuView.findId();
				break;
			case 3:
				MenuView.findPw();
				break;
			case 4:
				MenuView.register(); // 회원가입
				break;
			case 9:
				System.exit(0);
			}

		} // while
	}

	/**
	 * 로그인
	 */
	private static void login() {
		System.out.print("아이디를 입력해 주세요 >");
		String userId = sc.nextLine();

		System.out.print("비밀번호를 입력해 주세요 >");
		String userPw = sc.nextLine();

		CustomerController.login(userId, userPw);
	}

	/**
	 * 아이디 찾기
	 */
	private static void findId() {
		System.out.print("핸드폰 번호를 입력해 주세요 >"); 
		String phonNum = sc.nextLine();
		

		CustomerController.findId(phonNum);
	}

	/**
	 * 비밀번호 찾기
	 */
	private static void findPw() {
		System.out.print("아이디를 입력해 주세요 >"); 
		String userId = sc.nextLine();
		
		System.out.print("핸드폰 번호를 입력해 주세요 >"); 
		String phonNum = sc.nextLine();
		
		CustomerController.findPw(userId,phonNum);
		//CustomerController.findId(phonNum); 없어도 될듯...?

	}

	/**
	 * 회원가입
	 */
	private static void register() {

		System.out.print("아이디를 입력해 주세요 >"); 
		String userId = sc.nextLine();
		
		System.out.print("비밀번호를 입력해 주세요 >"); 
		String userPw = sc.nextLine();
		
		System.out.print("전화번호를 입력해 주세요 >");
		String phonNum = sc.nextLine();
		
		CustomerController.register(userId,userPw,phonNum);

	}


	// 회원/비회원의 메뉴선택
	public static void printUserMenu(String userId) {
		while (true) {
//			UserSessionSet us = UserSessionSet.getInstance();
//			System.out.println(us.getSet()); //Set객체

			System.out.println("=== 메뉴 선택 ===");
			System.out.println("메뉴를 선택해주세요");
			System.out.println("1. 주문하기   |   2. 장바구니   |  3. 기프티콘으로 구매하기   |  4. 마이페이지   |  9. 돌아가기");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1 : //주문하기 
			    System.out.println("1. 커피   |  2. 스무디/프라페   |  3. 에이드/주스   | 4. 병음료   |  5. 티   |  6. 디저트  | 7. MD상품   | 8. 상품이름검색   | 9. 돌아가기  ");
			    int menu2 =Integer.parseInt( sc.nextLine());
					switch(menu2) {
						case 1 :
						case 2 :
						case 3 :
						case 4 :
						case 5 : 
						case 6 : 
						case 7 :
							GoodsController.goodsSelectByCategory(menu2);
							break;
						case 8 :
							MenuView.inputGoodsByName(); //상품이름검색
							break;
						case 9 :
							System.exit(0);
						}
			case 2: // 장바구니
			case 3: // 기프티콘으로 구매하기
			case 4: // 마이페이지
				CustomerController.myPage(null);
				break;

			case 9:
				System.exit(0);

			}
		}
	}//회원/비회원의 메뉴선택
			
	
	/* Admin 메뉴 뷰 */

		public static void printMenuForAdmin() {
			
		while(true) {
			//AdminSessionSet ss = AdminSessionSet.getInstance();
			//System.out.println(ss.getSet()); //Set객체 [session]
			System.out.println("-----" +adminId  + " 관리자 로그인 중 -----");
			System.out.println(" 1) 상품 등록 |  2) 상품 수정  |  3) 상품삭제 |  4 )판매통계보기 |  5) 공지입력하기  ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
	
				case 1 : //상품 등록(새로운) 
					MenuView.GoodsInsert(adminId);
					break;	
					
				case 2 : //상품 수정
					MenuView.GoodsUpdate(adminId);
					break;
					
				case 3 : //상품삭제
					MenuView.Goodsdelete(adminId);
					break;
				case 4 : //판매통계보기
					MenuView.Statistic(adminId);
					break;
					
				case 5 : //공지입력하기
					MenuView.NoticePrint();
					break;
		

		
	      }
	  }
	}
		

	/**
	 * 로그인 메뉴
	 * */

	public static void login() {
		 System.out.print("아이디 : ");
		 String adminIn = sc.nextLine();
		 
		 System.out.print("비번 : ");
		 String adminPw = sc.nextLine();
		 
		 AdminController.login(adminId, adminPw); 
	}
	
	


	//상품등록하기
	public static void GoodsInsert (String id) {
	  AdminController.GoodsInsert();
   }
	//상품 수정하기
	public static void GoodsUpdate (String goodsCode) {
		while(true) {
	
			System.out.println("--------상품수정하기---------");
			System.out.println(" 1) 상품이름 |  2) 상품가격  |  3) 품절여부 |  4 )재고수량 ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
	
				case 1 : //상품이름 
					AdminController.Goodsdelete();
					
					break;	
					
				case 2 : //상품가격
					AdminController.Goodsdelete();
					
					break;
					
				case 3 : //품절여부
					AdminController.Goodsdelete();
					
					break;
				case 4 : //재고수량
					AdminController.Goodsdelete();
					
					break;
	
	      }
	  }
		
				
	}
	
	//상품 삭제하기
	public static void Goodsdelete (String goodsCode) {
		AdminController.Goodsdelete();
    }  
	//통계조회하기
   public static void Statistic(String message) {
	   while(true) {
			
			System.out.println("--------상품수정하기---------");
			System.out.println(" 1) 일 통계 |  2) 월 통계 ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
	
				case 1 : // 일
					AdminController.DayStatistic();
					break;	
					
				case 2 : // 월
					AdminController.MonStatistic();
					break;
			}
	 	
	   }
   }
   
	//공지 띄우기
   
	public static void NoticePrint() {
	
		  AdminController.NoticePrint();
	}

	
	
// admin메뉴 끝
//----------------------------------------------------------------------------------------
	
	

	// printUserMenu

	// 회원 온니 메뉴
	public static void printUserMyPage(String userId) {
		while (true) {
			System.out.println("=== 마이페이지 ===");
			System.out.println("반갑습니다." +userId+ "님 !");
			System.out.println("1. 개인정보 변경   |   2. 최근 주문내역조회   |  3. 나만의 메뉴   |  4. 스탬프 조회   |  5. 쿠폰조회  |  6. 등록한 리뷰보기  |  9. 돌아가기 ");
			int mymenu =Integer.parseInt( sc.nextLine());
			switch(mymenu) {
			case 1: 
				CustomerController.userInfoChange(userId);
				break;
			case 2: 
				CustomerController.selectOrderReent(userId);
				break;
			case 3: 
				CustomerController.myMenu(userId);
				break;
			case 4: 
				CustomerController.myStamp(userId);
				break;
			case 5: 
				CustomerController.myCp(userId);
				break;
			case 6: 
				CustomerController.myReview(userId);
				break;
			case 9:
				System.exit(0);
				
			}
		}
	
	}


	
	public static void inputGoodsByName() {//상품 이름을 검색해서 찾기
		System.out.print("찾을 상품을 검색해주세요> ");
		String word = sc.nextLine();
		GoodsController.goodsSelectByName(word);
	}


	
}
