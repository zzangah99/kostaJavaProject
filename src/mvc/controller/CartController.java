package mvc.controller;

import java.util.Map;

import mvc.dto.Goods;
import mvc.service.GoodsService;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;
import mvc.view.EndView;
import mvc.view.FailView;


public class CartController {

	  private static GoodsService goodsService = new GoodsService();
	  	  
	   /**
	    * 장바구니 보기
	    * */
	   public static void viewCart(String userId) {
			UserSessionSet ss = UserSessionSet.getInstance();
			UserSession userSession = ss.get(userId);
			
			Map<Goods,Integer> cart = (Map<Goods, Integer>) userSession.getAttribute("cart");
			if(cart == null ) { // 장바구니가 없는 고객
				FailView.errorMessage("장바구니가 비었습니다");
			}else {
				EndView.printViewCart(userId , cart);
			}
		}


}
