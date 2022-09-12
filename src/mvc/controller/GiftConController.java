package mvc.controller;

import mvc.dto.GiftCon;
import mvc.dto.Orders;
import mvc.service.GiftConService;
import mvc.service.OrderService;
import mvc.view.EndView;
import mvc.view.FailView;

public class GiftConController {
	private static GiftConService giftConService = new GiftConService();
	
	/**
	 * 기프티콘 주문하기
	 */
	public static void orderByGiftCon(Orders order) {
		try {
			giftConService.orderByGiftCon(order);
			EndView.printMessage("기프티콘 주문이 완료되었습니다");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 기프티콘 조회
	 */
	public static void selectGiftCon(Orders order) {
		try {
			GiftCon giftcon = giftConService.selectGiftCon(order);
			EndView.printGiftCon(giftcon);
			
			GiftConController.orderByGiftCon(order);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

}
