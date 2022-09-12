package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import mvc.dto.Review;
import mvc.util.DbUtil;

public class ReviewDAOImpl implements ReviewDAO {
	private Properties proFile = DbUtil.getProfile();
	Review review = new Review();

	/**
	 * 대분류 상품 검색
	 */
	
	@Override
	public int writeReview(Review review) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		int result = 0;
		String sql = proFile.getProperty("review.write"); //insert into review values(

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
		
			ps.setString(1, review.);
			ps.setInt(2, result);
			ps.setString(3, sql);
			
			result = ps.executeUpdate();

			
		} finally {
			DbUtil.dbClose(con, ps);
		}
		return result;

	}
	

}
