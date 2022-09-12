package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import mvc.dto.GiftCon;
import mvc.dto.Orders;
import mvc.util.DbUtil;

public class GiftConDAOImpl implements GiftConDAO {
	private Properties profile = DbUtil.getProfile();
	
	/**
	 * 기프티콘 조회
	 * @throws SQLException 
	 */
	public GiftCon selectGiftCon(Orders order) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = profile.getProperty("giftInfo.selectAllByGiftCode");
		//select * from gift_info where gift_code = ?
		GiftCon giftcon = null;
		
		try {
		   con = DbUtil.getConnection();
		   con.setAutoCommit(false);
		   
		   ps = con.prepareStatement(sql);
		   ps.setString(1, order.getGiftCode());
		   
		   rs = ps.executeQuery();
		   
		   if(rs.next()) {
			   giftcon = new GiftCon(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
		   }
		   
	    }finally {
	  	  	con.commit();
	    	DbUtil.dbClose(con, ps , rs);
	    }
			
			return giftcon;
		}

	
	/**
	 * 기프티콘 사용
	 * @throws SQLException 
	 */
	@Override
	public int orderByGiftCon(Orders order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = profile.getProperty("orderOrder.insertAll");
		//insert into order_order (user_id, order_quan, order_price, user_cp_code, order_payment, gift_code, take_out) values (?, ?, ?, ?, ?, ?, ?)
		int result = 0;
		
		try {
		   con = DbUtil.getConnection();
		   con.setAutoCommit(false);
		   
		   ps = con.prepareStatement(sql);
		   ps.setString(1, order.getUserId());
		   ps.setInt(2, order.getOrderQuan());
		   ps.setInt(3, order.getOrderPrice());//기프티콘 사용시 총구매금액
		   ps.setString(4, order.getUserCpCode());
		   ps.setString(5, order.getOrderPayment());
		   ps.setString(6, order.getGiftCode());
		   ps.setString(7, order.getTakeOut());
		   
		   result = ps.executeUpdate();
		   if(result==0) {
			   con.rollback();
			   throw new SQLException("기프티콘 사용에 실패하였습니다");
		   }
		   
		   result = useGiftCon(con, order);
		   if(result==0) {
			   con.rollback();
			   throw new SQLException("기프티콘 사용 업데이트에 실패하였습니다");
		   }
		   
	    }finally {
	  	  	con.commit();
	    	DbUtil.dbClose(con, ps , null);
	    }
			
			return result;
	}
	
	
	/**
	 * 기프티콘 사용 여부 업데이트
	 */
	public int useGiftCon(Connection con, Orders order) throws SQLException {
		PreparedStatement ps = null;
		String sql = profile.getProperty("giftInfo.updateFlag");
		// update gift_info set gift_flag = ? where gift_code = ?
		int result = 0;

		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(sql);
			ps.setString(1, "Y");
			ps.setString(2, order.getGiftCode());

			result = ps.executeUpdate();

		} finally {
			con.commit();
			DbUtil.dbClose(con, ps, null);
		}

		return result;

	}
	
}
