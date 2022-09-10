<<<<<<< HEAD
package mvc.dao;

import java.sql.SQLException;

import mvc.dto.Customer;
import mvc.dto.MyStar;

public interface CustomerDAO {
	
	/**
	 * 로그인  
	 */
	Customer login(String userId, String userPw) throws SQLException;
	
	/**
	 * 아이디 찾기 
	 */
	String findId(String phonNum)throws SQLException;
	
	/**
	 * 비번 찾기 
	 */
	String findPw(String useId, String phonNum)throws SQLException;

	/**
	 * 회원가입
	 * */
	int register(String userId, String userPw, String userName, String phoneNum, 
			String email, String pinNum, int stamp)
			throws SQLException;
	
	/**
	 * 마이페이지가기 
	 */
	Customer myPage(String userId)throws SQLException;

	/**
	 * 개인정보 보여주기  
	 */
	Customer userInfoChange(String userId, String userPw)throws SQLException;

	/**
	 * 개인정보 변경->닉네임 
	 */
	int userInfoChangeName(String userId, String userName) throws SQLException;

	/**
	 * 개인정보 변경->폰번호  
	 * @throws SQLException 
	 */
	int userInfoChangePhoneNum(String userId, String phoneNum) throws SQLException;

	/**
	 * 개인정보 변경->비번  
	 * @throws SQLException 
	 */
	int userInfoChangePw(String userId, String userPw) throws SQLException;

	/**
	 * 개인정보 변경->이메일  
	 * @throws SQLException 
	 */
	int userInfoChangeEmail(String userId, String eamil) throws SQLException;
	
	
	/**
	 * 마이페이지->최근 주문내역 보기(쵀대 5개)
	 */
	Customer selectOrderRecent(String userId)throws SQLException;

	/**
	 * 마이페이지->나만의 메뉴 만들기 
	 */
	Customer myMenu(String userId)throws SQLException;

	/**
	 * 마이페이지->스탬프 조회
	 */
	int myStamp(String userId)throws SQLException;

	/**
	 * 마이페이지->쿠폰 조회 
	 */
	Customer myCp(String userId)throws SQLException;

	

	

	

	
	
	
	
	
}
=======
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
	int myStamp(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 쿠폰 조회 
	 */
	Customer myCp(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 별점 조회 
	 */
	int myStar(String userId)throws SQLException;

	

	
	
	
	
	
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
	int myStamp(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 쿠폰 조회 
	 */
	Customer myCp(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 별점 조회 
	 */
	int myStar(String userId)throws SQLException;

	

	
	
	
	
	
}
>>>>>>> da39303894f84cf203d021d702f2bf752d5c6d83
>>>>>>> 65025e7d7839d243ef049936358ef77ac92f6cc6
