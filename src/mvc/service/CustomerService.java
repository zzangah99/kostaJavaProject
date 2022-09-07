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
	 * @throws NotFoundException 
	 */
	public Customer login(String userId, String userPw)throws NotFoundException , SQLException {
		Customer customer = customerDao.login(userId, userPw);
		
		if(customer==null) {
			throw new NotFoundException("아이디 | 비밀번호가 틀렸습니다.");
		}
		
		
		//로그인된 정보 세션객체 만들어서 저장 
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);
		
		return customer;
	}

	/**
	 * 아이디 찾기 
	 * @throws NotFoundException 
	 */
	public Customer findId(String phonNum)throws NotFoundException , SQLException  {
		Customer customer = customerDao.findId(phonNum);
		
		if(customer==null) {
			throw new NotFoundException("일치하는 정보가 없습니다.");
		}
		/*
		else {
			System.out.println("아이디는 " +customer+ "입니다.");
		}
		*/
		
		UserSession userSession = new UserSession(phonNum); //세션에서 public UserSession(String phonNum) 생성자 지웠는데 어떻게 해야될지 모르겠음  
		
		return customer;
	}

	/**
	 * 비밀번호 찾기 
	 * @throws NotFoundException 
	 */
	public Customer findPw(String userId, String phonNum)throws NotFoundException , SQLException {
		Customer customer=customerDao.findPw(userId, phonNum);
		
		if(customer==null) {
			throw new NotFoundException("일치하는 정보가 없습니다.");
		}
		/*
		else {
			System.out.println("비밀번호는 " +customer+ "입니다.");
		}
		*/
		UserSession userSession = new UserSession(userId, phonNum); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);
		
		return customer;
	}
	
	/**
	 * 회원가입하기 
	 * @throws NotFoundException 
	 */
	public int register(String userId, String userPw, String phonNum) throws NotFoundException, SQLException {
		int customer=customerDao.register(userId, userPw, phonNum);
		
		if(customer==0) { //조건문 어떻게 줘야할지 모르겠음  
			throw new NotFoundException("이미 등록된 회원입니다.");
		}
		/*
		else {
			System.out.println("환영합니다 회원가입이 완료되었습니다 ^_^");
		}
		*/
		
		UserSession userSession = new UserSession(userId, userPw, phonNum); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);
		return customer;
	}
	
	/**
	 * 마이페이지가기 
	 * @throws NotFoundException 
	 */
	public Customer myPage(String userId) throws SQLException, NotFoundException {
		Customer customer=customerDao.myPage(userId);
		
		if(customer==null) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);		
	
		return customer;
	}

	/**
	  * 개인정보 변경 
	 * @throws NotFoundException 
	 * @throws SQLException 
	  * */
	public int userInfoChange(String userPw) throws NotFoundException, SQLException {
		int customer=customerDao.userInfoChange(userPw);
		
		if(customer==0) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		UserSession userSession = new UserSession(userPw); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);		
	
		return customer;
	}
	
	/**
	  * 최근주문내역 조회 
	 * @throws NotFoundException 
	 * @throws SQLException 
	  * */
	public Customer selectOrderRecent(String userId) throws NotFoundException, SQLException {
		Customer customer=customerDao.selectOrderRecent(userId);
		
		if(customer==null) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);		
	
		return customer;
	}
	
	/**
	  * 나만의 메뉴 
	 * @throws NotFoundException 
	 * @throws SQLException 
	  * */
	public Customer myMenu(String userId) throws NotFoundException, SQLException {
		Customer customer=customerDao.myMenu(userId);
		
		if(customer==null) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);	
		
		return customer;
	}
	
	/**
	  * 스탬프 조회 
	 * @throws NotFoundException 
	 * @throws SQLException 
	  * */
	public Customer myStamp(String userId) throws NotFoundException, SQLException {
		Customer customer=customerDao.myStamp(userId);
		
		if(customer==null) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);	
		
		return customer;
	}
	
	
	/**
	  * 쿠폰 조회 
	 * @throws NotFoundException 
	 * @throws SQLException 
	  * */
	public Customer myCp(String userId) throws NotFoundException, SQLException {
		Customer customer=customerDao.myCp(userId);
		
		if(customer==null) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);	
		
		return customer;
	}

	
	/**
	  * 내가 쓴 리뷰보기 
	 * @throws NotFoundException 
	 * @throws SQLException 
	  * */
	public Customer myStar(String userId) throws NotFoundException, SQLException {
		Customer customer=customerDao.myStar(userId);
		
		if(customer==null) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);	
		
		return customer;
	}
	
	
	
	
	
	
}
