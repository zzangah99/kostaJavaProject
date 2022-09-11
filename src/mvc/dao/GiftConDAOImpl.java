package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import mvc.dto.Orders;
import mvc.util.DbUtil;

public class GiftConDAOImpl implements GiftConDAO {
	private Properties profile = DbUtil.getProfile();
	
	@Override
	public int GiftConInsert(Orders order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = profile.getProperty("giftInfo.insert");//insert into gift_info (gift_code, order_code, gift_limit_date) values (?, ?, ?)
		int result = 0;
		
		try {
		   con = DbUtil.getConnection();
		   con.setAutoCommit(false);
		   
		   ps = con.prepareStatement(sql);
		   ps.setString(1, order.getGiftCode());
		   ps.setInt(2, order.getOrderCode());
		   ps.setString(3, order.getOrderTime());
		   
		   result = ps.executeUpdate();
		   if(result==0) {
			   con.rollback();
			   throw new SQLException("주문에 실패하였습니다");
		   }
		   else {
			   int re [] = orderLineInsert(con, order); //주문상세 등록하기 
			   for(int i : re) {
				   if(i != 1) {//
					   con.rollback();
					   throw new SQLException("주문에 실패하였습니다");
				   }
			   }
			   
			   //주문수량만큼 재고량 감소하기
			   decrementStock(con, order.getOrderLineList());
			   con.commit();
		   }
		   
    }finally {
  	  	con.commit();
    	DbUtil.dbClose(con, ps , null);
    }
		
		return result;
	}
	

	private int[] orderLineInsert(Connection con, Orders order) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Orders> giftConInfo(String giftCode) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
