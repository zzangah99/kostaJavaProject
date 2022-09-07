package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mvc.dto.Category;

import mvc.util.DbUtil;

public class CategoryDAOImpl implements CategoryDAO() {
	private Properties proFile = DbUtil.getProfile();
	Category category = new Category();
	
	
	@Override
	public List<Category> selectCategory() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Category> categoryList = new ArrayList<Category>();
		String sql = proFile.getProperty("goodsCategory.selectAll"); //select * from goods_category

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
			    Category category = new Category(rs.getInt(1), rs.getString(2));
				categoryList.add(category);
			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return categoryList;

	}
	
	
}
