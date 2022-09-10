package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import mvc.dto.MyStar;
import mvc.util.DbUtil;

public class MyStarDAOImpl implements MyStarDAO{
	
	private Properties proFile = DbUtil.getProfile();
	
	/**
	  * 마이페이지->내가쓴 별점평가 보기(not null) 
	  * */
	public MyStar myStar(String userId) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 MyStar myStar=null;
		 //int myStar=0;
		 //String sql=proFile.getProperty("");
		 String sql="select order_code, goods_code, reviw_star, reviw_date from reviw where user_id = ?;";
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 ps.setString(1, userId);
			 rs=ps.executeQuery();
			
			 if(rs.next()) {
				 myStar = new MyStar(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
			 	}
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }
		return myStar;
	}

	@Override
	public MyStar myStarAssess(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	  * 마이페이지->내가쓴 별점 평가하기(not null) 
	  * */
	
	
	
	
	
	
	
	
}
