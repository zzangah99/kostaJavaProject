package mvc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mvc.dao.GoodsDAOImpl;
import mvc.dto.Goods;
import mvc.dto.Option;
import mvc.dto.OrderLine;
import mvc.dto.Orders;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;
import mvc.view.EndView;
import mvc.view.FailView;

public class CartController {

	private static GoodsDAOImpl goodsDao = new GoodsDAOImpl();
	
	/**
	 * 장바구니에 넣기
	 */
	public static void putCart(OrderLine orderLine, String userId) {

		try {

			// 상품번호에 해당 상품찾기
			Goods goods = goodsDao.goodsSelectBygoodsCode(orderLine.getGoodsCode());
			int quantity = orderLine.getDetailQuan();

			if (goods.getStock() <=0 ) {
	            throw new SQLException("재고량 부족으로 장바구니에 담을수 없습니다.");
	         }


			// id에 해당하는 세션찾기
			UserSessionSet ss = UserSessionSet.getInstance();
			UserSession userSession = ss.get(userId);

			// 세션에서 장바구니 찾기
			Map<OrderLine, Integer> cart = (Map<OrderLine, Integer>) userSession.getAttribute("cart"); // 상품 , 수량 저장

			// 장바구니가 없으면 장바구니 생성
			if (cart == null) {
				cart = new HashMap<>();
				userSession.setAttribute("cart", cart);
			}

			// 장바구니에서 상품찾기
			Integer oldQuantity = cart.get(orderLine);
			if (oldQuantity != null) { // 장바구니에 이미 상품이 있다면
				quantity += oldQuantity; // 수량을 누적
			}

			cart.put(orderLine, quantity); // 장바구니에 상품 넣기

			EndView.printMessage("장바구니에 담았습니다");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 장바구니 보기
	 */
	public static void viewCart(String id) {
		UserSessionSet ss = UserSessionSet.getInstance();
		UserSession userSession = ss.get(id);

		Map<OrderLine, Integer> cart = (Map<OrderLine, Integer>) userSession.getAttribute("cart");
		if (cart == null) { // 장바구니가 없는 고객
			FailView.errorMessage("장바구니가 비었습니다");
		} else {
			EndView.printViewCart(id, cart);
		}
	}

	/**
	 * 장바구니 결제하기
	 */
	public static void payingCart(String userId, Map<OrderLine, Integer> cart) {

		Orders orders = new Orders(0, userId, null, 0, 0, null, null, null, null);
		List<OrderLine> orderLineList = orders.getOrderLineList();

		for (OrderLine orderLineKey : cart.keySet()) {
			OrderLine orderLine = new OrderLine(0, 0, orderLineKey.getGoodsCode(), 0, 0, orderLineKey.getGoodsName());
			//System.out.println(orderLine);
			List<Option> optionList = orderLine.getOptionList();
			
			//카드에서 옵션의 정보를 꺼내서 orderLIne에 저장한다. 
			optionList.add(orderLineKey.getOptionList().get(0));
			
			//System.out.println("orderLine.getOptionList = " + orderLine.getOptionList());
			orderLineList.add(orderLine);
		}

		System.out.println("------------ 총 " + orderLineList.size() + "개의 상품이 결제 진행 중입니다 ------------");
		OrdersController.insertOrders(orders);//옵션 저장해서 가야한다. 

		// 장바구니비우기
		UserSessionSet ss = UserSessionSet.getInstance();
		UserSession userSession = ss.get(userId);
		userSession.removeAttribute("cart");

	}
	
	/**
	 * 장바구니 전체 비우기
	 */
	public static void modifyingCart(String userId, Map<OrderLine, Integer> cart) {
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 제품의 주문번호을 입력해주십시오 > ");
		int modifyingGoods = sc.nextInt();
		
		if (modifyingGoods == cart.hashCode()) { // 장바구니에 이미 상품이 있다면
			cart.clear();
		} else {
			FailView.errorMessage("장바구니에 존재하지 않는 상품입니다.");
		}
		*/
		
		// 장바구니 전체 비우기
		UserSessionSet ss = UserSessionSet.getInstance();
		UserSession userSession = ss.get(userId);
		userSession.removeAttribute("cart");
	}

	/**
	 * 기프티콘 넣기
	 */
	public static void gifticonCart(String userId, Map<OrderLine, Integer> cart) {

		Orders orders = new Orders(0, userId, null, 0, 0, null, null, null, null);
		List<OrderLine> orderLineList = orders.getOrderLineList();

		for (OrderLine orderLineKey : cart.keySet()) {
			OrderLine orderLine = new OrderLine(0, 0, orderLineKey.getGoodsCode(), 0, 0, orderLineKey.getGoodsName());
			orderLineList.add(orderLine);
		}

		System.out.println("----- 총 " + orderLineList.size() + "개의 상품이 결제 진행 중입니다.------ ");
		OrdersController.insertOrders(orders);

		// 장바구니비우기
		UserSessionSet ss = UserSessionSet.getInstance();
		UserSession userSession = ss.get(userId);
		userSession.removeAttribute("cart");

	}

}
