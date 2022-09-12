package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import mvc.dto.Goods;
import mvc.util.DbUtil;

public class GoodsDAOImpl implements GoodsDAO {
	private Properties proFile = DbUtil.getProfile();
	Goods goods = new Goods();

	/**
	 * 대분류 상품 검색
	 */
	
	@Override
	public List<Goods> selectBever(int num, String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<Goods>();
		String sql = proFile.getProperty("goods.selectAllByCategory"); //select * from goods where category_code = ?

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();

			while (rs.next()) {
			    Goods goods = new Goods(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
			    goods.setUserId(userId); goods.setNum(num);
			    list.add(goods);
			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;

	}
	
	/**
	 * 재고 품절 여부 확인
	 
	
	public List<Goods> goodsSelectByStock(int goodsCode) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list  = new ArrayList<Goods>();
		String sql = proFile.getProperty("goods.selectAllByCode");// select * from goods where goods_code = ?

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ?가 있다면 setXxx설정
			ps.setInt(1, goodsCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
			    list.add(goods);
			}

		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return list;
	}
	**/
	
	/**
	 * 상품 이름으로 검색
	 */
	
	@Override
	public List<Goods> goodsSelectBygoodsName(String keyword) throws SQLException { //이름으로 검색
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<Goods>();
		String sql = proFile.getProperty("goods.selectAllByName"); // =select * from goods where goods_name like = ?

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			// ?가 있다면 setXxx설정
			ps.setString(1, "%" + keyword + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
				list.add(goods);
			}
			
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return list;
	}
	
	/**
	 * 상품 번호로 검색
	 */

	public Goods goodsSelectBygoodsCode(int goodsCode) throws SQLException{
	      Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  Goods goods =null;
		  String sql = proFile.getProperty("goods.selectAllByCode"); //select * from goods where goods_code = ?
		  
		  try {
			  con = DbUtil.getConnection();
			  ps= con.prepareStatement(sql);
			  
			  ps.setInt(1, goodsCode);
		      rs = ps.executeQuery(); 
		 
		  if(rs.next()) {
			  goods = new Goods(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
		  }
		  }finally {
			  DbUtil.dbClose(con, ps, rs);
		  }
		  	return goods;
	}

}
