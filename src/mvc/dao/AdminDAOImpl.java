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

public class AdminDAOImpl implements AdminDAO {

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
		String sql="insert into goods(goodsCode, goodsName, goodsPrice, soldOut, stock,categoryCode) "
				+ "values(?,?,?,?,?,)";
		int result =0;
		
		try {
		   con = DbUtil.getConnection();
		   con.setAutoCommit(false);
		   
		   ps = con.prepareStatement(sql);
		  
		   ps.setString(1, Goods.getGoodsCode());
		   ps.setString(2, Goods.getGoodsName());
		   ps.setInt(3, Goods.getGoodsPrice());
		   ps.setString(4, Goods.getSoldOut());
		   ps.setInt(5, Goods.getStock());
		   ps.setString(6, Goods.getCategoryCode());
		   ps.addBatch(); //일괄처리할 작업에 추가
		   ps.clearParameters();
		   

		   result = ps.executeUpdate();
		  
		}catch (Exception e) {
			System.err.println("상품등록 실패하였습니다");
	
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}
	
	//수정
	@Override
	public int GoodsUpdate(Goods goods) throws SQLException {
		
		AdminController adminController = new AdminController() ;
		
        Connection con=null;
		PreparedStatement ps =null;
		String sql="update * from goods where goodsCode = gcode";
	 
		try {
			 con = DbUtil.getConnection();
			 ps = con.prepareStatement(sql);
			 
	//??????????????????????????????????????????????????????????
			 
			int result = ps.executeUpdate();
			System.out.println("상품 수정완료");

		}catch (Exception e) {
			//e.printStackTrace();
			System.err.println("상품수정 실패하였습니다");
		}finally {
			DbUtil.dbClose(con, ps);
		}
		
		return 0;
		
		
		
		
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
	@Override
	public int NoticePrint(Notice notice) throws SQLException {
		
		Connection con=null;
		
		PreparedStatement ps =null;
		String sql="insert into notice(notice_num, adminId, noticeDate, noticeTitel,noticeContent) "
				+ "values(?,?,sysdate,?,?,)";
		try {
		   con = DbUtil.getConnection();
		   ps = con.prepareStatement(sql);
		   
		   ps.setInt(1, Notice.getNoticeNum());
		   ps.setString(2, Notice.getAdminId());
		   ps.setString(3, Notice.getNoticeDate());
		   ps.setString(4, Notice.getNoticeTitel());
		   ps.setString(5, Notice.getNoticeContent());

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
}
