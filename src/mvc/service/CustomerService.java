<<<<<<< HEAD
package mvc.service;

import java.sql.SQLException;

import mvc.dao.CustomerDAO;
import mvc.dao.CustomerDAOImpl;
import mvc.dto.Customer;
import mvc.dto.MyStar;
import mvc.exception.NotFoundException;
import mvc.session.UserSession;
import mvc.session.UserSessionSet;
import mvc.view.MenuView;

public class CustomerService {
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
		if(customer==0) { //ID값이 중복으로 입력될 경우이걸 어트케 써야하나... 하...  
			throw new SQLException("이미 등록된 회원입니다. 테스트2");
		}
		//앤드뷰로 이동합시다 근데 앤드뷰는 컨드롤러에서 가는 건데 여기서 가도됨?  
		System.out.println("환영합니다 회원가입이 완료되었습니다 ^_^");
		return customer;
	}
	
	/**
	 * 마이페이지가기 
	 */
	public Customer myPage(String userId) throws SQLException, NotFoundException {
		Customer customer=customerDao.myPage(userId);
		if(customer==null) {//null이면 바꾸하게 할라했는데 왜 회원도 로그인 후 사용하래... 
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요. 테스트2");
		}
		return customer;
	}

	/**
	 * 마이페이지->개인정보 보여주기 
	  * */
	public Customer userInfoChange(String userId, String userPw) throws NotFoundException, SQLException {
		Customer customer=customerDao.userInfoChange(userId, userPw);
		//customer안에 있는 pw랑 입력받은 pw랑 맞는지 
		//if(customer.getUserPw()!=userPw) {//테스트를 위해 잠시 꺼둠 
		  if(customer==null) {//문제 : 비번틀렸는데도 개인정보페이지로 넘어감 
			throw new NotFoundException("등록된 비밀번호가 없습니다.");
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
	
	/**
	 * 마이페이지->최근주문내역 조회 
	  * */
	public Customer selectOrderRecent(String userId) throws NotFoundException, SQLException {
		Customer customer=customerDao.selectOrderRecent(userId);
		if(customer==null) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		return customer;
	}
	
	/**
	 * 마이페이지->나만의 메뉴(null) 
	  * */
	public Customer myMenu(String userId) throws NotFoundException, SQLException {
		Customer customer=customerDao.myMenu(userId);
		/*
		if(customer==null) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		*/
		return customer;
	}
	
	/**
	 * 마이페이지->스탬프 조회(not null) 
	  * */
	public int myStamp(String userId) throws NotFoundException, SQLException {
		int myStamp=customerDao.myStamp(userId);
		//스탬프 0개 가진 회원 조회하니까 에러나서 잠시 꺼둠 
		if(myStamp==-1) {
			throw new NotFoundException("실패했습니다.");
		}
		return myStamp;
	}
	
	
	


	
	
	
	
	
	
}
=======
<<<<<<< HEAD
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
	 * catchUserPw -> 찾은 비번 아이디 
	 * 저스트 찾을 아이디반 리턴해주면 되기 때문에 세션,세션셋이 필요가 없음요 
	 */
	public String findPw(String phonNum, String userId)throws NotFoundException , SQLException {
		String catchUserPw=customerDao.findPw(phonNum, userId);
		
		if(catchUserPw==null) { //입력받은 폰번호,아이디가 없는 정보면 null이면? 이게 맞는건가? 
			throw new NotFoundException("일치하는 정보가 없습니다.");
		}
			//서비스는 성공했을 때 어떻네 나와야하는지 처리 그럼 여기서 이게 맞음? --> 여기서 말고 앤드뷰로 
			System.out.println("비밀번호는 "+catchUserPw+ "입니다.");
			
			//저스트 찾을 아이디반 리턴해주면 되기 때문에 세션,세션셋이 필요가 없음요 
		
		return catchUserPw;
	}
	
	/**
	 * 회원가입하기 
	 */
	public int register(String userId, String userPw, String userName, String phoneNum,
			String email, String pinNum, int stamp) throws NotFoundException, SQLException {
		int customer=customerDao.register(userId, userPw, userName, phoneNum, email, pinNum, stamp);
		
		if(customer==0) { //ID값이 중복으로 입력될 경우이걸 어트케 써야하나... 하...  
			throw new SQLException("이미 등록된 회원입니다.");
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
	 * 마이페이지 
	 * 비번인수로 받아 개인정보 변경 
	  * */
	public int userInfoChange(String userPw) throws NotFoundException, SQLException {
		int customer=customerDao.userInfoChange(userPw);
		
		if(customer==0) {
			throw new NotFoundException("비밀번호 오류입니다.");
		}
		
		UserSession userSession = new UserSession(userPw); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);		
	
		return customer;
	}
	
	/**
	 * 마이페이지
	 * 최근주문내역 조회 
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
	 * 마이페이지
	 * 나만의 메뉴 
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
	 * 마이페이지
	 * 스탬프 조회 
	  * */
	public int myStamp(String userId) throws NotFoundException, SQLException {
		int myStamp=customerDao.myStamp(userId);
		
		if(myStamp==0) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);	
		
		return myStamp;
	}
	
	
	/**
	 * 마이페이지
	 * 내가 쓴 리뷰보기 
	  * */
	public int myStar(String userId) throws NotFoundException, SQLException {
		int myStar=customerDao.myStar(userId);
		
		if(myStar==0) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		//이거 앤드뷰로 가야됨 
		System.out.println(userId+ "님이 작성하신 리뷰는 총"+"개입니다.");
		
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);	
		
		return myStar;
	}
	
	
	
	
	
	
}
=======
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
	 * catchUserPw -> 찾은 비번 아이디 
	 * 저스트 찾을 아이디반 리턴해주면 되기 때문에 세션,세션셋이 필요가 없음요 
	 */
	public String findPw(String phonNum, String userId)throws NotFoundException , SQLException {
		String catchUserPw=customerDao.findPw(phonNum, userId);
		
		if(catchUserPw==null) { //입력받은 폰번호,아이디가 없는 정보면 null이면? 이게 맞는건가? 
			throw new NotFoundException("일치하는 정보가 없습니다.");
		}
			//서비스는 성공했을 때 어떻네 나와야하는지 처리 그럼 여기서 이게 맞음? --> 여기서 말고 앤드뷰로 
			System.out.println("비밀번호는 "+catchUserPw+ "입니다.");
			
			//저스트 찾을 아이디반 리턴해주면 되기 때문에 세션,세션셋이 필요가 없음요 
		
		return catchUserPw;
	}
	
	/**
	 * 회원가입하기 
	 */
	public int register(String userId, String userPw, String userName, String phoneNum,
			String email, String pinNum, int stamp) throws NotFoundException, SQLException {
		int customer=customerDao.register(userId, userPw, userName, phoneNum, email, pinNum, stamp);
		
		if(customer==0) { //ID값이 중복으로 입력될 경우이걸 어트케 써야하나... 하...  
			throw new SQLException("이미 등록된 회원입니다.");
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
	 * 마이페이지 
	 * 비번인수로 받아 개인정보 변경 
	  * */
	public int userInfoChange(String userPw) throws NotFoundException, SQLException {
		int customer=customerDao.userInfoChange(userPw);
		
		if(customer==0) {
			throw new NotFoundException("비밀번호 오류입니다.");
		}
		
		UserSession userSession = new UserSession(userPw); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);		
	
		return customer;
	}
	
	/**
	 * 마이페이지
	 * 최근주문내역 조회 
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
	 * 마이페이지
	 * 나만의 메뉴 
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
	 * 마이페이지
	 * 스탬프 조회 
	  * */
	public int myStamp(String userId) throws NotFoundException, SQLException {
		int myStamp=customerDao.myStamp(userId);
		
		if(myStamp==0) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);	
		
		return myStamp;
	}
	
	
	/**
	 * 마이페이지
	 * 내가 쓴 리뷰보기 
	  * */
	public int myStar(String userId) throws NotFoundException, SQLException {
		int myStar=customerDao.myStar(userId);
		
		if(myStar==0) {
			throw new NotFoundException("회원만 사용가능합니다. 로그인 후 이용해주세요.");
		}
		
		//이거 앤드뷰로 가야됨 
		System.out.println(userId+ "님이 작성하신 리뷰는 총"+"개입니다.");
		
		
		UserSession userSession = new UserSession(userId); 
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();		
		userSessionSet.add(userSession);	
		
		return myStar;
	}
	
	
	
	
	
	
}
>>>>>>> da39303894f84cf203d021d702f2bf752d5c6d83
>>>>>>> 65025e7d7839d243ef049936358ef77ac92f6cc6
