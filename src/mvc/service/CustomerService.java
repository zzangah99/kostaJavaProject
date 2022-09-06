package mvc.service;

import java.sql.SQLException;

import mvc.dao.CustomerDAO;
import mvc.dao.CustomerDAOImpl;
import mvc.dto.Customer;
import mvc.exception.NotFoundException;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;

public class CustomerService {
	CustomerDAO customerDao = new CustomerDAOImpl();
	
	/**
	 * 로그인
	 */
	public Customer login(String userId, String userPw)throws NotFoundException , SQLException {
		Customer customer = customerDao.login(userId, userPw);
		
		if(customer==null) {
			throw new NotFoundException("일치하는 정보가 없습니다.");
		}
		
		/**
		 * 로그인된 정보 세션객체 만들어서 저장 
		 */
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);
		
		return customer;
	}

	/**
	 * 아이디 찾기 
	 */
	public Customer findId(String phonNum)throws NotFoundException , SQLException  {
		Customer customer = customerDao.findId(phonNum);
		
		if(customer==null) {
			throw new NotFoundException("일치하는 정보가 없습니다.");
		}else {
			System.out.println("아이디는 " +customer+ "입니다.");
		}
		
		UserSession userSession = new UserSession(phonNum); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);
		
		return customer;
	}

	/**
	 * 비밀번호 찾기 
	 */
	public Customer findPw(String userId, String phonNum)throws NotFoundException , SQLException {
		Customer customer=customerDao.findPw(userId, phonNum);
		
		if(customer==null) {
			throw new NotFoundException("일치하는 정보가 없습니다.");
		}else {
			System.out.println("비밀번호는 " +customer+ "입니다.");
		}
		
		UserSession userSession = new UserSession(userId, phonNum); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);
		
		return customer;
	}
	
	/**
	 * 회원가입하기 
	 */
	public Customer register(String userId, String userPw, String phonNum) throws NotFoundException, SQLException {
		Customer customer=customerDao.register(userId, userPw, phonNum);
		
		if(customer==null) {
			throw new NotFoundException("이미 등록된 회원입니다.");
		}else {
			System.out.println("환영합니다 회원가입이 완료되었습니다 ^_^");
		}
		
		//UserSession userSession = new UserSession(userId, userPw, phonNum); 
		
		//UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		//userSessionSet.add(userSession);
		return customer;
	}
	
	/**
	 * 마이페이지가기 
	 */
	public Customer myPage(String userId) throws SQLException {
		Customer customer=customerDao.myPage(userId);
		
			//여기 모르겠다...
	
		return customer;
	}
}
