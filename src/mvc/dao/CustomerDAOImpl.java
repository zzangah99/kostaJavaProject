package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import mvc.dto.Customer;
import mvc.util.DbUtil;

public class CustomerDAOImpl implements CustomerDAO {
	
	private Properties proFile = DbUtil.getProfile();

	/**
	 * 아이디, 비번 인수로 받아 로그인  
	 * @throws SQLException  
	 */
	@Override
	public Customer login(String userId, String userPw) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Customer customer=null;
		 
		 String sql=proFile.getProperty("userInfo.selectLogin");
		 //아이디, 비번으로 로그인 
		 //select * from user_info where user_id = ? and user_pw = ?
		 //select * from user_info where user_id = 'firstid' and user_pw = 'first';
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 
			 //'?'있으니까 setXxx
			 ps.setString(1, userId);
			 ps.setString(2, userPw);
			 
			 //쿼리문 실행 
			 rs=ps.executeQuery();
			
			 if(rs.next()) {
				 //이 부분은 customer에서 생성한 생성자 순서와 같아야함 
				 customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), 
						 rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			 	}//if
			 
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }//try
		 
		return customer;
	}

	
	/**
	 * 폰번호 인수로 받아 아이디 찾기 
	 * @throws SQLException 
	 * catchUserId -> 찾은 아이디 
	 */
	@Override
	public String findId(String phonNum) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 //Customer customer=null;
		 String catchUserId=null; 
		 
		 String sql=proFile.getProperty("userInfo.selectIdByPhone");
		 //폰번호를 입력하면 아이디 알려주는 쿼리 
		 //select user_id FROM user_info where phone_num = ?
		 //select user_id FROM user_info where phone_num = '01012345678';
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 
			 //'?'있으니까 setXxx
			 ps.setString(1, phonNum);
			 
			//쿼리문 실행 
			 rs=ps.executeQuery();
			
			 if(rs.next()) {//쿼리로 뽑힌 컬럼으로 getXXX(index) 여기선 아이디컬럼 하나만 결과임 
				 catchUserId=rs.getString(1); //이게 결과임 
			 	}//if
			 
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }
		 
		 return catchUserId;//쿼리결과가 여기에 저장 
	}


	/**
	  * 아이디, 폰번호 인수로 받아 비밀번호 찾기 
	  * @throws SQLException 
	  * catchUserPw -> 찾은 비번 
	  * */
	@Override
	public String findPw(String userId, String phonNum) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 //Customer customer=null;
		 String catchUserPw=null;
		 
		 String sql=proFile.getProperty("userInfo.selectPwPhoneByPhone");
		 //폰번호, 아이디 입력하면 비밀번호 알려주는 쿼리 
		 //select user_pw where user_id = ? and phone_num = ?
		 //SELECT USER_PW  FROM user_info where phone_num = '01012345678' AND USER_ID='firstid';
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 
			 //'?'있으니까 setXxx
			 ps.setString(1, userId);
			 ps.setString(2, phonNum);
			 
			 //쿼리문 실행 
			 rs=ps.executeQuery();
			
			 if(rs.next()) {//쿼리로 뽑힌 컬럼으로 getXXX(index) 여기선 버번컬럼 하나만 결과임 
				 catchUserPw=rs.getString(1); //이게 결과임 
			 	}//if
			 
			 
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }
		 
		 return catchUserPw;
		
	}


	/**
	  * 아이디, 비번, 폰번호 인수로 받아 회원가입
	  * @throws SQLException  
	  * */
	@Override
	public int register(String userId, String userPw, String userName, String phoneNum, 
			String email,String pinNum, int stamp) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 int chatchRegister=0;
		 //Customer customer=null;
		 
		 String sql=proFile.getProperty("userInfo.insert");
		 //아이디, 비번, 폰번호 입력
		 /*
		  * insert into user_info (user_id, user_pw, user_name, phone_num, email, pin_num, stamp) 
		  * values (?, ?, ?, ?, ?, ?, 0)
		 */
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 
			 //'?'있으니까 setXxx
			 ps.setString(1,userId);
			 ps.setString(2,userPw);
			 ps.setString(3,userName);
			 ps.setString(4,phoneNum);
			 ps.setString(5,email);
			 ps.setString(6,pinNum);
			 ps.setInt(7,0);
		
			 //쿼리문 실행 
			 chatchRegister=ps.executeUpdate();
			 
		 }finally {
			DbUtil.dbClose(con, ps);
		 }
		 
		 return chatchRegister;
	}

	/*
	 * 아이디 인수로 받아 마이페이지 가기 
	 * 쿼리 어트케...?
	@Override
	 */
	public Customer myPage(String userId) throws SQLException {
		 
		 
		return null;
	}
	
	/**
	  * 아이디 인수로 받아 개인정보 변경(update)
	 * @throws SQLException 
	  * */
	@Override
	public int userInfoChange(String userPw) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 //ResultSet rs=null;
		 //Customer customer=null;
		 int result=0;
		 
		 //쿼리를 뭘 써야하나 
		 String sql=proFile.getProperty("");
		 //
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 
			 //아이디 받아와서 개인정보 변경을 해야되는데 엄.... 
			 //쿼리문이 저렇게 들어가는데 인수가 비번 하나면 안되는건가...? ㅇㅅㅇ?
			 //'?'있으니까 setXxx
			 /*
			 ps.setString(1, userPw.);
			 ps.setString(2, userPw);
			 ps.setString(3, userPw);
			 ps.setString(4, userPw);
			 ps.setString(5, userPw);
			 ps.setString(6, userPw);
			 ps.setInt(6, 0);
			 */
			 //쿼리문 실행 
			 result=ps.executeUpdate();
			
		 }finally {
			DbUtil.dbClose(con, ps);
		 }//try
		 
		return result;
	}

	
	/**
	  * 아이디 인수로 받아 최근주문내역 조회 
	 * @throws SQLException 
	  * */
	@Override
	public Customer selectOrderRecent(String userId) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Customer customer=null;
		 
		 String sql=proFile.getProperty("orderOrder.selectAllById");
		 //아이디로 최근주문 내역 조회 
		 //select * from order_order where user_id = ?
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 
			 //'?'있으니까 setXxx
			 ps.setString(1, userId);
			 
			 //쿼리문 실행 
			 rs=ps.executeQuery();
			
			 //이게 맞는지... 
			 if(rs.next()) {
				 customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), 
						 rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			 	}//if
			 
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }//try
		 
		return customer;

	}

	
	/**
	  * 아이디 인수로 받아 나만의 메뉴 조회 
	 * @throws SQLException 
	  * */
	@Override
	public Customer myMenu(String userId) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Customer customer=null;
		 
		 String sql=proFile.getProperty("myMenu.selectAl");
		 //select * from my_menu
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 
			 //쿼리문 실행 
			 rs=ps.executeQuery();
			 
			 if(rs.next()) {
				 customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), 
						 rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			 	}//if
			
		 }finally {
			DbUtil.dbClose(con, ps);
		 }//try
		 
		return customer;
	}
	
	
	/**
	  * 아이디 인수로 받아 나만의 메뉴 만들기  
	  * */
	
	
	
	/**
	  * 아이디 인수로 받아 스탬프 조회 
	 * @throws SQLException 
	  * */
	@Override
	public String myStamp(String userId) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 //Customer customer=null;
		 String myStamp=null;
		 
		 String sql=proFile.getProperty("userInfo.selectStampById");
		 //아이디로 스탬프 조회 
		 //select stamp from user_info where user_id = ?
		 //select stamp from user_info where user_id = 'yuna';
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 
			 //'?'있으니까 setXxx
			 ps.setString(1, userId);
			 
			 //쿼리문 실행 
			 rs=ps.executeQuery();
			
			 //이게 맞는지... 
			 if(rs.next()) {
				 myStamp=rs.getString(1);
			 	}//if
			
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }//try
		 
		return myStamp;

	}

	
	/**
	  * 아이디 인수로 받아 쿠폰 조회 
	 * @throws SQLException 
	@Override
	@Override
	*/
	public Customer myCp(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	  * 아이디 인수로 받아 내가 쓴 리뷰보기 
	 * @throws SQLException 
	  * */
	@Override
	public Customer myStar(String userId) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Customer customer=null;
		 
		 String sql=proFile.getProperty("review.selectAllById");
		 //아이디로 리뷰보기 
		 //select * from review where user_id = ? order by review_date
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 
			 //'?'있으니까 setXxx
			 ps.setString(1, userId);
			 
			 //쿼리문 실행 
			 rs=ps.executeQuery();
			
			 //이게 맞는지... 
			 if(rs.next()) {
				 customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), 
						 rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			 	}//if
			 
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }//try
		 
		return customer;

	}


	
	/**
	  * 아이디 인수로 받아 내가 쓴 리뷰쓰기 
	  * */

	
		
		
		


	


	
	

}
