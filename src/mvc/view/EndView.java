package mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import mvc.controller.CartController;
import mvc.controller.OrdersController;
import mvc.dto.Customer;

import mvc.dto.Category;
import mvc.dto.Category;
import mvc.dto.Goods;
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
	public static void printGoodsList(List<Goods> coffeeList) {// 수정 필요
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
		}

		// 용식님 메뉴 상세정보 출력하실 위치(영양정보, 메뉴정보 나오는 화면 ...)

		//
		System.out.print("\n주문할 상품 번호를 고르세요> ");
		int orderNo = Integer.parseInt(sc.nextLine());
		int orderCode = goodsCodeList.get(orderNo - 1);

		if (orderNo > 6) {// 디저트, md 상품이 아니면 옵션 선택
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

		System.out.println();
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
	/**
	  * 마이페이지->개인정보 보여주기 
	  * */
	public static void userInfoChange(String userId, Customer customer) {
		//System.out.println(customer);//이렇게하면 어떻게 출력되는지 궁금쓰 -> 주소가 찍히는 군 흠...
		System.out.println("========================== " +userId+ "님의 개인정보 내역입니다. =============================================================");
		String userName=customer.getUserName();
		String userPw=customer.getUserPw();
		String phoneNum=customer.getPhoneNum();
		String email=customer.getEmail();
		String pinNum=customer.getPinNum();
		String regDate=customer.getRegDate();
		
		System.out.println(" | 닉네임 : " +userName+ " | 비밀번호 : " +userPw +" | 휴대폰 : " 
		+phoneNum+ " | 이메일 : " +email+ " | 생년월일 : " +pinNum + " | 가입일 : " + regDate+ " | ");
		System.out.println();
		System.out.println("변경할 개인정보를 선택해주세요 >");
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

	

	

	

	

	
	
	

}


