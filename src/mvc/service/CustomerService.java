package mvc.service;

import java.sql.SQLException;

import mvc.dao.CustomerDAO;
import mvc.dao.CustomerDAOImpl;
import mvc.dto.Customer;
import mvc.exception.NotFoundException;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;
import mvc.view.MenuView;

public class CustomerService {
	//CustomerDAO customerDao = new CustomerDAOImpl();
	CustomerDAO customerDao = new CustomerDAOImpl();
	
	/**
	 * 로그인
	 */
	public Customer login(String userId, String userPw)throws NotFoundException , SQLException {
		Customer customer = customerDao.login(userId, userPw);
		if(customer==null) {
			throw new NotFoundException("아이디 | 비밀번호가 틀렸습니다.");
		}
		//로그인된 정보 세션객체 만들어서 저장 
		UserSession userSession = new UserSession(userId); //회원 하나하나에 대한 목록 
		UserSessionSet userSessionSet = UserSessionSet.getInstance();//로그인한 사람 모음 
		userSessionSet.add(userSession);
		return customer;
	}

	/**
	 * 아이디 찾기 
	 * catchUserId : 찾은 아이디 
	 */
	public String findId(String phonNum)throws NotFoundException , SQLException  {
		String catchUserId = customerDao.findId(phonNum);
		if(catchUserId==null) {
			throw new NotFoundException("일치하는 정보가 없습니다.");
		}
		//앤드뷰로 이동합시다  
		System.out.println("아이디는 "+catchUserId+ "입니다.");
		return catchUserId;
	}

	/**
	 * 비밀번호 찾기 
	 * catchUserPw : 찾은 비번  
	 */
	public String findPw(String phonNum, String userId)throws NotFoundException , SQLException {
		String catchUserPw=customerDao.findPw(phonNum, userId);
		if(catchUserPw==null) { 
			throw new NotFoundException("일치하는 정보가 없습니다.");
		}
		//앤드뷰로 이동합시다  
		System.out.println("비밀번호는 "+catchUserPw+ "입니다.");
		return catchUserPw;
	}
	
	/**
	 * 회원가입하기 
	 */
	public int register(String userId, String userPw, String userName, String phoneNum,
			String email, String pinNum, int stamp) throws NotFoundException, SQLException {
		int customer=customerDao.register(userId, userPw, userName, phoneNum, email, pinNum, stamp);
		if(customer==0) { 
			throw new NotFoundException("실패했습니다.");
		}
		return customer;
	}
	
	/**
	 * 마이페이지가기 
	 */
	public Customer myPage(String userId) throws SQLException, NotFoundException {
		Customer customer=customerDao.myPage(userId);
		if(customer==null) {
			throw new NotFoundException("실패했습니다");
		}
		return customer;
	}

	/**
	 * 마이페이지->개인정보 보여주기 
	  * */
	public Customer userInfoChange(String userId, String userPw) throws NotFoundException, SQLException {
		Customer customer=customerDao.userInfoChange(userId, userPw);
		//customer안에 있는 pw랑 입력받은 pw랑 맞는지 
		//if(customer.getUserPw()==userPw) {//테스트를 위해 잠시 꺼둠 
		if(customer==null) {//문제 : 비번틀렸는데도 개인정보페이지로 넘어감 
			throw new NotFoundException("실패했습니다.");
		}
		return customer;
	}
	
	/**
	 * 마이페이지->개인정보 변경->닉네임(null)
	 */
	public int userInfoChangeName(String userId, String userName) throws SQLException {
		int userInfoChangeName=customerDao.userInfoChangeName(userId, userName);
		//닉네임은 null이 가능하니까 굳이 잡아줄 필요는 없으나 에러 처리한다고 나쁜건 없음 근데 설명을 잘 이해 못했음 
		if(userInfoChangeName==0) {
			throw new SQLException("실패했습니다.");
		}
		return userInfoChangeName;
	}

	/**
	 * 마이페이지->개인정보 변경->폰번호(not null) 
	 */
	public int userInfoChangePhoneNum(String userId, String phoneNum) throws NotFoundException, SQLException {
		int userInfoChangePhoneNum=customerDao.userInfoChangePhoneNum(userId, phoneNum);
		if(userInfoChangePhoneNum==0) {
			throw new SQLException("휴대폰 번호를 입력해주세요.");
		}
		return userInfoChangePhoneNum;
	}

	/**
	 * 마이페이지->개인정보 변경->비번(not null)  
	 */
	public int userInfoChangePw(String userId, String userPw) throws NotFoundException, SQLException {
		int userInfoChangePw=customerDao.userInfoChangePw(userId, userPw);
		if(userInfoChangePw==0) {
			throw new NotFoundException("비밀번호를 입력해주세요.");
		}
		return userInfoChangePw;
	}
	
	/**
	 * 마이페이지->개인정보 변경->이메일(null) 
	 */
	public int userInfoChangeEmail(String userId, String email) throws SQLException {
		int userInfoChangeEmail=customerDao.userInfoChangeEmail(userId, email);
		if(userInfoChangeEmail==0) {
			throw new SQLException("실패했습니다.");
		}
		return userInfoChangeEmail;
	}
	
	
	
	

	


	
	
	
	
	
	
}
