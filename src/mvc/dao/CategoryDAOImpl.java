package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mvc.util.DbUtil;
import mvc.dto.Category;
import mvc.dto.Goods;

public class CategoryDAOImpl implements CategoryDAO{
	Category category = new Category();
	private Properties proFile = DbUtil.getProfile();
	
	
	
	public List<Category> selectCategory() throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Category> list = new ArrayList<Category>();
		String sql = proFile.getProperty("goodsCategory.selectAll"); //select * from goods_category
		try {
			 con = DbUtil.getConnection();
			 ps= con.prepareStatement(sql);
		     rs = ps.executeQuery();
		
		     while(rs.next()) {
		    	 Category category = new Category(rs.getInt(1),rs.getString(2));
		    	 list.add(category);
		     }
		
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
		
	}
	


	
	
	
}