package mvc.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mvc.controller.CartController;
import mvc.controller.OrdersController;
import mvc.dao.GoodsDAOImpl;
import mvc.dto.Category;
import mvc.dto.Customer;
import mvc.dto.GiftCon;
import mvc.dto.Goods;
import mvc.dto.Option;
import mvc.dto.OrderDetail;
import mvc.dto.OrderLine;
import mvc.dto.Orders;

public class EndView {
	static Scanner sc = new Scanner(System.in);

	/**
	 * 대분류 출력
	 */

	public static void printsCategoryList(List<Category> categoryList) {
		for (Category list : categoryList) {
			System.out.print("  " + list.getCategoryCode() + "." + list.getCategoryName() + "   ");

		}
		System.out.print("  "+"8.상품 이름으로 검색하기  ");
		System.out.println("  "+"9.돌아가기");
		System.out.println();
		System.out.print("주문하실 종류의 번호를 입력해주세요 > ");
	}
	
	/**
	 * 검색해서 상품 찾기
	 */
	
	public static void printGoodsSelectByName(List<Goods> list) {
		
		for(Goods goods : list) {
			System.out.println(goods);
		}

	}

	/**
	 * 메뉴 출력 후 주문
	 */
	public static void printGoodsList(List<Goods> coffeeList) {
		List<Integer> goodsCodeList = new ArrayList<Integer>();
		String op[] = new String[3];
		int cup = 0;
		String tem = null;
		String userId = null;

		System.out.println("\n-------------------------- 상품 " + coffeeList.size() + "개 --------------------------");
	      int goodsNo = 0;
	      for (Goods goods : coffeeList) {
	         System.out.print((++goodsNo) + "." + goods.getGoodsName()+"\t");
	         System.out.printf(String.format(goods.getGoodsPrice()+"\t"));
	         System.out.printf(String.format(goods.getGoodsDetail())+"\n");
	         goodsCodeList.add(goods.getGoodsCode());
	         userId = goods.getUserId();
	         if(userId==null) userId = "Guest";
	      }

		
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
		int choice = Integer.parseInt(sc.nextLine());
		System.out.println("- 주문 상품 : " + coffeeList.get(orderNo - 1).getGoodsName() + "  |  주문 수량 : " + quan+ "  |  주문 금액 : "+ (coffeeList.get(orderNo - 1).getGoodsPrice() * quan));

		switch (choice) {
		case 1:
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

			Orders order = new Orders(0, userId, null, quan, 0, null, payment, null, takeOut);
			OrderLine orderline = new OrderLine(0, 0, orderGoodsCode, 0, quan, coffeeList.get(orderNo - 1).getGoodsName());
			order.getOrderLineList().add(orderline);
			if(coffeeList.get(orderNo - 1).getNum() < 6) {
				Option option = new Option(0, cup, null, tem, op[0], op[1], op[2]);
				orderline.getOptionList().add(option);
			}
			
			OrdersController.insertOrders(order);
			break;
		case 2: // 장바구니 담기
			Orders cartOrder = new Orders(0, userId, null, quan, 0, null, null, null, null);
			OrderLine cartOrderline = new OrderLine(0, 0, orderGoodsCode, 0, quan, coffeeList.get(orderNo - 1).getGoodsName());
			
			cartOrder.getOrderLineList().add(cartOrderline);
			if(coffeeList.get(orderNo - 1).getNum() < 6) {
				Option option = new Option(0, cup, null, tem, op[0], op[1], op[2]);
				cartOrderline.getOptionList().add(option);
			}
			
			
			
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

	}

	/**
	 * 주문 내역보기
	 */
	public static void printOrderByUserId(List<Orders> orderList) {
		System.out.println("------------------- 주문 내역 --------------------");
		//System.out.println("주문 코드 | 주문 날짜 | 주문 수량 | 주문 금액 | 결제 수단");
		for (Orders order : orderList) {
			System.out.println("주문 코드: "+ order.getOrderCode() + " | " + "주문 날짜: "+ order.getOrderTime() + " | " + "주문 수량: "+ order.getOrderQuan()
					+ " | " + "주문 금액: "+ order.getOrderPrice() + " | " + "결제 수단: "+ order.getOrderPayment());

			for (OrderLine orderLine : order.getOrderLineList()) {
				System.out.println("  > " + orderLine);
				for (Option option : orderLine.getOptionList()) {
					System.out.println("     - " + option);
				}
			}
			System.out.println();
		}
	}
	
	
	/**
	 * 마이페이지->개인정보 보여주기 
	 */
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

	
	/**
	 * 기프티콘 출력
	 */
	public static void printGiftCon(GiftCon giftcon) {
		System.out.println(giftcon);
	}
	
	
	/**
	 * 통계 출력
	 * @param orderDetail
	 */
		public static void PrintDayStatistic(OrderDetail orderDetail) {
		System.out.println("금일 총 판매수량 : " + String.valueOf(orderDetail.getTotalQuantity())+"개");
		
		System.out.println("금일 총 판매금액 : "+String.valueOf(orderDetail.getTotalPrice()));
		}
		
		public static void PrintMonthStatistic(OrderDetail orderDetail) {
		System.out.println("당월 총 판매수량 : " + String.valueOf(orderDetail.getTotalQuantity())+"개");
		
		System.out.println("당울 총 판매금액 : "+String.valueOf(orderDetail.getTotalPrice()));
		}

}