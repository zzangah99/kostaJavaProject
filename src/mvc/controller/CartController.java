package mvc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mvc.dao.GoodsDAO;
import mvc.dao.GoodsDAOImpl;
import mvc.dto.Goods;
import mvc.dto.OrderLine;
import mvc.dto.Orders;
import mvc.service.GoodsService;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;
import mvc.view.EndView;
import mvc.view.FailView;
import mvc.view.MenuView;


public class CartController {

	private static GoodsDAOImpl goodsDAO = new GoodsDAOImpl(); 
			
	/**
	 * 장바구니에 담기
	 */
	public static void putCart(String userId, int goodsId, int quantity) {

		try {
			Goods goods = goodsDAO.goodsSelectBygoodsCode(goodsId);
			
			if (goods.getStock() < quantity) {
				throw new SQLException("재고량 부족으로 장바구니에 담을수 없습니다.");
			}
			// id에 해당하는 세션찾기
			UserSessionSet ss = UserSessionSet.getInstance();
			UserSession userSession = ss.get(userId);

			// 세션에서 장바구니 찾기
			Map<Goods, Integer> cart = (Map<Goods, Integer>) userSession.getAttribute("cart"); // 상품 , 수량 저장

			// 장바구니가 없으면 장바구니 생성
			if (cart == null) {
				cart = new HashMap<>();
				userSession.setAttribute("cart", cart);
			}

			// 장바구니에서 상품찾기
			Integer oldQuantity = cart.get(goods);
			if (oldQuantity != null) { // 장바구니에 이미 상품이 있다면
				quantity += oldQuantity; // 수량을 누적
			}

			cart.put(goods, quantity); // 장바구니에 상품 넣기
			EndView.printMessage("장바구니에 담았습니다");

			// 장바구니 보러가거나 쇼핑하기 메뉴 실행
			Scanner sc = new Scanner(System.in);
			System.out.println("1.장바구니 보러가기  | 2.계속쇼핑하러가기");

			switch (Integer.parseInt(sc.nextLine())) {
			case 1:
				viewCart(userId);
				break;
			case 2:
				MenuView.printUserMenu(userId);
				break;
			}

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 장바구니 보기
	 */
	public static void viewCart(String userId) {

		// userId가 null 일때 userId에 임이의 값 지정.
		if (userId == null)
			userId = "Guest";

		UserSessionSet ss = UserSessionSet.getInstance();
		UserSession userSession = ss.get(userId);

		Map<Goods, Integer> cart = (Map<Goods, Integer>) userSession.getAttribute("cart");
		if (cart == null) { // 장바구니가 없는 고객
			FailView.errorMessage("장바구니가 비었습니다");
			MenuView.printUserMenu(userId);
		} else {
			EndView.printViewCart(userId, cart);
		}
	}

	/**
	 * 장바구니 결제하기
	 */
	public static void payingCart(String userId, Map<Goods, Integer> cart) {
		Orders orders = new Orders(0, null, userId, null, 0, 0, null, null, null);

		List<OrderLine> orderLineList = orders.getOrderLineList();

		for (Goods goodsKey : cart.keySet()) {
			OrderLine orderLine = new OrderLine(0, 0, goodsKey.getGoodsCode(), 0, 0);
			orderLineList.add(orderLine);
		}

		System.out.println("----- 총 " + orderLineList.size() + "개의 상품이 결제 진행 중입니다.------ ");
		OrdersController.insertOrders(orders);

		// 장바구니비우기
		UserSessionSet ss = UserSessionSet.getInstance();
		UserSession userSession = ss.get(userId);
		userSession.removeAttribute("cart");
	}

	/**
	 * 수정하기
	 */
	public static void modifyingCart(String modifyingGoods, Map<Goods, Integer> cart) {
	
		
		
		Integer oldQuantity = cart.get(goodsDAO);

		if (oldQuantity != null) { // 장바구니에 이미 상품이 있다면

		} else {
			FailView.errorMessage("장바구니에 존재하지 않는 상품입니다.");
		}

	}

	/**
	 * 기프티콘 만들기
	 */
	public static void gifticonCart(String userId, Map<Goods, Integer> cart) {

	}

}
