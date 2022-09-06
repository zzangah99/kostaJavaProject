package mvc.dao;

import java.sql.SQLException;

import mvc.dto.Customer;

public interface CustomerDAO {
	
	//로그인 
	Customer login(String userId, String userPw) throws SQLException;
	
	//아이디 찾기 
	Customer findId(String phonNum)throws SQLException;
	
	//비밀번호 찾기 
	Customer findPw(String useId, String phonNum)throws SQLException;

    //회원가입하기 
	Customer register(String userId, String userPw, String phonNum)throws SQLException;

	//마이페이지 가기 
	Customer myPage(String userId)throws SQLException;
}
