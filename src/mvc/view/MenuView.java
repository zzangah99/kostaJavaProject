package mvc.view;

import java.sql.SQLException;
import java.util.Scanner;
import mvc.view.MenuView;
import mvc.controller.AdminController;
import mvc.controller.CartController;
import mvc.controller.CouponController;
import mvc.controller.CategoryController;
import mvc.controller.CouponController;
import mvc.controller.CustomerController;
import mvc.controller.GiftConController;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;
import mvc.session.UserSession;
import mvc.controller.GoodsController;
import mvc.controller.MyStarController;
import mvc.controller.OrdersController;
import mvc.dto.Category;
import mvc.dto.Goods;
import mvc.dto.Notice;
import mvc.dto.Orders;
import mvc.session.UserSessionSet;
import mvc.controller.GoodsController;

public class MenuView {// 메인 메뉴
	private static Scanner sc = new Scanner(System.in);

	/**
	 * 초기 메뉴
	 */
	public static void menu() { // 초기 메뉴
		while (true) {
			// UserSessionSet us = UserSessionSet.getInstance();
			// System.out.println(ss.getSet());
			System.out.println("=============================== Cafe ================================");
			// AdminController adminController = new AdminController();
			// adminController.NoticePrint();
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
					// 비회원이면 printUserMenu("Guest")
					UserSession userSession = new UserSession("Guest"); // 회원 하나하나에 대한 목록
					UserSessionSet userSessionSet = UserSessionSet.getInstance();// 로그인한 사람 모음
					userSessionSet.add(userSession);

					MenuView.printUserMenu("Guest");// 비회원은 바로 메인 메뉴 선택으로
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
	 */
	public static void printUserMyPage(String userId) {// 회원마이페이지
		if (userId.equalsIgnoreCase("Guest")) {
			System.out.println("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		} else {

			while (true) {
				System.out.println("============================== 마이페이지 =================================");
				System.out.println("------------------------- 안녕하세요! " + userId + "님! ---------------------------");
				System.out.println("| 1. 개인정보 변경 | 2. 최근 주문내역조회 | 3. 나만의 메뉴 | 4. 스탬프 조회  |"
						+ "\n|  5. 쿠폰조회    | 6. 등록한 별점보기  |  7. 주문하기   | 0.  종료하기   |");
				try {
					int mymenu = Integer.parseInt(sc.nextLine());
					switch (mymenu) {
					case 1:
						MenuView.userInfoChange(userId);
						break;
					case 2:
						// CustomerController.selectOrderRecent(userId);//문제
						OrdersController.selectOrdersByUserId(userId);
						break;
					case 3:
						CustomerController.myMenu(userId);// 문제
						break;
					case 4:
						CustomerController.myStamp(userId);// 문제
						break;
					case 5:
						CouponController.UserCoupon(userId);// 문제
						break;
					case 6:
						// MyStarController.myStar(userId);//문제
						MenuView.myStar(userId);
						break;
					case 7:
						MenuView.printUserMenu(userId);
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
					MenuView.printUserMyPage(userId);
				}
			} // while 끝
		}
	}// 회원마이페이지 메소드 끝

	/**
	 * 마이페이지 switch 사용 메소드 별점 평가
	 */
	private static void myStar(String userId) {
		// 주문내역을 쭉 보여주고 해당 상품 번호를 클릭해서 별점을 먹인다??
		System.out.println("====== " + userId + "님의 주문내역 =====");
		System.out.println("별점 등록하실 상품코드를 선택해주세요.");
		int menu = Integer.parseInt(sc.nextLine());
		System.out.print("별점을 등록해주세요.(최대 5점)");
		String myStar = sc.nextLine();
		MyStarController.myStar(userId);
	}

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
			// UserSessionSet us = UserSessionSet.getInstance();
			// System.out.println(us.getSet()); //Set객체
			System.out.println("================================== 메뉴 선택 =====================================");
			System.out.println("-------------------------------메뉴를 선택해주세요-----------------------------------");
			System.out.println("|  1. 주문하기   |  2. 장바구니   | 3.기프티콘으로 구매하기 |  4. 마이페이지   |   0. 종료   |");

			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1: // 카테고리 메뉴들 출력
					CategoryController.selectCategory();
					int num = Integer.parseInt(sc.nextLine());
					GoodsController.selectBever(num, userId);
					break;
				case 2:
					CartController.viewCart(userId);
					break;
				case 3: // 기프티콘으로 구매하기
					MenuView.useGiftCon(userId);
					break;
				case 4:
					// CustomerController.myPage(userId);
					MenuView.printUserMyPage(userId);// 마이페이지가기
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
		// System.out.println(adminId);
		System.out.print("비번 : ");
		String adminPw = sc.nextLine();
		// System.out.println(adminPw);

		AdminController.login(adminId, adminPw);
	}

	/**
	 * 관리자 메뉴 초기메뉴(관리자주문) -> 관리자 로그인 메뉴(로그인) -> "관리자 메뉴"
	 * 
	 * @throws SQLException
	 */
	public static void printMenuForAdmin(String adminId) throws SQLException {// 관리자 메뉴
		while (true) {
			// AdminSessionSet ss = AdminSessionSet.getInstance();
			// System.out.println(ss.getSet()); //Set객체 [session]
			System.out.println("================================ 관리자 로그인 중====================================");
			System.out.println("| 1. 상품 등록  | 2. 상품 수정  | 3. 상품삭제  | 4. 판매통계보기  | 5. 공지입력하기 | 0. 종료  |");
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1: // 상품 등록(새로운)
					MenuView.GoodsInsert();
					break;
				case 2: // 상품 수정
					MenuView.GoodsUpdate();
					break;
				case 3: // 상품삭제
					MenuView.Goodsdelete();
					break;
				case 4: // 판매통계보기
					MenuView.Statistic();
					break;
				case 5: // 공지입력하기
					MenuView.NoticePrint();
					break;
				case 0:
					System.exit(0);
				default:
					System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}// switch끝
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.printMenuForAdmin(adminId);
			}
		} // while 끝
	}// 관리자메뉴 메소드 끝
		// 상품등록하기

	public static void GoodsInsert() {
		System.out.println("--새로운 상품 등록하기 --");
		System.out.print("상품코드를 입력해주세요 : ");
		int goodsCode = Integer.parseInt(sc.nextLine());

		System.out.print("상품이름을 입력해주세요 : ");
		String goodsName = sc.nextLine();

		System.out.print("상품가격을 입력해주세요 : ");
		int goodsPrice = Integer.parseInt(sc.nextLine());

		System.out.print("상세설명을 입력해주세요 : ");
		String goodsDetail = sc.nextLine();

		System.out.print("품절여부를 입력해주세요 : ");
		String soldOut = sc.nextLine();

		System.out.print("재고수량을 입력해주세요 : ");
		int stock = Integer.parseInt(sc.nextLine());

		System.out.print("카테고리코드를 입력해주세요 : ");
		int categoryCode = Integer.parseInt(sc.nextLine());

		Goods goods = new Goods(0, goodsName, 0, goodsDetail, soldOut, 0, 0);
		AdminController adminController = new AdminController();
		adminController.GoodsInsert(goods);

	}

	// 상품 수정하기
	private static void GoodsUpdate() {
		while (true) {
			System.out.println("--------상품수정하기---------");
			System.out.println(" 1) 상품이름 |  2) 상품가격  |  3) 품절여부 |  4 )재고수량 ");
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1: // 상품이름
					MenuView.GoodsUpdateName();
					break;
				case 2: // 상품가격
					MenuView.GoodsUpdatePr();
					break;
				case 3: // 품절여부
					MenuView.GoodsUpdateSo();
					break;
				case 4: // 재고수량
					MenuView.GoodsUpdateSt();
					break;
				default:
					System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}// switch끝
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");

				MenuView.GoodsUpdate();
			}
		} // while끝
	}// GoodsUpdate 메소드 끝
	// 상품 삭제하기

	public static void Goodsdelete() {
		AdminController.Goodsdelete(0);
		int goodsCode = Integer.parseInt(sc.nextLine());
	}

	// 통계조회하기
	public static void Statistic() throws SQLException {
		while (true) {
			System.out.println("--------상품수정하기---------");
			System.out.println(" 1) 일 통계 |  2) 월 통계 ");
			try {
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1: // 일
					AdminController.getTodaysTotalOrderDetail();
					break;
				case 2: // 월
					AdminController.getMonthTotalOrderDetail();
					break;
				default:
					System.out.println(">>>>>>메뉴속 번호를 입력해 주세요");
				}// switch끝
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println(">>>>>>잘못된 번호입니다. 숫자를 입력해 주세요");
				MenuView.Statistic();
			}
		} // while끝
	}// Statistic1 메소드 끝
		// 공지 띄우기

	public static void NoticePrint() {

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
		String noticeContent = sc.nextLine();

		Notice notice = new Notice(0, "adminId", "noticeDate", "noticeTitel", "noticeContent");
		AdminController.NoticePrint();
	}

	// 상세수정하기
	public static void GoodsUpdateName() {
		System.out.println("수정 할 상품의 상품코드는?");
		int goodsCode = Integer.parseInt(sc.nextLine());
		System.out.println("변경할 이름");
		String goodsRename = sc.nextLine();
		AdminController.GoodsUpdateName(goodsCode, goodsRename);
	}

	public static void GoodsUpdatePr() {
		System.out.println("수정 할 상품의 상품코드는?");
		int goodsCode = Integer.parseInt(sc.nextLine());
		;
		System.out.println("변경할 가격");
		int goodsReprice = Integer.parseInt(sc.nextLine());
		;
		AdminController.GoodsUpdatePr(goodsCode, goodsReprice);
	}

	public static void GoodsUpdateSo() {
		System.out.println("수정 할 상품의 상품코드는?");
		int goodsCode = Integer.parseInt(sc.nextLine());
		System.out.println("품절여부");
		String goodsReSo = sc.nextLine();
		AdminController.GoodsUpdateSo(goodsCode, goodsReSo);
	}

	public static void GoodsUpdateSt() {
		System.out.println("수정 할 상품의 상품코드는?");
		int goodsCode = Integer.parseInt(sc.nextLine());
		System.out.println("변경할 재고량");
		int goodsReSt = Integer.parseInt(sc.nextLine());
		;
		AdminController.GoodsUpdateSt(goodsCode, goodsReSt);
	}
	
	
	public static void useGiftCon(String userId) {
		System.out.print("사용할 기프티콘 코드를 입력해 주세요 >");
		String giftCode = sc.nextLine();
		Orders orders = new Orders(0, userId, null, 1, 0, null, "기프티콘", giftCode, null);
		GiftConController.selectGiftCon(orders);
		GiftConController.orderByGiftCon(orders);
	}
}// 클래스 끝