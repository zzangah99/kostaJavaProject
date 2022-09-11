package mvc.view;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mvc.controller.CartController;
import mvc.controller.OrdersController;
import mvc.dao.GoodsDAO;
import mvc.dao.GoodsDAOImpl;
import mvc.dto.Customer;

import mvc.dto.Category;
import mvc.dto.Goods;
import mvc.dto.MyMenu;
import mvc.dto.MyStar;
import mvc.dto.Option;
import mvc.dto.OrderLine;
import mvc.dto.Orders;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;
import mvc.view.MenuView;

public class EndView {
	static Scanner sc = new Scanner(System.in);

	/**
	 * 대분류 출력
	 */

	public static void printsCategoryList(List<Category> categoryList) {
		for (Category list : categoryList) {
			System.out.print("  " + list.getCategoryCode() + "." + list.getCategoryName() + "   ");

		}
		System.out.println();
		System.out.println();
		System.out.print("주문하실 종류의 번호를 입력해주세요 > "); 
	}

	/**
	 * 메뉴 출력 후 주문
	 */
	public static void printGoodsList(List<Goods> coffeeList) {//그래서 여기 온 순간 이미 카테고리 정해진 굿즈 리스트가 나오는거고
		List<Integer> goodsCodeList = new ArrayList<Integer>();
		String op[] = new String[3];
		int cup = 0;
		String tem = null;
		String userId = null;

		System.out.println("------------- 상품 " + coffeeList.size() + "개 -------------");
		int goodsNo = 0;
		for (Goods goods : coffeeList) {
			System.out.print((++goodsNo) + "." + goods.getGoodsName() + "\t");
			goodsCodeList.add(goods.getGoodsCode());
			userId = goods.getUserId();
			if(userId==null) userId = "Guest";
		}

		// 용식님 메뉴 상세정보 출력하실 위치(영양정보, 메뉴정보 나오는 화면 ...)

		//
		System.out.print("\n주문할 상품 번호를 고르세요 > ");
		int orderNo = Integer.parseInt(sc.nextLine());
		int orderGoodsCode = goodsCodeList.get(orderNo - 1);
		
		if (coffeeList.get(orderNo - 1).getNum() < 6) {// 디저트, md 상품이 아니면 옵션
			System.out.println("선택해주세요");
			System.out.println("1.Hot\t 2.Ice");
			tem = sc.nextLine();
			tem = tem.replace("1", "H");
			tem = tem.replace("2", "I");

			System.out.println("0.없음\t 1.Small\t 2.Regular\t 3.Large");
			cup = Integer.parseInt(sc.nextLine());

			System.out.println("1.시럽 추가\t 2.추가 안 함");
			op[0] = sc.nextLine();
			System.out.println("1.디카페인\t 2.해당 없음");
			op[1] = sc.nextLine();
			System.out.println("1.휘핑 추가\t 2.추가 없음");
			op[2] = sc.nextLine();
			
			for (int i = 0; i < op.length; i++) {
				op[i] = op[i].replace("1", "Y");
				op[i] = op[i].replace("2", "N");
			}
		}
		System.out.print("수량을 입력하세요 >");
		int quan = Integer.parseInt(sc.nextLine());

		System.out.println("1.주문하기\t 2.장바구니에 담기");
		int choice = sc.nextInt();
		System.out.println("- 주문 상품 : " + coffeeList.get(orderNo - 1).getGoodsName() + "\t주문 수량 : " + quan);

		switch (choice) {
		case 1:
			System.out.println("쿠폰을 사용하시겠습니까?\n1.사용\t 2.사용 안 함");
			String cpChoice = sc.nextLine();
			if (cpChoice.equals("1")) {
				System.out.println("사용 가능한 쿠폰 목록");

			}

			System.out.println("\n- 결제 수단 선택");
			System.out.println("1.현금\t 2.카드\t");
			String payment = sc.nextLine();
			payment = payment.replace("1", "현금");
			payment = payment.replace("2", "카드");

			System.out.println("\n테이크 아웃 여부를 선택해주세요");
			System.out.println("1.매장\t 2.포장");
			String takeOut = sc.nextLine();
			takeOut = takeOut.replace("1", "N");
			takeOut = takeOut.replace("2", "Y");

			Orders order = new Orders(0, userId, null, quan, 0, null, payment, null, takeOut);// userId 받아야함
			OrderLine orderline = new OrderLine(0, 0, orderGoodsCode, 0, quan);
			orderline.setGoodsName(coffeeList.get(orderNo - 1).getGoodsName());
			order.getOrderLineList().add(orderline);
			if(orderNo < 6) {
				Option option = new Option(0, cup, null, tem, op[0], op[1], op[2]);
				orderline.getOptionList().add(option);
			}
			
			OrdersController.insertOrders(order);//이후 스탬프 적립 컨트롤러 추가
			break;
		case 2: // 장바구니 담기
			Orders cartOrder = new Orders(0, userId, null, quan, 0, null, null, null, null);// userId 받아야함
			OrderLine cartOrderline = new OrderLine(0, 0, orderGoodsCode, 0, quan);
			
			if(orderNo < 6) {
				Option option = new Option(0, cup, null, tem, op[0], op[1], op[2]);
				cartOrderline.getOptionList().add(option);
			}
			
			cartOrder.getOrderLineList().add(cartOrderline);
			
			CartController.putCart(cartOrderline,userId);
			
			break;
		}

		System.out.println();
	}

