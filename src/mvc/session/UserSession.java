package mvc.session;

import java.util.HashMap;
import java.util.Map;

public class UserSession {
		private String userSessionId; //로그인된 사용자 아이디 
		private Map<String,Object> attributes; //장바구니 
		private String userPw;    //로그인된 사용자 비번 
		private String userName;  //로그인된 사용자 닉네임  
		private String phoneNum;  //로그인된 사용자 폰번호 
		private String email;     //로그인된 사용자 이메일 
		private String pinNum;    //로그인된 사용자 생년월일 
		private int stamp;        //로그인된 사용자 스탬프 
		private String phonNum; //로그인된 사용자 폰번호 
	
	public UserSession(String userSessionId) {
		this.userSessionId = userSessionId;
		attributes = new HashMap<>();
	}

	
	//회원가입 
	//여기서 userid는 userSessionId이걸로 바꿔줘야하는지 
	//이런 생성자의 경우 this. 이런식으로 하나씩 쭉 써주는게 맞는지 
	public UserSession(String userId, String userPw, String userName, String phoneNum, String email, String pinNum,
			int stamp) {
		super();
		this.userSessionId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.pinNum =pinNum;
		this.stamp = stamp;
	}


	public String getSessionId() { //찾은 아이디 
		return userSessionId;
	}	
	
	//추가
	public void setAttribute(String name, Object value) {//cart , Map<Goods, Integer>
		attributes.put(name,value);
	}
		
	//조회(Map에 key에 해당하는 value 찾기)
	public Object getAttribute(String name) {//cart
		return attributes.get(name);
	}
		
	//제거(장바구니를 비울대 사용한다)
	public void removeAttribute(String name) {//cart
		attributes.remove(name);
	}

}
