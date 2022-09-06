package mvc.session;

import java.util.HashSet;
import java.util.Set;

public class SessionSet {

	private static SessionSet sessionSet = new SessionSet();
	private Set<UserSession> set;

	private SessionSet() {
		set = new HashSet<>();
	}

	public static SessionSet getInstance() {
		return sessionSet;
	}

	/**
	 * 사용자 찾기
	 */
	public UserSession get(String userSessionId) {
		for (UserSession userSession : set) {
			if (userSession.getSessionId().equals(userSessionId)) {
				return userSession;
			}
		}
		return null;
	}

	public Set<UserSession> getSet() {
		return set;
	}

	/**
	 * 로그인 된 사용자 추가
	 */
	public void add(UserSession userSessionSet) {
		set.add(userSessionSet);
	}

	/**
	 * 사용자 제거 - 로그아웃
	 */
	public void remove(UserSession userSessionSet) {
		set.remove(userSessionSet);
	}
}
