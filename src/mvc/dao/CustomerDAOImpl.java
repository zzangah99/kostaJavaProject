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
	 * 로그인  
	 */
	@Override
	public Customer login(String userId, String userPw) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Customer customer=null;
		 String sql=proFile.getProperty("userInfo.selectLogin");
		 //select * from user_info where user_id = ? and user_pw = ?
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 
			 //'?'있으니까 setXxx
			 ps.setString(1, userId);
			 ps.setString(2, userPw);
			 
			 //쿼리문 실행 
			 rs=ps.executeQuery();//이게 결과임 
			
			 if(rs.next()) {
				 //이 부분은 customer에서 생성한 생성자 순서와 같아야함 
				 customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), 
						 rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			 	}
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }
		return customer;
	}
	
	/**
	 * 아이디 찾기 
	 * catchUserId : 찾은 아이디 
	 */
	@Override
	public String findId(String phonNum) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 String catchUserId=null; 
		 String sql=proFile.getProperty("userInfo.selectIdByPhone");
		 //select user_id from user_info where phone_num = ?
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 ps.setString(1, phonNum);
			 rs=ps.executeQuery();
			
			 if(rs.next()) {
				 catchUserId=rs.getString(1); 
			 	}
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }
		 return catchUserId;
	}

	/**
	  * 비밀번호 찾기 
	  * catchUserPw : 찾은 비밀번호 
	  * */
	@Override
	public String findPw(String userId, String phoneNum) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 String catchUserPw=null;
		 String sql=proFile.getProperty("userInfo.selectPwPhoneByPhone");

		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 ps.setString(1, userId);
			 ps.setString(2, phoneNum);
			 rs=ps.executeQuery();
			
			 while(rs.next()) {
				 catchUserPw=rs.getString(1); 
			 	}//if
			 
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }
		 return catchUserPw;
	}

	/**
	  * 회원가입
	  * chatchRegister : 회원가입한 정보 
	  * */
	@Override
	public int register(String userId, String userPw, String userName, String phoneNum, 
		 String email, String pinNum, int stamp) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 int chatchRegister=0;
		 
		 //String sql=proFile.getProperty("userInfo.insert");
		 String sql="insert into user_info (user_id, user_pw, user_name, phone_num, email, pin_num, stamp) \r\n"
		 		+ "values (?, ?, ?, ?, ?, ?, ?)";
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 ps.setString(1,userId);
			 ps.setString(2,userPw);
			 ps.setString(3,userName);
			 ps.setString(4,phoneNum);
			 ps.setString(5,email);
			 ps.setString(6,pinNum);
			 ps.setInt(7,stamp);
			 
			 chatchRegister=ps.executeUpdate();
			 
		 }finally {
			DbUtil.dbClose(con, ps);
		 }
		 return chatchRegister;
	}

	/*
	 * 마이페이지
	 */
	@Override
	public Customer myPage(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * 마이페이지->개인정보 보여주기 
	 * 닉네임, 비번, 폰번호, 이메일, 가입일, 생년월일 
	 */
	public Customer userInfoChange(String userId, String userPw) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null; 
		 ResultSet rs=null;
		 Customer customer=null;
		 String sql=proFile.getProperty("userInfo.selectAllById2");
		 //select user_name, user_pw, phone_num, email, pin_num, reg_date from user_info where user_id = ?
		 con=DbUtil.getConnection();
		 ps=con.prepareStatement(sql);
		 ps.setString(1, userId);
		 rs=ps.executeQuery();
		 
		 if(rs.next()) {
			 customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					 rs.getString(5), rs.getString(6));
		 	}
		 return customer;
	}
	
	/**
	 * 개인정보 변경->닉네임 
	 * @throws SQLException 
	 */
	public int userInfoChangeName(String userId, String userName) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null; 
		 Customer customer=null;
		 int userInfoChangeName=0;
		 String sql=proFile.getProperty("userInfo.updateName");
		 //update user_info set user_name = ? where user_id = ?
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 ps.setString(1, userName);//이렇게 쓰고 싶으면 인수로 닉네임도 같이 들고 들어와야하는지 
			 ps.setString(2, userId);
			 userInfoChangeName=ps.executeUpdate(); //결과가 int 
		 }	 
		 finally {
				DbUtil.dbClose(con, ps);
		 }
		 return userInfoChangeName;
		 //성공 유무면 결과를 가지고 간다 
	}
	
	/**
	 * 개인정보 변경->폰번호(not null)  
	 * @throws SQLException 
	 */
	public int userInfoChangePhoneNum(String userId, String phoneNum) throws SQLException{
		 Connection con=null;
		 PreparedStatement ps=null; 
		 Customer customer=null;
		 int userInfoChangePhoneNum=0;
		 String sql=proFile.getProperty("userInfo.updatePhone");
		 //update user_info set phone_num = ? where user_id = ?
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 ps.setString(1, phoneNum);
			 ps.setString(2, userId);
			 userInfoChangePhoneNum=ps.executeUpdate();
		 }
		 finally {
			 DbUtil.dbClose(con, ps);
		 }
		 return userInfoChangePhoneNum;
	}
	
	/**
	 * 개인정보 변경->비번  
	 * @throws SQLException 
	 */
	public int userInfoChangePw(String userId, String userPw) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null; 
		 Customer customer=null;
		 int userInfoChangePw=0;
		 String sql=proFile.getProperty("userInfo.updatePw");
		 //update user_info set user_pw = ? where user_id = ?
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 ps.setString(1, userPw);
			 ps.setString(2, userId);
			 userInfoChangePw=ps.executeUpdate();
		 }
		 finally {
			 DbUtil.dbClose(con, ps);
		 }
		 return userInfoChangePw;
	}
	
	/**
	 * 개인정보 변경->이메일  
	 * @throws SQLException 
	 */
	public int userInfoChangeEmail(String userId, String email) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null; 
		 Customer customer=null;
		 int userInfoChangeEmail=0;
		 String sql=proFile.getProperty("userInfo.updateEmail");
		 //update user_info set email = ? where user_id = ?
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 ps.setString(1, email);
			 ps.setString(2, userId);
			 userInfoChangeEmail=ps.executeUpdate();
		 }
		 finally {
			 DbUtil.dbClose(con, ps);
		 }
		 return userInfoChangeEmail;
	}

	@Override
	public Customer userInfoChange(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	
	
		


	


	
	

}
