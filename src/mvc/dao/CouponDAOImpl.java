package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import mvc.dto.Customer;
import mvc.util.DbUtil;

public class CouponDAOImpl implements CouponDAO {

	private Properties proFile = DbUtil.getProfile();
	
	/**
	 * 마이페이지->쿠폰현황 조회
	 */
	public String UserCoupon(String userId) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 String UserCoupon=null;
		 
		 //String sql=proFile.getProperty
		 String sql="SELECT * FROM USER_COUPON WHERE user_id=?";
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 
			 ps.setString(1, userId);
			 
			 if(rs.next()) {
				 UserCoupon=rs.getString(1); //쿠폰코드가 저장 
			 }
			 
		 }finally{
			 DbUtil.dbClose(con, ps);
		 }
		 
		 return UserCoupon;
	}


}
