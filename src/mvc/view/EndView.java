package mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mvc.controller.OrdersController;
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

		System.out.println("------------- 상품 " + coffeeList.size() + "개 -------------");
		for (Goods goods : coffeeList) {
			int no=1;
			System.out.print(no++ +"."+goods.getGoodsName() +" \t");
			goodsCodeList.add(goods.getGoodsCode());
		}
		System.out.print("\n주문할 상품 번호를 고르세요> ");
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

			System.out.println("1.Hot\t 2.Ice");
			tem = sc.nextInt();
			System.out.println("0.없음\t 1.Small\t 2.Regular\t 3.Large");
			cup = sc.nextInt();
			System.out.println("1.시럽 추가\t 2.추가 안 함");
			op[0] = sc.nextInt();
			System.out.println("1.디카페인\t 2.해당 없음");
			op[1] = sc.nextInt();
			System.out.println("1.휘핑 추가\t 2.추가 없음");
			op[2] = sc.nextInt();
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
		case 3:
			temS="I";
			break;
		}
		
		
		Orders order = new Orders(0,null,null,null,0,quan,null,null,null);//userId 받아야함
		OrderLine orderline = new OrderLine(0,0,orderCode,0,quan);
		Option option = new Option(0,cup,null,temS,op2[0],op2[1],op2[2]);
		
		order.getOrderLineList().add(orderline);
		orderline.getOptionList().add(option);
		
		System.out.println("1.주문하기\t 2.장바구니에 담기");
		switch(sc.nextInt()){
		case 1:	
			System.out.println("- 주문 상품 : ");
			
			OrdersController.insertOrders(order); break;
		case 2: 
		}
		
		
		Orders orders = new Orders(0,null,null,null,0,quan,null,null,null);
		OrderLine ordLine = new OrderLine(0,0,orderCode,0,quan);
		Option option2 = new Option(0,cup,null,temS,op2[0],op2[1],op2[2]);
		
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


	
}
