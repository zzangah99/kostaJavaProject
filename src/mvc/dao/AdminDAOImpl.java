package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import mvc.controller.AdminController;
import mvc.dto.Admin;
import mvc.dto.Goods;
import mvc.dto.Notice;
import mvc.dto.OrderDetail;
import mvc.util.DbUtil;

public class AdminDAOImpl implements AdminDAO {
	GoodsDAO goodsDao = new GoodsDAOImpl();


	private Properties proFile = DbUtil.getProfile();


	@Override
	public Admin login(String adminId, String adminPw) throws SQLException {
	
		  Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  Admin admin=null;
		  
		  String sql=proFile.getProperty("admin.selectLogin");
		 try {
			
		   con = DbUtil.getConnection();
		  
		   ps= con.prepareStatement(sql);
	
		  
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
	public int[] GoodsInsert(Goods goods) throws SQLException {
		
        Connection con=null;
		PreparedStatement ps =null;
		String sql = proFile.getProperty("goods.insertMd");
		int result[] =null;
		
		try {
		   con = DbUtil.getConnection();
		   ps = con.prepareStatement(sql);
		  
		  // ps.setInt(1, 12345);
		   ps.setString(1, goods.getGoodsName());
		   ps.setInt(2, goods.getGoodsPrice());
		   ps.setString(3, goods.getGoodsDetail());
		   ps.setString(4, goods.getSoldOut());
		   ps.setInt(5, goods.getStock());
		   ps.setInt(6, goods.getCategoryCode());
		   System.out.println(goods.getCategoryCode());
		   ps.addBatch(); //일괄처리할 작업에 추가
		   ps.clearParameters();
		   

		   result = ps.executeBatch();

	
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}
	
	//수정
	@Override
	public int GoodsUpdateName(Goods goods) throws SQLException {
	

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

		}finally {
			DbUtil.dbClose(con, ps);
		}
		
		return result;
	}
	
	//수정
  @Override
	public int GoodsUpdatePr(Goods goods) throws SQLException {

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

			}finally {
				DbUtil.dbClose(con, ps);
			}
			
			return result;
		}
	@Override
	public int GoodsUpdateDi(Goods goods) throws SQLException {

        Connection con=null;
		PreparedStatement ps =null;
		int result = 0;
		String sql= proFile.getProperty("goods.updateDetailByCode");
		try {
			 con = DbUtil.getConnection();
			 ps = con.prepareStatement(sql);
			 
				ps.setString(1,goods.getGoodsDetail() );
				ps.setInt(2,goods.getGoodsCode() );
			 
		result = ps.executeUpdate();

		}finally {
			DbUtil.dbClose(con, ps);
		}
		
		return result;
	}
	
		//수정
		@Override
		public int GoodsUpdateSo(Goods goods) throws SQLException {

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

			}finally {
				DbUtil.dbClose(con, ps);
			}
			
			return result;
		}

	
	
	
		

    //삭제
	@Override

	public int GoodsDelete(int goodsCode) throws SQLException {
	AdminController adminController = new AdminController() ;	   
	   Connection con=null;
		PreparedStatement ps =null;
		
		//nutrition.deleteByCode
		
		String sql = proFile.getProperty("goods.deleteByCode");
		int result = 0;
	
		try	 {
			  con = DbUtil.getConnection();
			  ps = con.prepareStatement(sql);
			   
			  ps.setInt(1, goodsCode );
		
			result = ps.executeUpdate(); 
			
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	
 	}


	//공지

	@Override
	public int NoticeInsert(Notice notice) throws SQLException {
		  Connection con=null;
		  PreparedStatement ps=null;
		  int result = 0;

		  String sql = proFile.getProperty("notice.insert"); 
		  //notice.insert=insert into notice (admin_id, notice_title, notice_content) values (?, ?, ?)
		
		  try {
			  con = DbUtil.getConnection();
			  ps= con.prepareStatement(sql);
			  ps.setString(1,notice.getAdminId() );
		   	  ps.setString(2,notice.getNoticeTitel() );
			  ps.setString(3,notice.getNoticeContent() );
		


			  result = ps.executeUpdate(); 
	
		  
		  }finally {
			  DbUtil.dbClose(con, ps);
		  }
		  	return result;
		
	}
	//공지
			@Override
			public String noticeprint() throws SQLException {
				  Connection con=null;
				  PreparedStatement ps=null;
				  ResultSet rs=null;
				  String notice = null;

				  String sql = proFile.getProperty("notice.selectContent"); //select notice_content from notice
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
	
	
	
	
	
	
	
	 //통계보기
	 
	   //일통계보기
	@Override
	public OrderDetail getTodaysTotalOrderDetail() throws SQLException {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
		Date date = new Date(System.currentTimeMillis());
		String curDate = formatter.format(date);
		System.out.println(curDate);
		curDate = curDate.substring(2);
		//System.out.println(curDate);

		 Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  OrderDetail orderDetail = null;

		  String sql = proFile.getProperty("tongye.selectByDate");	  
		  try {
			  con = DbUtil.getConnection();
			  ps= con.prepareStatement(sql);
			  
			  ps.setString(1,curDate);
			  ps.setString(2,curDate);
		
		      rs = ps.executeQuery(); 
		  
		 
		  if(rs.next()) {
			  //System.out.println("123456xxx");
			  orderDetail = new OrderDetail(rs.getString(1),rs.getInt(2));
			  System.out.println(orderDetail);
				}
		  
		  }finally {
			  DbUtil.dbClose(con, ps, rs);
		  }
		  	return orderDetail;
		
	}
	

	   //월 통계보기
	@Override
	
	public OrderDetail getMonthTotalOrderDetail() throws SQLException {
			SimpleDateFormat formatter= new SimpleDateFormat("yyyyMM");
			Date date = new Date(System.currentTimeMillis());
			String curDate = formatter.format(date);
			//System.out.println(curDate);
			curDate = curDate.substring(2);
			//System.out.println(curDate);

			 Connection con=null;
			  PreparedStatement ps=null;
			  ResultSet rs=null;
			  OrderDetail orderDetail = null;

			  String sql = proFile.getProperty("tongye.selectByMonth");	  
			  try {
				  con = DbUtil.getConnection();
				  ps= con.prepareStatement(sql);
				  
				  ps.setString(1,curDate);
				  ps.setString(2,curDate);
		 
			      rs = ps.executeQuery(); 
			 
			 
			  if(rs.next()) {
				  orderDetail = new OrderDetail(rs.getString(1),rs.getInt(2));
				  System.out.println(orderDetail);
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


}
