package mvc.session;

import java.util.HashMap;
import java.util.Map;

public class UserSession {
		private String userSessionId; //로그인된 사용자 아이디 
		private Map<String,Object> attributes; //장바구니 
		private String phonNum;
	
	public UserSession(String userSessionId) {
		this.userSessionId = userSessionId;
		attributes = new HashMap<>();
	}
	
	public UserSession(String phonNum) {
		this.phonNum = phonNum;
		attributes = new HashMap<>();
	}
	
	public UserSession(String userId, String phonNum) {
		//customerservice 비밀번호 찾기 때문에 일단 만들어 놓았습니다. 
	}
	
	
	public String getSessionId() {
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
