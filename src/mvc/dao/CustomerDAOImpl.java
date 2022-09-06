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

	//로그인 
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
			 //ps=con.prepareStatement("select * from user_info where user_id = ? and user_pw = ?");
			 ps.setString(1, userId);
			 ps.setString(2, userPw);
			 
			 rs=ps.executeQuery();
			
			 
			 if(rs.next()) {
//				 Customer dto = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), 
//						 rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
				 customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), 
						 rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			 	}
			 
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }
		 
		return customer;
	}

	
	//아이디 찾기 
	@Override
	public Customer findId(String phonNum) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Customer customer=null;
		 
		 String sql=proFile.getProperty("userInfo.selectIdByPhone");
		 //폰번호를 입력하면 아이디 알려주는 쿼리 
		 //select user_id where phone_num = ?
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			
		if(phonNum==rs.getString(1)){
			System.out.println("아이디는 "+"입니다."); 
//			System.out.println("아이디는 "+userId+ "입니다."); 
			 
		 }else {
			System.out.println("일치하는 정보가 없습니다.");
		 }//if
			 
			 
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }
		 
		 return customer;
	}


	//비밀번호 찾기 
	@Override
	public Customer findPw(String userId, String phonNum) throws SQLException {
		Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Customer customer=null;
		 
		 String sql=proFile.getProperty("");
		 //아이디,폰번호 입력하면 비밀번호 알려주는 쿼리 
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			
		if(phonNum==rs.getString(1)){
			System.out.println("비밀번호는 "+ "입니다.");
//			System.out.println("비밀번호는 "+userPw+ "입니다.");
			 
		 }else {
			System.out.println("일치하는 정보가 없습니다.");
		 }//if
			 
			 
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }
		 
		 return customer;
		
	}


	//회원가입하기 
	@Override
	public Customer register(String userId, String userPw, String phonNum) throws SQLException {
		Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Customer customer=null;
		 
		 String sql=proFile.getProperty("");
		 //아이디, 비번, 폰번호 입력
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			
		if(rs.next()){
			new Customer(rs.getString(1), rs.getString(2), rs.getString(3), 
					 rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			//System.out.println("환영합니다 회원가입이 완료되었습니다 ^_^");
		 }//if
			 
			 
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }
		 
		 return customer;
	}
	

	//마이페이지 가기 
	@Override
	public Customer myPage(String userId) throws SQLException {
		Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 Customer customer=null;
		 
		 String sql=proFile.getProperty("");
		 //
		 
		 try {
			 con=DbUtil.getConnection();
			 ps=con.prepareStatement(sql);
			 rs=ps.executeQuery();
			
		if(rs.next()){
			new Customer(rs.getString(1), rs.getString(2), rs.getString(3), 
					 rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
		 }//if
			 
			 
		 }finally {
			DbUtil.dbClose(con, ps, rs);
		 }
		 
		 return customer;

		
		
		
	}


	


	
	

}