	public static void printMessage(String message) {//
		System.out.println(message);
	}

	/**
	 * 장바구니 보기
	 **/
	public static void printViewCart(String userId, Map<OrderLine, Integer> cart) {
		GoodsDAOImpl goodsDao = new GoodsDAOImpl();

		System.out.println("-------------------------------------------장바구니--------------------------------------------------");

		// 장바구니 목록
		for (OrderLine orderLine : cart.keySet()) {
			try {
				Goods goods = goodsDao.goodsSelectBygoodsCode(orderLine.getGoodsCode());
				int goodsCode = goods.getGoodsCode();// 상품코드
				String goodsName = goods.getGoodsName();// 상품이름		
				int goodsPrice = goods.getGoodsPrice();// 상품가격
				int quantity = cart.get(orderLine);//
				
				System.out.print(" [ 주문코드:" + goodsCode +" | 상품코드: " + goodsCode + " | 상품이름: " + goodsName +" | 상품개수:" + quantity + " | 상품가격:" + goodsPrice);
				
				for (Option option : orderLine.getOptionList()) {
					System.out.println(" | " +option+" ] ");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println();

		}

		// 장바구니 메뉴로 이동하기.
		Scanner sc = new Scanner(System.in);
		System.out.println(" 1.결제하기  | 2.장바구니전체비우기  | 3.기프티콘만들기  | 4.쇼핑하러가기");
		switch (Integer.parseInt(sc.nextLine())) {
		case 1:
			CartController.payingCart(userId, cart);
			break;
		case 2:
			CartController.modifyingCart(userId, cart);
			break;
		case 3:
			CartController.gifticonCart(userId, cart);
			break;

		case 4:
			MenuView.printUserMenu(userId);
			break;
		}

		// System.out.println(id);

	}

	/**
	 * 주문 내역보기
	 */
	public static void printOrderByUserId(List<Orders> orderList) {
		System.out.println("주문 내역");
		System.out.println("주문 코드 | 주문 날짜 | 주문 수량 | 주문 금액 | 결제 수단");
		for (Orders order : orderList) {
			System.out.println(order.getOrderCode() + " | " + order.getOrderTime() + " | " + order.getOrderQuan()
					+ " | " + order.getOrderPrice() + " | " + order.getOrderPayment());

			for (OrderLine orderLine : order.getOrderLineList()) {
				System.out.println("  ▶ " + orderLine);
				for (Option option : orderLine.getOptionList()) {
					System.out.println("     - " + option);
				}
			}
			System.out.println();
		}
	}
	/**
	  * 마이페이지->개인정보 보여주기 
	  * */
	public static void userInfoChange(Customer customer) {
		//System.out.println(customer);//이렇게하면 어떻게 출력되는지 궁금쓰 -> 주소가 찍히는 군 흠...
		System.out.println("============================== 개인정보 =================================");
		String userName=customer.getUserName();
		String userPw=customer.getUserPw();
		String phoneNum=customer.getPhoneNum();
		String email=customer.getEmail();
		String pinNum=customer.getPinNum();
		String regDate=customer.getRegDate();
		System.out.println("개인정보 변경");
		System.out.println("개인정보\t | \t휴대폰\t | \t비밀번호\t | \t이메일 \t | \t가입일자\t | \t생년월일\t");
		
		System.out.println("변경할 내용을 선택해주세요.");
		
		System.out.println(" | 닉네임 : " +userName+ " | 비밀번호 : " +userPw +" | 휴대폰 : " 
		+phoneNum+ " | 이메일 : " +email+ " | 생년월일 : " +pinNum + " | 가입일 : " + regDate+ " | ");
	}
	
	/**
	  * 마이페이지->개인정보 변경->닉네임 
	  * */
	public static void userInfoChangeName(Customer customer) {
		System.out.println("변경하신 닉네임은 " +customer.getUserName()+ "입니다.");
	}

	/**
	  * 마이페이지->개인정보 변경->폰번호 
	  * */
	public static void userInfoChangePhoneNum(Customer customer) {
		System.out.println("변경하신 휴대폰 번호은 " +customer.getPhoneNum()+ "입니다.");
	}
	
	/**
	  * 마이페이지->개인정보 변경->비번 
	  * */
	public static void userInfoChangePw(Customer customer) {
		System.out.println("변경하신 비밀번호는 " +customer.getUserPw()+ "입니다.");
	}
	
	/**
	  * 마이페이지->개인정보 변경->이메일 
	  * */
	public static void userInfoChangeEmail(Customer customer) {
		System.out.println("변경하신 이메일은 " +customer.getEmail()+ "입니다.");
	}

	/** 아직못함 
	  * 마이페이지->스탬프 조회 
	  * */
	public static void myStamp(int myStamp) {
		Customer customer = new Customer();
		System.out.println("============================== 스탬프 =================================");
		System.out.println("스탬프 현황 : " +customer.getStamp()+ "개");
		System.out.println("앗!메리카노 쿠폰 발행까지 " +(10-customer.getStamp())+ "개가 남았습니다.");
	}

	/** 아직못함 
	 *  마이페이지->최근주문내역 조회 
	  * */
	public static void selectOrderRecent(Customer customer) {
		// TODO Auto-generated method stub
	}

	public static void myMenu(Customer customer) {
		System.out.println("============================== 나만의 메뉴 =================================");
		MyMenu myMenu = new MyMenu();
		String mmName=myMenu.getMmName();
		String tem=myMenu.getTem();
		String syrup=myMenu.getSyrup();
		String def=myMenu.getDef();
		String whip=myMenu.getWhip();
		String sizeSize=myMenu.getSizeSize();
		
		System.out.println(" | 메뉴 이름 : " +mmName+ " | 온도 : " +tem +" | 시럽 : " 
		+syrup+ " | 디카페인 : " +def+ " | 휘핑크림 : " +whip + " | 사이즈 : " + sizeSize+ " | ");
	}

	/** 아직못함 
	  * 마이페이지->쿠폰코드 조회
	  * */
	public static void UserCoupon(String userId, String UserCoupon) {
		System.out.println(userId+ "님의 쿠폰 보유 현황");
		System.out.println("======보유한 쿠폰 List=====");
		System.out.println("[쿠폰코드] " +UserCoupon+ " | [쿠폰이름] " + "| [할인금액] " + "| [기한] " );
	}

	/**
	  * 마이페이지->내가 쓴 별점평가 보기 
	  * */
	public static void myStar(MyStar myStar) {
		Orders order = new Orders();
		Goods goods = new Goods();
			System.out.println("============================== 별점 평가내역 =================================");
			int oderCode=order.getOrderCode();
			int goodsCode=goods.getGoodsCode();
			int reviewStar=myStar.getReviewStar();
			String reviewDate=myStar.getReviewDate();
				
			System.out.println(" | 주문코드 : " +oderCode+ " | 상품코드 : " +goodsCode + 
					" | 별점 : " +reviewStar+ " | 별점작성 날짜 : " +reviewDate+ " | ");
			}
	
	/**
	  * 마이페이지->별점평가 
	  * */
	public static void myStarAssess(MyStar myStar) {
		System.out.println("등록하신 별점은 " +myStar.getReviewStar()+ " 점 입니다.");
		System.out.println("등록해 주셔서 감사합니다.");
	}

}