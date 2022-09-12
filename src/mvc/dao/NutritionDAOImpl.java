package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mvc.dto.Goods;
import mvc.dto.Nutrition;
import mvc.util.DbUtil;

public class NutritionDAOImpl implements NutritionDAO{
	private Properties proFile = DbUtil.getProfile();

	public List<Nutrition> goodsNutrition(int goodsCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Nutrition> list = new ArrayList<Nutrition>();
		String sql = proFile.getProperty("nutrition.selectAllByCode"); // select * from nutrition where goods_code = ?

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, goodsCode);
			rs = ps.executeQuery();

			while (rs.next()) {
				Nutrition nutrition = new Nutrition(rs.getInt(1), rs.getString(2),rs.getString(3), 
						rs.getString(4), rs.getString(5),rs.getString(6));
				list.add(nutrition);
			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;

	}

}
