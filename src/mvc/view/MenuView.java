package mvc.view;

import java.util.Scanner;
import mvc.view.MenuView;
import mvc.controller.AdminController;
import mvc.controller.CartController;
import mvc.controller.CategoryController;
import mvc.controller.CustomerController;
import mvc.session.UserSession;
import mvc.controller.CustomerController;
import mvc.session.UserSessionSet;
import mvc.session.UserSession;
import mvc.controller.GoodsController;
import mvc.dto.Category;
import mvc.dto.Goods;
import mvc.session.UserSessionSet;
import mvc.controller.GoodsController;

public class MenuView {//메인 메뉴
	private static Scanner sc = new Scanner(System.in);
	
		/**
		 * 초기 메뉴
		 */
	public static void menu() { //초기 메뉴
		while (true) {
			// UserSessionSet us = UserSessionSet.getInstance();
			// System.out.println(ss.getSet());
			System.out.println("=============================== Cafe ================================");
			System.out.println("-------------------------접속 유형을 선택해주세요--------------------------");
			System.out.println("| 1. 회원으로 주문하기 | 2. 비회원으로 주문하기 | 3.  관리자 접속   | 0.  종료   |");
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
					case 1:
						MenuView.printMenuForMember(); 
						break;
					case 2:
						// 회원이면 printUserMenu(회원ID)
						// 비회원이면 printUserMenu(null)
						MenuView.printUserMenu(null);// 비회원은 바로 메인 메뉴 선택으로
						break;
					case 3:
						MenuView.AdminLogin(null);
						break;
					case 0:
						System.exit(0);
						break;
					default:
						System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}//switch끝
			}catch (NumberFormatException e){
				//e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.menu();
			}
		}//while끝
	} //초기 메뉴 끝

		/**
		 * 회원 메뉴
		 * 초기메뉴(회원주문) -> "회원메뉴"
		 */
	public static void printMenuForMember() { // 1번 회원메뉴 출력 문구 
		while (true) {
			System.out.println("=========================== 회원으로 주문하기 =============================");
			System.out.println("---------------------------메뉴를 선택해주세요------------------------------");
			System.out.println("| 1. 로그인  | 2. 아이디 찾기 | 3. 비밀번호 찾기 | 4. 회원가입 하기 | 0. 돌아가기  |");
			try {
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
					MenuView.register();
					break;
				case 0:
					MenuView.menu();
					break;	
				default:
					System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}//switch끝
			}catch (NumberFormatException e){
				//e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.printMenuForMember();
			}
		} //while끝
	}//회원메뉴 출력 메소드 끝
	
		/**
		 * 회원메뉴 switch 사용 메소드
		 * 로그인=login(), 아이디찾기=findId(), 비번찾기=findPw(), 회원가입=register()
		 */
	private static void login() {//회원로그인
		System.out.print("아이디를 입력해 주세요 >");
		String userId = sc.nextLine();
		System.out.print("비밀번호를 입력해 주세요 >");
		String userPw = sc.nextLine();

		CustomerController.login(userId, userPw);
	}//회원로그인 메소드 끝

	private static void findId() {//아이디찾기
		System.out.print("핸드폰 번호를 입력해 주세요 >"); 
		String phonNum = sc.nextLine();
		
		CustomerController.findId(phonNum);
	}//아이디찾기 메소드 끝
	
	static void findPw() {//비번찾기
		System.out.print("아이디를 입력해 주세요 >"); 
		String userId = sc.nextLine();
		System.out.print("핸드폰 번호를 입력해 주세요 >"); 
		String phonNum = sc.nextLine();
		
		CustomerController.findPw(userId,phonNum);
		//CustomerController.findId(phonNum); //없어도 될듯...?
	}//비번찾기 메소드 끝

	private static void register() {//회원가입
		System.out.print("아이디를 입력해 주세요 >"); 
		String userId = sc.nextLine();
		System.out.print("비밀번호를 입력해 주세요 >"); 
		String userPw = sc.nextLine();
		System.out.print("전화번호를 입력해 주세요 >");
		String phonNum = sc.nextLine();
		
		CustomerController.register(userId,userPw,phonNum);
	}//회원가입 메소드 끝
	/**
	 * 회원-마이페이지
	 * 초기메뉴 -> 회원메뉴 -> 로그인후 "마이페이지"
	 */
	public static void printUserMyPage(String userId) {//회원마이페이지
		while (true) {
			System.out.println("============================== 마이페이지 =================================");
			System.out.println("-------------------반갑습니다." +userId+ "님 !----------------------");
			System.out.println("| 1. 개인정보 변경 | 2. 최근 주문내역조회 | 3. 나만의 메뉴 | 4. 스탬프 조회  |"
						   + "\n|  5. 쿠폰조회    | 6. 등록한 리뷰보기  |  9. 주문하기   | 0.  종료하기   |");
			try {
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
						MenuView.printUserMenu(userId);
						break;
					case 0:
						System.exit(0);
						break;
					default:
						System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}//switch끝
			}catch (NumberFormatException e){
				//e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.printUserMyPage(userId);
			}
		}//while 끝
	}//회원마이페이지 메소드 끝
		/**
		 * 주문 메뉴
		 * 초기메뉴 (회원주문->회원메뉴//비회원주문) ->  "주문메뉴(회원/비회원상태)"
		 */
	public static void printUserMenu(String userId) {//주문메뉴
		while (true) {
			//UserSessionSet us = UserSessionSet.getInstance();
			//System.out.println(us.getSet()); //Set객체
			System.out.println("================================== 메뉴 선택 =====================================");
			System.out.println("-------------------------------메뉴를 선택해주세요-----------------------------------");
			System.out.println("|  1. 주문하기   |  2. 장바구니   | 3.기프티콘으로 구매하기 |  4. 마이페이지   |   0. 종료   |");
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1 : //카테고리 메뉴들 출력
					CategoryController.selectCategory();
					int num = Integer.parseInt(sc.nextLine());
					GoodsController.selectBever(num, userId);
					
					
				case 2:
					CartController.viewCart(userId);
					break;
				case 3: // 기프티콘으로 구매하기
					
				case 4:
					CustomerController.myPage(userId);
					break;
				case 0:
					System.exit(0);				
				default:
					System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}//switch끝
			}catch (NumberFormatException e){
				//e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.printUserMenu(userId);
			}
		}//while 메뉴선택 끝
	}//주문메뉴 메소드 끝
	
	/**
	 * 관리자 로그인 메뉴
	 * 초기메뉴(관리자주문) ->"관리자 로그인 메뉴" -> 관리자 메뉴
	 */
	public static void AdminLogin(String adminId) {//관리자 로그인 메뉴
		System.out.println(">>>>관리자로 로그인하기>>>>");
		 System.out.print("아이디 : ");
		 String adminIn = sc.nextLine();
		 System.out.print("비번 : ");
		 String adminPw = sc.nextLine();
		 
		 AdminController.login(adminId, adminPw); 
	}
	
		/**
		 * 관리자 로그인 메뉴
		 * 초기메뉴(관리자주문) -> 관리자 로그인 메뉴(로그인) -> "관리자 메뉴"
		 */
	public static void printMenuForAdmin(String adminId) {//관리자 메뉴
		while(true) {
			//AdminSessionSet ss = AdminSessionSet.getInstance();
			//System.out.println(ss.getSet()); //Set객체 [session]
			System.out.println("================================ 관리자 로그인 중====================================");
			System.out.println("| 1. 상품 등록  | 2. 상품 수정  | 3. 상품삭제  | 4. 판매통계보기  | 5. 공지입력하기 | 0. 종료  |");
			try {
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
					MenuView.Statistic1(adminId);
					break;
				case 5 : //공지입력하기
					MenuView.NoticePrint();
					break;
				case 0:
					System.exit(0);
				default:
					System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}//switch끝
			}catch (NumberFormatException e){
				//e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.printMenuForAdmin(adminId);
			}
		}//while 끝
	}//관리자메뉴 메소드 끝
	//상품등록하기
	public static void GoodsInsert (String adminId) {
		System.out.println("--새로운 상품 등록하기 --");
		System.out.print("상품코드를 입력해주세요 : ");
		String goodsCode = sc.nextLine();
		
		System.out.print("상품이름을 입력해주세요 : ");
		String goodsName = sc.nextLine();
		
		System.out.print("상품가격을 입력해주세요 : ");
		int goodsPrice = Integer.parseInt(sc.nextLine());
		
		System.out.print("상세설명을 입력해주세요 : ");
		int goodsDetail = Integer.parseInt(sc.nextLine());
		
		System.out.print("품절여부를 입력해주세요 : ");
		String soldOut = sc.nextLine();
		
		System.out.print("재고수량을 입력해주세요 : ");
		int stock = Integer.parseInt(sc.nextLine());
		
		System.out.print("카테고리코드를 입력해주세요 : ");
		String categoryCode =sc.nextLine();
		
		Goods goods =  new Goods(0, "goodsName", 0, "soldOut","stock",0,0);
	}
	//상품 수정하기
	private static void GoodsUpdate(String goodsCode) {
		while(true) {
			System.out.println("--------상품수정하기---------");
			System.out.println(" 1) 상품이름 |  2) 상품가격  |  3) 품절여부 |  4 )재고수량 ");
			try {
				int menu =Integer.parseInt( sc.nextLine());
				switch(menu) {
					case 1 : //상품이름 
						MenuView.GoodsUpdateName();
						break;
					case 2 : //상품가격
						MenuView.GoodsUpdatePr();
						break;
					case 3 : //품절여부
						MenuView.GoodsUpdateSo();
						break;
					case 4 : //재고수량
						MenuView.GoodsUpdateSt();
						break;	
					default:
						System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
					}//switch끝
				}catch (NumberFormatException e){
					//e.printStackTrace();
					System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
					MenuView.GoodsUpdate(goodsCode);
				}
			}//while끝
		}//GoodsUpdate 메소드 끝
	//상품 삭제하기
	public static void Goodsdelete (String goodsCode) {
		AdminController.Goodsdelete();
		 int gcode = Integer.parseInt(sc.nextLine());
	}  
	//통계조회하기
	public static void Statistic1(String message) {
	   while(true) {
		   System.out.println("--------상품수정하기---------");
		   System.out.println(" 1) 일 통계 |  2) 월 통계 ");
		   try {
		   int menu =Integer.parseInt( sc.nextLine());
		   switch(menu) {
		   		case 1 : // 일
		   			AdminController.DayStatistic();
					break;	
				case 2 : // 월
					AdminController.MonStatistic();
					break;	
				default:
					System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}//switch끝
			}catch (NumberFormatException e){
				//e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.Statistic1(message);
			}
		}//while끝
	}//Statistic1 메소드 끝
	//공지 띄우기
	
	public static void NoticePrint () {
		   
		 System.out.println("공지등록하기");
	
	  	 System.out.println("공지번호를 입력해주세요?");
	  	 int noticeNum = Integer.parseInt(sc.nextLine());
	  	 
	  	 System.out.println("작성자를 입력해주세요?");
	  	 String adminId = sc.nextLine();
	  	 
	  	 System.out.println("작성일자");
	  	 String noticeDate = sc.nextLine();
	  	 
	  	 System.out.println("제목을 입력해주세요");
	  	 String noticeTitel = sc.nextLine();
	  	 
	  	 System.out.println("내용을 입력해주세요?");
	  	 String noticeContent= sc.nextLine();
	
	  	 
	  	 //Notice notice =  new Notice(0, "adminId", "noticeDate", "noticeTitel", "noticeContent");
	   }
	
	//상세수정하기
	public static void GoodsUpdateName() {
		System.out.println("수정 할 상품의 상품코드는?");
		String goodsCode = sc.nextLine();
	}
	public static void GoodsUpdatePr() {
		System.out.println("수정 할 상품의 상품코드는?");
		String goodsCode = sc.nextLine();
	}     
	public static void GoodsUpdateSo() {
		System.out.println("수정 할 상품의 상품코드는?");
		String goodsCode = sc.nextLine();
	} 
	public static void GoodsUpdateSt() {
		System.out.println("수정 할 상품의 상품코드는?");
		String goodsCode = sc.nextLine();
	  
	}

}//클래스 끝