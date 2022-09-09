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
		UserSession userSession = new UserSession(userId); //회원 하나하나에 대한 목록 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();//로그인한 사람 모음 
		userSessionSet.add(userSession);
		
		return customer;
	}

	/**
	 * 폰번호 입력받아 아이디 찾기 
	 * @throws NotFoundException 
	 * catchUserId -> 찾은 유져 아이디 
	 */
	public String findId(String phonNum)throws NotFoundException , SQLException  {
		String catchUserId = customerDao.findId(phonNum);
		
		if(catchUserId==null) {
			throw new NotFoundException("일치하는 정보가 없습니다.");
		}//if
			
			//서비스는 성공했을 때 어떻네 나와야하는지 처리 그럼 여기서 이게 맞음? --> 여기서 말고 앤드뷰로 
			System.out.println("아이디는 "+catchUserId+ "입니다.");
			
			//저스트 찾을 아이디반 리턴해주면 되기 때문에 세션,세션셋이 필요가 없음요 
		
		return catchUserId;
	}

	/**
	 * 비밀번호 찾기 
	 * @throws NotFoundException 
	 * catchUserPw -> 찾은 비번 아이디 
	 * 저스트 찾을 아이디반 리턴해주면 되기 때문에 세션,세션셋이 필요가 없음요 
	 */
	public String findPw(String phonNum, String userId)throws NotFoundException , SQLException {
		String catchUserPw=customerDao.findPw(phonNum, userId);
		
		if(catchUserPw==null) {
			throw new NotFoundException("일치하는 정보가 없습니다.");
		}
			//서비스는 성공했을 때 어떻네 나와야하는지 처리 그럼 여기서 이게 맞음? --> 여기서 말고 앤드뷰로 
			System.out.println("비밀번호는 "+catchUserPw+ "입니다.");
			
			//저스트 찾을 아이디반 리턴해주면 되기 때문에 세션,세션셋이 필요가 없음요 
		
		return catchUserPw;
	}
	
	/**
	 * 회원가입하기 
	 * @throws NotFoundException 
	 */
	public int register(String userId, String userPw, String userName, String phoneNum,
			String email, String pinNum, int stamp) throws NotFoundException, SQLException {
		int customer=customerDao.register(userId, userPw, userName, phoneNum, email, pinNum, stamp);
		
		if(customer==0) { //조건문 어떻게 줘야할지 모르겠음  
			throw new NotFoundException("이미 등록된 회원입니다.");
		}
			//서비스는 성공했을 때 어떻네 나와야하는지 처리 그럼 여기서 이게 맞음? --> 여기서 말고 앤드뷰로
			System.out.println("환영합니다 회원가입이 완료되었습니다 ^_^");
		
		//이렇게 세션 생성자 만들어야하는지...? 
		UserSession userSession = new UserSession(userId, userPw, userName, phoneNum, email, pinNum, stamp); 
		
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
		
		
		return customer;
		//저스트 마이페이지만 가는것이기 때문에 세션,세션셋이 필요가 없음요 
	
	}

	/**
	 * 비번인수로 받아 개인정보 변경 
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
	public String myStamp(String userId) throws NotFoundException, SQLException {
		String myStamp=customerDao.myStamp(userId);
		
		if(myStamp==null) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);	
		
		return myStamp;
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
