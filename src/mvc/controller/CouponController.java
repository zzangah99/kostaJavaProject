package mvc.controller;

import mvc.dto.Customer;
import mvc.service.CouponService;
import mvc.service.CustomerService;
import mvc.view.EndView;
import mvc.view.FailView;

public class CouponController {
	static CouponService couponService = new CouponService();
	
	/**
	  * 마이페이지->쿠폰현황 조회 
	  * */
	public static void UserCoupon(String userId) {
		try {
			String UserCoupon = couponService.UserCoupon(userId);
			
			EndView.UserCoupon(userId, UserCoupon);
			
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
	}
}	