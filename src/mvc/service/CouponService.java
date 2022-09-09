package mvc.service;

import java.sql.SQLException;

import mvc.dao.CouponDAO;
import mvc.dao.CouponDAOImpl;
import mvc.dao.CustomerDAO;
import mvc.dao.CustomerDAOImpl;
import mvc.dto.Customer;
import mvc.exception.NotFoundException;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;

public class CouponService {
	CouponDAO couponDao = new CouponDAOImpl();
	

	/**
	 * 마이페이지->쿠폰현황 조회
	 */
	public String UserCoupon(String userId) throws SQLException, NotFoundException {
		String UserCoupon = couponDao.UserCoupon(userId);
		
		if(UserCoupon==null) {
			throw new NotFoundException("현재 보유하고 있는 쿠폰이없습니다.");
		}
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);	
		
		return UserCoupon;
		
	}



}



