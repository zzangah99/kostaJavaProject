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
	Customer findId(String phonNum)throws SQLException;
	
	/**
	 * 아이디, 폰번호 인수로 받아 비번 찾기 
	 */
	Customer findPw(String useId, String phonNum)throws SQLException;

	/**
	 * 아이디, 비번, 폰번호 인수로 받아 회원가입 
	 * insert into user_info (user_id, user_pw, phone_num)values (?, ?, ?)
	 */
	int register(String userId, String userPw, String phonNum)throws SQLException;

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
	Customer myStamp(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 쿠폰 조회 
	 */
	Customer myCp(String userId)throws SQLException;

	
	/**
	 * 아이디 인수로 받아 별점 조회 
	 */
	Customer myStar(String userId)throws SQLException;
	
	
	
	
}
