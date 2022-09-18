package mvc.view;

import java.sql.SQLException;
import java.util.Scanner;

import mvc.controller.AdminController;
import mvc.controller.CartController;
import mvc.controller.CategoryController;
import mvc.controller.CustomerController;
import mvc.controller.GiftConController;
import mvc.controller.GoodsController;
import mvc.controller.OrdersController;
import mvc.dto.Goods;
import mvc.dto.Notice;
import mvc.dto.OrderDetail;
import mvc.dto.Orders;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;

public class MenuView {// 메인 메뉴
	private static Scanner sc = new Scanner(System.in);

	/**
	 * 초기 메뉴
	 */
	public static void menu() { // 초기 메뉴
		while (true) {
			System.out.println("================================== Cafe ===================================");
			AdminController.NoticePrint();
			System.out.println("-------------------------접속 유형을 선택해주세요--------------------------");
			System.out.println("| 1. 회원으로 주문하기 | 2. 비회원으로 주문하기 | 3.  관리자 접속   | 0.  종료   |");
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					MenuView.printMenuForMember();
					break;
				case 2:
					UserSession userSession = new UserSession("Guest"); 
					UserSessionSet userSessionSet = UserSessionSet.getInstance();
					userSessionSet.add(userSession);

					MenuView.printUserMenu("Guest");
					break;
				case 3:
					MenuView.AdminLogin();
					break;
				case 0:
					System.exit(0);
					break;
				default:
					System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}// switch끝
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.menu();
			}
		} // while끝
	} // 초기 메뉴 끝

	/**
	 * 회원 메뉴 초기메뉴(회원주문) -> "회원메뉴"
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
				}// switch끝
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.printMenuForMember();
			}
		} // while끝
	}// 회원메뉴 출력 메소드 끝

	/**
	 * 회원메뉴 switch 사용 메소드 로그인=login(), 아이디찾기=findId(), 비번찾기=findPw(),
	 * 회원가입=register()
	 */
	private static void login() {// 회원로그인
		System.out.print("아이디를 입력해 주세요 >");
		String userId = sc.nextLine();
		System.out.print("비밀번호를 입력해 주세요 >");
		String userPw = sc.nextLine();

		CustomerController.login(userId, userPw);
	}// 회원로그인 메소드 끝

	private static void findId() {// 아이디찾기
		System.out.print("핸드폰 번호를 입력해 주세요 >");
		String phonNum = sc.nextLine();

		CustomerController.findId(phonNum);

	}// 아이디찾기 메소드 끝

	static void findPw() {// 비번찾기
		System.out.print("아이디를 입력해 주세요 >");
		String userId = sc.nextLine();
		System.out.print("핸드폰 번호를 입력해 주세요 >");
		String phonNum = sc.nextLine();

		CustomerController.findPw(userId, phonNum);
	}// 비번찾기 메소드 끝

	private static void register() {// 회원가입
		System.out.print("아이디를 입력해주세요 >");
		String userId = sc.nextLine();

		System.out.print("비밀번호를 입력해 주세요 >");
		String userPw = sc.nextLine();

		System.out.print("닉네임을 입력해 주세요 >");
		String userName = sc.nextLine();

		System.out.print("핸드폰 번호를 입력해 주세요 >");
		String phoneNum = sc.nextLine();

		System.out.print("이메일 주소를 입력해 주세요 >");
		String email = sc.nextLine();

		System.out.print("생년월일을 입력해 주세요 >");
		String pinNum = sc.nextLine();

		int stamp = 0;
		CustomerController.register(userId, userPw, userName, phoneNum, pinNum, email, stamp);
	}// 회원가입 메소드 끝

	
	/**
	 * 회원-마이페이지 초기메뉴 -> 회원메뉴 -> 로그인후 "마이페이지"
	 * @param userId
	 */
	public static void printUserMyPage(String userId) {//회원마이페이지
		
		if(userId.equalsIgnoreCase("Guest")) {
			System.out.println("회원만 사용가능합니다. 로그인 후 이용해주세요.");  
		}else {
		
		//while (userId!=n) {
		while(true) {
			System.out.println("============================== 마이페이지 =================================");
			System.out.println("------------------------- 안녕하세요! " +userId+ "님! ---------------------------");
			System.out.println("| 1. 개인정보 변경 | 2. 최근 주문내역조회 |  3. 주문하기   | 0.  돌아가기   |");
			try {
				int mymenu =Integer.parseInt( sc.nextLine());
				switch(mymenu) {
					case 1: 
						MenuView.userInfoChange(userId);
						break;
					case 2: 
						OrdersController.selectOrdersByUserId(userId);
						break;
					case 3:
						MenuView.printUserMenu(userId);
						break;
					case 0:
						MenuView.printUserMenu(userId);
						break;
					default:
						System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}//switch끝
			}catch (NumberFormatException e){
				//e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.printUserMyPage(userId);
			}
		} // while 끝
	}//else  
}//회원마이페이지 메소드 끝
	

	/**
	 * 마이페이지 switch 사용 메소드 개인정보 변경
	 */
	private static void userInfoChange(String userId) {// 개인정보변경
		System.out.print("개인정보 보호를 위해 비밀번호를 한번 더 입력해 주세요 >");
		String userPw = sc.nextLine();
		CustomerController.userInfoChange(userId, userPw); // 개인정보 보여주기

		System.out.println("============================== 개인정보 변경 =================================");
		System.out.println("| 1. 닉네임 변경 | 2. 핸드폰 번호 변경 | 3. 비밀번호 변경 | 4. 이메일 변경  | 0. 돌아가기 |");
		int menu = Integer.parseInt(sc.nextLine());
		switch (menu) {
		case 1:
			System.out.print("변경하실 닉네임을 입력해주세요 >");
			String userName = sc.nextLine();
			CustomerController.userInfoChangeName(userId, userName); // 닉네임 변경
			break;
		case 2:
			System.out.print("변경하실 핸드폰 번호를 입력해주세요 >");
			String phoneNum = sc.nextLine();
			CustomerController.userInfoChangePhoneNum(userId, phoneNum); // 폰번호 변경
			break;
		case 3:
			System.out.print("변경하실 비밀번호를 입력해주세요 >");
			userPw = sc.nextLine();
			CustomerController.userInfoChangePw(userId, userPw); // 비번 변경
			break;
		case 4:
			System.out.print("변경하실 이메일을 입력해주세요 >");
			String email = sc.nextLine();
			CustomerController.userInfoChangeEmail(userId, email); // 이메일 변경
			break;
		case 0:
			MenuView.printUserMenu(userId);
			break;
		}
	}// 개인정보변경 메소드 끝

	/**
	 * 주문 메뉴 초기메뉴 (회원주문->회원메뉴//비회원주문) -> "주문메뉴(회원/비회원상태)"
	 */
	public static void printUserMenu(String userId) {// 주문메뉴


		while (true) {
			System.out.println("==================================== 메뉴 선택 ========================================");
			System.out.println("------------------------------- 메뉴를 선택해주세요 -----------------------------------");
			System.out.println("|  1. 주문하기   |  2. 장바구니   | 3.기프티콘으로 구매하기 |  4. 마이페이지   |   0. 종료   |");

			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1: // 카테고리 메뉴들 출력
					CategoryController.selectCategory();
					int num = Integer.parseInt(sc.nextLine());
					switch(num) {
					case 1 :
					case 2 :
					case 3 :
					case 4 :
					case 5 :
					case 6 :
					case 7 :GoodsController.selectBever(num, userId);
							break;
					case 8 :System.out.print("상품명을 입력해주세요 > ");
							String word = sc.nextLine();
							GoodsController.goodsSelectByName(word);
							break;
						
					case 9 :MenuView.printUserMenu(userId); 
							break;
					}
					break;
				case 2:
					CartController.viewCart(userId);
					break;
				case 3:
					MenuView.useGiftCon(userId);
					break;
				case 4:
					MenuView.printUserMyPage(userId);
					break;
				case 0:
					System.exit(0);
				default:
					System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}// switch끝
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.printUserMenu(userId);
			}

		} // while 메뉴선택 끝
	}// 주문메뉴 메소드 끝

	/**
	 * 관리자 로그인 메뉴 초기메뉴(관리자주문) ->"관리자 로그인 메뉴" -> 관리자 메뉴
	 */
	public static void AdminLogin() {// 관리자 로그인 메뉴
		System.out.println(">>>>관리자로 로그인하기>>>>");
		System.out.print("아이디 : ");
		String adminId = sc.nextLine();
		System.out.print("비번 : ");
		String adminPw = sc.nextLine();

		AdminController.login(adminId, adminPw);
	}

	/**
	 * 관리자 메뉴
	 * 초기메뉴(관리자주문) -> 관리자 로그인 메뉴(로그인) -> "관리자 메뉴"
	 * @throws SQLException 
	 */
	public static void printMenuForAdmin(String adminId) throws SQLException {//관리자 메뉴
		while(true) {
			//AdminSessionSet ss = AdminSessionSet.getInstance();
			//System.out.println(ss.getSet()); //Set객체 [session]
			System.out.println("================================ 관리자 로그인 중====================================");
			System.out.println("| 1. 상품 등록  | 2. 상품 수정  | 3. 상품삭제  | 4. 판매통계보기  | 5. 공지입력하기  |  0. 종료  |");
			try {
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 : //상품 등록(새로운) 
					MenuView.GoodsInsert();
					break;	
				case 2 : //상품 수정
					MenuView.GoodsUpdate(null);
					break;
				case 3 : //상품삭제
					MenuView.Goodsdelete();
					break;
				case 4 : //판매통계보기
					MenuView.Statistic();
					break;
				case 5 : //공지입력하기
					MenuView.NoticePrint(adminId);
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
    public static void GoodsInsert () {
       System.out.println("--새로운 상품 등록하기 --");
       //System.out.print("상품코드를 입력해주세요 : ");
       //int goodsCode = Integer.parseInt(sc.nextLine());
       
       System.out.print("상품이름을 입력해주세요 : ");
       String goodsName = sc.nextLine();
       
       System.out.print("상품가격을 입력해주세요 : ");
       int goodsPrice = Integer.parseInt(sc.nextLine());
       
       System.out.print("상세설명을 입력해주세요 : ");
       String goodsDetail = sc.nextLine();
       
       System.out.print("품절여부 입력해주세요 : ");
       String SoldOut = sc.nextLine();
       
       System.out.print("재고수량을 입력해주세요 : ");
       int stock = Integer.parseInt(sc.nextLine());
       
       System.out.print("카테고리코드를 입력해주세요 : ");
       int categoryCode =Integer.parseInt(sc.nextLine());
       
       Goods goods =  new Goods(0,goodsName, goodsPrice, goodsDetail,SoldOut,stock,categoryCode);      
       AdminController adminController = new AdminController();
       adminController.GoodsInsert(goods); 
       
    }
	
	
		//상품 수정하기
		private static void GoodsUpdate(Goods goodsCode) {
			while(true) {
				
				System.out.println("--------상품수정하기---------");
				System.out.println(" 1) 상품이름 |  2) 상품가격  |  3) 상세설명 |  4 )재고수량  ");
				try {
					int menu =Integer.parseInt( sc.nextLine());
					switch(menu) {
						case 1 : //상품이름 
						
						MenuView.GoodsUpdateName();		
							break;
						case 2 : //상품가격
							MenuView.GoodsUpdatePr();
							break;
						case 3: //상세설명
							MenuView.GoodsUpdateDi();
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
		public static void Goodsdelete () {
			System.out.println("삭제할 상품코드를 입력해주세요");
			int goodsCode = Integer.parseInt(sc.nextLine());
			AdminController.Goodsdelete(goodsCode);
			
		}  
		//통계조회하기
		public static void Statistic( ) throws SQLException {
		   while(true) {
			   System.out.println("--------통계보기---------");
			   System.out.println(" 1) 일 통계 |  2) 월 통계 ");
			   try {
			   int menu =Integer.parseInt( sc.nextLine());
			   OrderDetail orderDetail = new OrderDetail();
			   switch(menu) {
			   		case 1 : // 일
			   			orderDetail =AdminController.getTodaysTotalOrderDetail();
			   		
						break;	
					case 2 : // 월
						orderDetail =AdminController.getMonthTotalOrderDetail();
						break;	
					default:
						System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
					}//switch끝
				}catch (NumberFormatException e){
					//e.printStackTrace();
					System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
					MenuView.Statistic();
				}
			}//while끝
		}//Statistic1 메소드 끝
		//공지 띄우기
		
		public static void NoticePrint (String adminId) {
			   
			 System.out.println("공지등록하기");
		
		  	 //System.out.println("공지번호를 입력해주세요?");
		  	// int noticeNum = Integer.parseInt(sc.nextLine());
		  	 
		    // System.out.println("작성자를 입력해주세요?");
		  	// String adminId = sc.nextLine();
		  	 
		  	// System.out.println("작성일자");
		  	 //String noticeDate = sc.nextLine();
		  	 
		  	 System.out.println("제목을 입력해주세요");
		  	 String noticeTitel = sc.nextLine();
		  	 
		  	 System.out.println("내용을 입력해주세요?");
		  	 String noticeContent= sc.nextLine();
		
		  	 
		  	 Notice notice =  new Notice(0, adminId, null, noticeTitel, noticeContent);
		  	AdminController.NoticeInsert(notice); 
		   }
		
		//상세수정하기
		public static void GoodsUpdateName() {
			System.out.println("수정 할 상품의 상품코드는?");
			int goodsCode = Integer.parseInt(sc.nextLine());
			System.out.println("변경할 이름");
			String goodsRename = sc.nextLine();
			Goods goods =  new Goods(goodsCode,goodsRename, 0, null,null,0,0);
			AdminController.GoodsUpdateName(goods); 
		
		}
		public static void GoodsUpdatePr() {
			System.out.println("수정 할 상품의 상품코드는?");
			int goodsCode = Integer.parseInt(sc.nextLine());;
			System.out.println("변경할 가격");
			int goodsReprice = Integer.parseInt(sc.nextLine());;
			Goods goods =  new Goods(goodsCode,null, goodsReprice, null,null,0,0);
			AdminController.GoodsUpdatePr(goods); 
		}  
		public static void GoodsUpdateDi() {
			System.out.println("수정 할 상품의 상품코드는?");
			int goodsCode = Integer.parseInt(sc.nextLine());;
			System.out.println("변경할 상세내용");
			String goodsReDi = sc.nextLine();
			Goods goods =  new Goods(goodsCode,null, 0, goodsReDi,null,0,0);
			AdminController.GoodsUpdateDi(goods); 
		}  
		
		
		public static void GoodsUpdateSt() {
			System.out.println("수정 할 상품의 상품코드는?");
			int goodsCode = Integer.parseInt(sc.nextLine());
			System.out.println("변경할 재고량");
			int goodsReSt = Integer.parseInt(sc.nextLine());;
			Goods goods =  new Goods(goodsCode,null, 0, null,null,goodsReSt,0);
			AdminController.GoodsUpdateSt(goods); 
		}
	
	public static void useGiftCon(String userId) {
		System.out.print("사용할 기프티콘 코드를 입력해 주세요 > ");
		String giftCode = sc.nextLine();
		Orders orders = new Orders(0, userId, null, 1, 0, null, "기프티콘", giftCode, null);
		GiftConController.selectGiftCon(orders);
	}
}// 클래스 끝
