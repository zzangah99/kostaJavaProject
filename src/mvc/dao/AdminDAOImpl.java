package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mvc.controller.AdminController;
import mvc.dto.Admin;
import mvc.dto.Goods;
import mvc.dto.Notice;
import mvc.util.DbUtil;
import java.util.Properties;

public class AdminDAOImpl implements AdminDAO {
	private Properties proFile = DbUtil.getProfile();

	@Override
	public Admin login(String adminId, String adminPw) throws SQLException {
	
		  Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  Admin admin=null;
		 try {
		   con = DbUtil.getConnection();
		   ps= con.prepareStatement("select * from Customer where user_id=? and user_pwd=?");
		   ps.setString(1, adminId);
		   ps.setString(2, adminPw);
		   
	        rs = ps.executeQuery(); 
	        
	        if(rs.next()) {
	        	admin = new Admin(rs.getString(1), rs.getString(2));
	        }
        }finally {
        	DbUtil.dbClose(con, ps, rs);
        }
		return admin;
	}//로그인
	
	
     //등록
	@Override
	public int GoodsInsert(Goods goods) throws SQLException {
		
        Connection con=null;
		PreparedStatement ps =null;
		String sql = proFile.getProperty("goods.insertMd=insert into goods");
		int result =0;
		
		try {
		   con = DbUtil.getConnection();
		   con.setAutoCommit(false);
		   
		   ps = con.prepareStatement(sql);
		  
		   ps.setInt(1, goods.getGoodsCode());
		   ps.setString(2, goods.getGoodsName());
		   ps.setInt(3, goods.getGoodsPrice());
		   ps.setString(4, goods.getSoldOut());
		   ps.setInt(5, goods.getStock());
		   ps.setInt(6, goods.getCategoryCode());
		   ps.addBatch(); //일괄처리할 작업에 추가
		   ps.clearParameters();
		   

		   result = ps.executeUpdate();

	
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}
	
	//수정
	public int GoodsUpadteName(Goods goods) throws SQLException {

        Connection con=null;
		PreparedStatement ps =null;
		int result = 0;
		String sql= proFile.getProperty("goods.updateNameByCode");//update goods set goods_name = ? where goods_code = ?
	    
		try {
			 con = DbUtil.getConnection();
			 ps = con.prepareStatement(sql);
			 
				ps.setString(1,goods.getGoodsName() );
				ps.setInt(2,goods.getGoodsCode() );
			 
		result = ps.executeUpdate();
			System.out.println("상품 수정완료");

		}finally {
			DbUtil.dbClose(con, ps);
		}
		
		return result;
	}
	
	//수정
		public int GoodsUpadtePr(Goods goods) throws SQLException {

	        Connection con=null;
			PreparedStatement ps =null;
			int result = 0;
			String sql= proFile.getProperty("goods.updatePriceByCode");
			try {
				 con = DbUtil.getConnection();
				 ps = con.prepareStatement(sql);
				 
					ps.setInt(1,goods.getGoodsPrice() );
					ps.setInt(2,goods.getGoodsCode() );
				 
			result = ps.executeUpdate();
				System.out.println("상품 수정완료");

			}finally {
				DbUtil.dbClose(con, ps);
			}
			
			return result;
		}
		//수정
		public int GoodsUpadteSo(Goods goods) throws SQLException {

	        Connection con=null;
			PreparedStatement ps =null;
			int result = 0;
			String sql= proFile.getProperty("goods.updateSoldOutByCode");
		    
			try {
				 con = DbUtil.getConnection();
				 ps = con.prepareStatement(sql);
				 
					ps.setString(1,goods.getSoldOut() );
					ps.setInt(2,goods.getGoodsCode() );
				 
			result = ps.executeUpdate();
				System.out.println("상품 수정완료");

			}finally {
				DbUtil.dbClose(con, ps);
			}
			
			return result;
		}
		
		//수정
		public int GoodsUpadteSt(Goods goods) throws SQLException {

	        Connection con=null;
			PreparedStatement ps =null;
			int result = 0;
			String sql= proFile.getProperty("goods.updateStockByCode");
		    
			try {
				 con = DbUtil.getConnection();
				 ps = con.prepareStatement(sql);
				 
					ps.setInt(1,goods.getStock() );
					ps.setInt(2,goods.getGoodsCode() );
				 
			result = ps.executeUpdate();
				System.out.println("상품 수정완료");

			}finally {
				DbUtil.dbClose(con, ps);
			}
			
			return result;
		}

	
	
	
		

    //삭제
	@Override
	public int GoodsDelete(int goodsNo) throws SQLException {
	AdminController adminController = new AdminController() ;	   
	   Connection con=null;
		
		PreparedStatement ps =null;
		String sql="delete from goods where goodsCode=(gcode)";
		
	 
		try	 {
		
			int result = ps.executeUpdate(); 
			System.out.println("상품이 삭제되었습니다." );
			
		} catch (Exception e) {
			System.err.println("상품삭제 실패하였습니다");
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return 0;
	
 	}


	//공지
	@SuppressWarnings("finally")
	@Override
	public int NoticePrint(Notice notice) throws SQLException {
		
		Connection con=null;
		
		PreparedStatement ps =null;
		String sql="insert into notice(notice_num, adminId, noticeDate, noticeTitel,noticeContent) "
				+ "values(?,?,sysdate,?,?,)";
		try {
		   con = DbUtil.getConnection();
		   ps = con.prepareStatement(sql);
		   
		   ps.setInt(1, notice.getNoticeNum());
		   ps.setString(2, notice.getAdminId());
		   ps.setString(3, notice.getNoticeDate());
		   ps.setString(4, notice.getNoticeTitel());
		   ps.setString(5, notice.getNoticeContent());

		   con.commit();
		   System.out.println("공지가 등록되었습니다.");
		   
		   //쿼리실행 - DB쪽으로 쿼리 전송!!

		  int result = ps.executeUpdate();
		}catch (Exception e) {
			System.err.println("공지등록에 실패하였습니다");

		}finally {
			DbUtil.dbClose(con, ps);

		return 0;
	}
	
	

	}


	@Override
	public List<Goods> goodsSelectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int GoodsUpdateName(Goods goods) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int GoodsUpdatePr(Goods goods) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int GoodsUpdateSo(Goods goods) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int GoodsUpdateSt(Goods goods) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
}
