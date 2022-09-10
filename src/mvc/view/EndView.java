package mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mvc.controller.CartController;
import mvc.controller.OrdersController;
<<<<<<< HEAD
import mvc.dao.NutritionDAO;
import mvc.dao.NutritionDAOImpl;
=======
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 65025e7d7839d243ef049936358ef77ac92f6cc6

>>>>>>> main
import mvc.dto.Customer;

import mvc.dto.Category;

<<<<<<< HEAD
import mvc.dto.Goods;
import mvc.dto.Nutrition;
=======
<<<<<<< HEAD
=======
import mvc.dto.Category;
>>>>>>> main
=======
>>>>>>> 65025e7d7839d243ef049936358ef77ac92f6cc6
import mvc.dto.Goods;
import mvc.dto.MyMenu;
import mvc.dto.MyStar;
>>>>>>> main
import mvc.dto.Option;
import mvc.dto.OrderLine;
import mvc.dto.Orders;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;


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
	public static void printGoodsList(List<Goods> coffeeList) {// 수정 필요
		List<Integer> goodsCodeList = new ArrayList<Integer>();
		String op[] = new String[3];
		int cup = 0;
		String tem = null;
		String userId = null;
		NutritionDAO nutrition = new NutritionDAOImpl();

		System.out.println("------------- 상품 " + coffeeList.size() + "개 -------------");
		int goodsNo = 0;
		for (Goods goods : coffeeList) {
			System.out.print((++goodsNo) + "." + goods.getGoodsName()+"\t");
			System.out.println(String.format("%-20s", goods.getGoodsPrice()+"\t"+goods.getGoodsDetail())); //살짝부족 수정 필요!!! 정렬 다시!
			goodsCodeList.add(goods.getGoodsCode());
			userId = goods.getUserId();
		
		}

		// 용식님 메뉴 상세정보 출력하실 위치(영양정보, 메뉴정보 나오는 화면 ...)

		//
		System.out.print("주문할 상품 번호를 고르세요> ");
		int orderNo = Integer.parseInt(sc.nextLine());
		int orderCode = goodsCodeList.get(orderNo - 1);

		if (orderNo != 6 && orderNo != 7) {// 디저트, md 상품이 아니면 옵션 선택
			System.out.print("영양정보를 확인 하시겠습니까? (Y or N)");
			String check = sc.nextLine();
			//if(check == "Y" || check == "y") {
			//List<Nutrition> nut= nutrition.goodsNutrition(int goodsCode);
			
		
			//}
			
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
		}
		System.out.println("수량을 입력하세요");
		int quan = Integer.parseInt(sc.nextLine());

		for (int i = 0; i < op.length; i++) {
			op[i] = op[i].replace("1", "Y");
			op[i] = op[i].replace("2", "N");
		}

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
			System.out.println("1.먹고가기\t 2.가져가기");
			String takeOut = sc.nextLine();
			takeOut = takeOut.replace("1", "N");
			takeOut = takeOut.replace("2", "Y");

			Orders order = new Orders(0, null, userId, null, 0, quan, payment, null, takeOut);// userId 받아야함
			OrderLine orderline = new OrderLine(0, orderCode, coffeeList.get(orderNo - 1).getGoodsCode(), 0, quan);
			Option option = new Option(0, cup, null, tem, op[0], op[1], op[2]);

			order.getOrderLineList().add(orderline);
			orderline.getOptionList().add(option);

			OrdersController.insertOrders(order);
			break;
		case 2: // 장바구니 담기
			
			Orders cartOrder = new Orders(0, null, userId, null, 0, quan, null, null, null);// userId 받아야함
			OrderLine cartOrderline = new OrderLine(0, 0, orderCode, 0, quan);
			Option cartOption = new Option(0, cup, null, tem, op[0], op[1], op[2]);
			
			cartOrder.getOrderLineList().add(cartOrderline);
			cartOrderline.getOptionList().add(cartOption);
			
			CartController.putCart(cartOrder, cartOrderline);
			System.out.println("11");
			break;
		}
	}
			
		
	
	
	public static void printMessage(String message) {//
		System.out.println(message);
	}

	/**
	 * 장바구니 보기
	 **/
	public static void printViewCart(String userId, Map<Goods, Integer> cart) {
		System.out.println("----------------장바구니------------------");
		// 장바구니 목록
		for (Goods goods : cart.keySet()) {
			int goodsCode = goods.getGoodsCode();// 상품번호
			String goodsName = goods.getGoodsName();// 상품번호
			// 상품옵션
			int goodsPrice = goods.getGoodsPrice();// 상품번호
			int quantity = cart.get(goods);//

			System.out.println(" [ 상품코드:" + goodsCode + "\t | 상품이름: " + goodsName + "\t | 상품옵션: " + goodsName
					+ "\t | 상품개수:" + quantity + "\t | 상품가격:" + goodsPrice + " ]");

		}

		// 장바구니 메뉴로 이동하기.
		Scanner sc = new Scanner(System.in);
		System.out.println("1.결제하기  | 2.수정하기  | 3.기프티콘만들기  | 4.쇼핑하러가기");
		switch (Integer.parseInt(sc.nextLine())) {
		case 1:
			CartController.payingCart(userId, cart);
			break;
		case 2:
			CartController.modifyingCart(userId, cart);
			break;
		case 3:
			System.out.println("수정할 제품의 이름을 입력해주십시오 > ");
			String modifyingGoods = sc.nextLine();
			CartController.gifticonCart(modifyingGoods, cart);
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
		System.out.println("주문 코드\t | \t주문 날짜\t | \t주문 수량\t | \t주문 금액\t | \t결제 수단\t");
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
<<<<<<< HEAD
	
=======


<<<<<<< HEAD
=======

>>>>>>> 65025e7d7839d243ef049936358ef77ac92f6cc6
>>>>>>> main
	/**
	  * 마이페이지->개인정보 보여주기 
	  * */
	public static void userInfoChange(Customer customer) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> main
		//System.out.println(customer);//이렇게하면 어떻게 출력되는지 궁금쓰 -> 주소가 찍히는 군 흠...
		System.out.println("============================== 개인정보 =================================");
		String userName=customer.getUserName();
		String userPw=customer.getUserPw();
		String phoneNum=customer.getPhoneNum();
		String email=customer.getEmail();
		String pinNum=customer.getPinNum();
		String regDate=customer.getRegDate();
<<<<<<< HEAD
=======
=======
		System.out.println("개인정보 변경");
		System.out.println("개인정보\t | \t휴대폰\t | \t비밀번호\t | \t이메일 \t | \t가입일자\t | \t생년월일\t");
		for() {
		System.out.println("변경할 내용을 선택해주세요.");
		
>>>>>>> 65025e7d7839d243ef049936358ef77ac92f6cc6
>>>>>>> main
		
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

<<<<<<< HEAD

=======
<<<<<<< HEAD
>>>>>>> main
	/** 아직못함 
	  * 마이페이지->나만의 메뉴 보기 
	  * */
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
<<<<<<< HEAD
}
=======

	

	
	
	
	
	
	
	}

	

	
	

	

<<<<<<< HEAD

=======
}
>>>>>>> main
=======
}
>>>>>>> 65025e7d7839d243ef049936358ef77ac92f6cc6
>>>>>>> main
