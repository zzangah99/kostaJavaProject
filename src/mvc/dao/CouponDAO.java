package mvc.dao;

import java.sql.SQLException;

public interface CouponDAO {
	
	
	/**
	 * 마이페이지->쿠폰코드 조회
	 */
	String UserCoupon(String userId) throws SQLException;
	
	
	
}
