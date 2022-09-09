<<<<<<< HEAD
package mvc.dao;

import java.sql.SQLException;

import mvc.dto.Customer;

public interface CustomerDAO {
	
	/**
	 * 아이디, 비번 인수로 받아 로그인  
	 * select * from user_info where user_id = ? and user_pw = ?
	 */
	Customer login(String userId, String userPw) throws SQLException;
	
	/**
	 * 폰번호 인수로 받아 아이디 찾기 
	 * select user_id where phone_num = ?
	 */
	String findId(String phonNum)throws SQLException;
	
	/**
	 * 아이디, 폰번호 인수로 받아 비번 찾기 
	 */
	String findPw(String useId, String phonNum)throws SQLException;

	
	/**
	 * 아이디, 비번, 폰번호 인수로 받아 회원가입
	 * @throws SQLException  
	 * */
	int register(String userId, String userPw, String userName, String phoneNum, 
			String email, String pinNum, int stamp)
			throws SQLException;
	

	/**
	 * 아이디 인수로 받아 마이페이지가기 
	 * 
	 */
	Customer myPage(String userId)throws SQLException;

	
	/**
	 * 비밀번호 인수로 받아 개인정보 변경 
	 * @throws SQLException 
	 */
	int userInfoChange(String userPw)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 최근 주문내역 보기(쵀대 5개)
	 */
	Customer selectOrderRecent(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 나만의 메뉴 만들기 
	 */
	Customer myMenu(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 스탬프 조회
	 */
	String myStamp(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 쿠폰 조회 
	 */
	Customer myCp(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 별점 조회 
	 */
	String myStar(String userId)throws SQLException;

	

	
	
	
	
	
}
=======
package mvc.dao;

import java.sql.SQLException;

import mvc.dto.Customer;

public interface CustomerDAO {
	
	/**
	 * 아이디, 비번 인수로 받아 로그인  
	 * select * from user_info where user_id = ? and user_pw = ?
	 */
	Customer login(String userId, String userPw) throws SQLException;
	
	/**
	 * 폰번호 인수로 받아 아이디 찾기 
	 * select user_id where phone_num = ?
	 */
	String findId(String phonNum)throws SQLException;
	
	/**
	 * 아이디, 폰번호 인수로 받아 비번 찾기 
	 */
	String findPw(String useId, String phonNum)throws SQLException;

	
	/**
	 * 아이디, 비번, 폰번호 인수로 받아 회원가입
	 * @throws SQLException  
	 * */
	int register(String userId, String userPw, String userName, String phoneNum, 
			String email, String pinNum, int stamp)
			throws SQLException;
	

	/**
	 * 아이디 인수로 받아 마이페이지가기 
	 * 
	 */
	Customer myPage(String userId)throws SQLException;

	
	/**
	 * 비밀번호 인수로 받아 개인정보 변경 
	 * @throws SQLException 
	 */
	int userInfoChange(String userPw)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 최근 주문내역 보기(쵀대 5개)
	 */
	Customer selectOrderRecent(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 나만의 메뉴 만들기 
	 */
	Customer myMenu(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 스탬프 조회
	 */
	String myStamp(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 쿠폰 조회 
	 */
	Customer myCp(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 별점 조회 
	 */
	String myStar(String userId)throws SQLException;

	

	
	
	
	
	
}
>>>>>>> 1af28d95296b0673b837018b49db91bf6fa558cd
