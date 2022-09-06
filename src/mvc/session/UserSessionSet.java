package mvc.session;

import java.util.HashSet;
import java.util.Set;
import mvc.session.UserSession;

	//UserSesstion을 저장해주는 영역 

public class UserSessionSet {
	
	private static UserSessionSet us = new UserSessionSet();
	private Set<UserSession> set;
	
	private UserSessionSet() {
		set = new HashSet<>();
	}
	
	public static UserSessionSet getInstance() { 
		return us;
	}
	
	/**
	 * 사용자 찾기
	 * */
	public UserSession get(String sessionId) {
		for(UserSession session : set) {
			if(session.getSessionId().equals(sessionId)) {
				return session;
			}
		}
		return null;
	}
	
	/**
	 * 세션 객체 반환 
	 * */
	public Set<UserSession> getSet(){
		return set;
	}	
	
	/**
	 * 로그인 된 사용자 추가
	 * */
	public void add(UserSession userSession) {
		set.add(userSession);
	}
	
	/**
	 * 사용자 제거 - 로그아웃
	 * */
	public void remove(UserSession userSession) {
		set.remove(userSession); 
	}
		
}
