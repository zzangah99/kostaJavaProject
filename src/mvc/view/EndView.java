package mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kosta.mvc.controller.OrderController;
import mvc.controller.OrdersController;
import mvc.dto.Customer;
import mvc.dto.Goods;
import mvc.dto.Option;
import mvc.dto.OrderLine;
import mvc.dto.Orders;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;

public class EndView {
	static Scanner sc = new Scanner(System.in);
	
	/**
	 * 상품 전체 출력
	 */
	public static void printGoodsList(List<Goods> coffeeList) {// 수정 필요
		List<Integer> goodsCodeList = new ArrayList<Integer>();
		int op[] = new int[3];
		String op2[] = new String[3];
		int cup=0;
		int tem = 0;
		String temS=null;
		
		System.out.println("-----상품 " + coffeeList.size() + "개 -------------");

		for (Goods goods : coffeeList) {
			int no=1;
			System.out.print(no +"."+goods.getGoodsName() +" ");
			goodsCodeList.add(goods.getGoodsCode());
		}
		System.out.print("주문할 상품 번호를 고르세요> ");
		int orderNo = sc.nextInt();
		int orderCode = goodsCodeList.get(orderNo-1);
		
		if(orderNo != 6 && orderNo != 7) {//디저트, md 상품이 아니면
			System.out.println("선택해주세요");
			System.out.println("1.Hot  2.Ice");
			tem = sc.nextInt();
			System.out.println("0.없음 1.Small  2.Regular  3.Large");
			cup = sc.nextInt();
			System.out.println("1.시럽 추가  2.추가 안 함");
			op[1] = sc.nextInt();
			System.out.println("1.디카페인  2.해당 없음");
			op[2] = sc.nextInt();
			System.out.println("1.휘핑 추가  2.추가 없음");
			op[3] = sc.nextInt();
		}
		System.out.println("수량을 입력하세요");
		int quan = sc.nextInt();
		
		for(int i=0; i<op.length; i++) {
			if(op[i]==1) {
				op2[i] = "Y";
			}else op2[i]="N";
		}
		
		switch(tem) {
		case 1:
			temS="H";
			break;
		case 2:
			temS="I";
			break;
		}
		
		
		Orders order = new Orders(0,null,null,null,0,quan,null,null,null);
		OrderLine orderline = new OrderLine(0,0,orderCode,0,quan);
		Option option = new Option(0,cup,null,temS,op2[0],op2[1],op2[2]);
		
		order.getOrderLineList().add(orderline);
		orderline.getOptionList().add(option);
		
		OrdersController.insertOrders(order);//주문 or 장바구니담기
		
		System.out.println();
	}
	 
	 
	 
	public static void printMessage(String message) {//
		System.out.println(message);
	}
	 
	
	/**
	 * 장바구니 보기
	 **/
	public static void printViewCart(String userId, Map<Goods, Integer> cart) {
		System.out.println("장바구니");

		for (Goods goods : cart.keySet()) {
			String goodsCode = goods.getGoodsCode();// 상품번호
			 String goodsName = goods.getGoodsCode();// 상품번호
			 int    goodsPrice = goods.getGoodsPrice();// 상품번호
			 String goodsDetail = goods.getGoodsDetail();// 상품번호
			 String soldout = goods.getGoodsCode();// 상품번호
			 int    stock = goods.getGoodsCode();// 상품번호
			
			String goodsId = goods.getGoodsCode();// 상품번호
			String name = goods.getGoodsName();// 상품이름
			int price = goods.getGoodsPrice();// 상품가격

			int quantity = cart.get(goods);//
			System.out.println(goodsId + " : " + name + " : " + price + " \t " + quantity);
		}

		Scanner sc = new Scanner(System.in);
		System.out.println("1.결제라디  |  9.나가기");
		switch (Integer.parseInt(sc.nextLine())) {
		case 1:


			Orders orders = new Orders(null, null,userId, null, 0, 0,null, null, null);

			List<OrderLine> orderLineList = orders.getOrderLineList();

			for (Goods goodsKey : cart.keySet()) {
				int qty = cart.get(goodsKey);
				OrderLine orderLine = new OrderLine(0,null, goodsKey.getGoodsCode(), 0, 0);
				orderLineList.add(orderLine);
			}

			System.out.println("orderLineList 개수 : " + orderLineList.size());
			OrdersController.insertOrders(orders);

			// 장바구니비우기
			UserSessionSet ss = UserSessionSet.getInstance();
			UserSession userSession = ss.get(userId);
			userSession.removeAttribute("cart");
			break;

		case 9:
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
				for(Option option : orderLine.getOptionList()) {
					System.out.println("     - " + option);
				}
			}
			System.out.println();
		}
	}


	/**
	  * 마이페이지  
	  * 비번 인수로 받아 개인정보 변경 
	  * */
	public static void userInfoChange(int customer) {
		System.out.println("변경할 내용을 선택해주세요.");
		
		
	}


	/**
	  * 마이페이지  
	  * 아이디 인수로 받아 스탬프 조회 
	  * */
	public static void myStamp(int myStamp) {
		System.out.println("보유한 스탬프는" +myStamp+ "개입니다.");
		
		Customer customer = new Customer();
		customer.getStamp();
		for(int i=1; i>=5; i++) {
			System.out.println("*");
		}
		
	}


	/** 
	 *  마이페이지 
	  * 아이디 인수로 받아 최근주문내역 조회 
	  * */
	public static void selectOrderRecent(Customer customer) {
		// TODO Auto-generated method stub
		
	}


	/** 
	  * 마이페이지 
	  * 아이디 인수로 받아 나만의 메뉴 
	  * */
	public static void myMenu(Customer customer) {
		// TODO Auto-generated method stub
		
	}


	/**
	  * 마이페이지->쿠폰코드 조회
	  * */
	public static void UserCoupon(String userId, String UserCoupon) {
		System.out.println(userId+ "님의 쿠폰 보유 현황");
		System.out.println("======보유한 쿠폰 List=====");
		System.out.println("[쿠폰코드] " +UserCoupon+ " | [쿠폰이름] " + "| [할인금액] " + "| [기한] " );
		
		
		
	}


	/**
	  * 아이디 인수로 받아 내가 쓴 별 보기 
	  * */
	public static void myStar() {
		// TODO Auto-generated method stub
		
	}



	
	

	
}
