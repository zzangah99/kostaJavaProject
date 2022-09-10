package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import mvc.controller.AdminController;
import mvc.dto.Admin;
import mvc.dto.Goods;
import mvc.dto.Notice;
import mvc.dto.OrderDetail;
import mvc.util.DbUtil;
import java.util.Properties;

public class AdminDAOImpl implements AdminDAO {
	GoodsDAO goodsDao = new GoodsDAOImpl();


	private Properties proFile = DbUtil.getProfile();


	@Override
	public Admin login(String adminId, String adminPw) throws SQLException {
	
		  Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  Admin admin=null;
		 try {
			// System.out.println("admindaoimpl login111");
		   con = DbUtil.getConnection();
		   //System.out.println("admindaoimpl login222");
		   ps= con.prepareStatement("select * from admin where admin_id = ? and admin_pw = ?");
		   //System.out.println("admindaoimpl login333");
		  System.out.println(adminId);
		  System.out.println(adminPw);
		  
		   ps.setString(1, adminId);
		   ps.setString(2, adminPw);
		   //System.out.println("admindaoimpl login444");
	        rs = ps.executeQuery(); 
	        //System.out.println("admindaoimpl login555");
	        
	        if(rs.next()) {
	        	//System.out.println("admindaoimpl login6666");
	        	//System.out.println(rs.getString(2));
	        	//System.out.println(rs.getString(1));
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
	@Override
	public int GoodsUpdateName(Goods goodsName) throws SQLException {

        Connection con=null;
		PreparedStatement ps =null;
		int result = 0;
		String sql= proFile.getProperty("goods.updateNameByCode");//update goods set goods_name = ? where goods_code = ?
	    
		try {
			 con = DbUtil.getConnection();
			 ps = con.prepareStatement(sql);
			 
				ps.setString(1,goodsName.getGoodsName() );
				ps.setInt(2,goodsName.getGoodsCode() );
			 
		result = ps.executeUpdate();
			System.out.println("상품 수정완료");

		}finally {
			DbUtil.dbClose(con, ps);
		}
		
		return result;
	}
	
	//수정
	@Override
		public int GoodsUpdatePr(Goods goodsCode) throws SQLException {

	        Connection con=null;
			PreparedStatement ps =null;
			int result = 0;
			String sql= proFile.getProperty("goods.updatePriceByCode");
			try {
				 con = DbUtil.getConnection();
				 ps = con.prepareStatement(sql);
				 
					ps.setInt(1,goodsCode.getGoodsPrice() );
					ps.setInt(2,goodsCode.getGoodsCode() );
				 
			result = ps.executeUpdate();
				System.out.println("상품 수정완료");

			}finally {
				DbUtil.dbClose(con, ps);
			}
			
			return result;
		}
		//수정
		@Override
		public int GoodsUpdateSo(Goods goodsCode) throws SQLException {

	        Connection con=null;
			PreparedStatement ps =null;
			int result = 0;
			String sql= proFile.getProperty("goods.updateSoldOutByCode");
		    
			try {
				 con = DbUtil.getConnection();
				 ps = con.prepareStatement(sql);
				 
					ps.setString(1,goodsCode.getSoldOut() );
					ps.setInt(2,goodsCode.getGoodsCode() );
				 
			result = ps.executeUpdate();
				System.out.println("상품 수정완료");

			}finally {
				DbUtil.dbClose(con, ps);
			}
			
			return result;
		}
		
		//수정
		@Override
		public int GoodsUpdateSt(Goods goodsCode) throws SQLException {

	        Connection con=null;
			PreparedStatement ps =null;
			int result = 0;
			String sql= proFile.getProperty("goods.updateStockByCode");
		    
			try {
				 con = DbUtil.getConnection();
				 ps = con.prepareStatement(sql);
				 
					ps.setInt(1,goodsCode.getStock() );
					ps.setInt(2,goodsCode.getGoodsCode() );
				 
			result = ps.executeUpdate();
				System.out.println("상품 수정완료");

			}finally {
				DbUtil.dbClose(con, ps);
			}
			
			return result;
		}

	
	
	
		

    //삭제
	@Override
	public int GoodsDelete() throws SQLException {
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
	public String noticeprint() throws SQLException {
		  Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  String notice = "";

		  String sql = proFile.getProperty("notice.selectLatestContent");	  
		  try {
			  con = DbUtil.getConnection();
			  ps= con.prepareStatement(sql);

		      rs = ps.executeQuery(); 
		 
		  if(rs.next()) {
			  notice = rs.getString(1);
		  }
		  
		  }finally {
			  DbUtil.dbClose(con, ps, rs);
		  }
		  	return notice;
		
	}

	/*
	 통계보기
	 */
	   //일통계보기

	public OrderDetail getTodaysTotalOrderDetail() throws SQLException {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
		Date date = new Date(System.currentTimeMillis());
		String curDate = formatter.format(date);
		curDate = curDate.substring(2);

		 Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  OrderDetail orderDetail = null;

		  String sql = proFile.getProperty("tongye.selectByDate");	  
		  try {
			  con = DbUtil.getConnection();
			  ps= con.prepareStatement(sql);
			  
			  ps.setString(1,curDate);
		      rs = ps.executeQuery(); 
		 
		  if(rs.next()) {
			  orderDetail = new OrderDetail(rs.getInt(1),rs.getInt(2));
		  }
		  
		  }finally {
			  DbUtil.dbClose(con, ps, rs);
		  }
		  	return orderDetail;
		
	}
	
	//======
	   //월 통계보기

	public OrderDetail getMonthTotalOrderDetail() throws SQLException {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
		Date date = new Date(System.currentTimeMillis());
		String curDate = formatter.format(date);
		curDate = curDate.substring(2);

		 Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  OrderDetail orderDetail = null;

		  String sql = proFile.getProperty("tongye.selectByMonth");	  
		  try {
			  con = DbUtil.getConnection();
			  ps= con.prepareStatement(sql);
			  
			  ps.setString(1,curDate);
		      rs = ps.executeQuery(); 
		 
		  if(rs.next()) {
			  orderDetail = new OrderDetail(rs.getInt(1),rs.getInt(2));
		  }
		  
		  }finally {
			  DbUtil.dbClose(con, ps, rs);
		  }
		  	return orderDetail;
		
	}


	@Override
	public List<Goods> goodsSelectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int GoodsUpdateName(String goods) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int GoodsUpdateSo(int goodsCode) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int GoodsUpdateSt(int goodsCode) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int GoodsUpdatePr(int goodsCode) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}







}
