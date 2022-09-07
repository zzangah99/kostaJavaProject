package mvc.session;


import java.util.HashSet;
import java.util.Set;


	// 저장

 public class AdminSessionSet {
	
	private static AdminSessionSet as = new AdminSessionSet();
	private Set<AdminSession> set;
	
	private AdminSessionSet() {
		set = new HashSet<>();
	}
	
	public static AdminSessionSet getInstance() { 
		return as;
	}
	

	
	/**
	 * 세션 객체 반환 
	 * */
	public Set<AdminSession> getSet(){
		return set;
	}	
	
	/**
	 * 로그인 된 사용자 추가
	 * */
	public void add(AdminSession adminSession) {
		set.add(adminSession);
	}
	
	/**
	 * 사용자 제거 - 로그아웃
	 * */
	public void remove(AdminSession adminSession) {
		set.remove(adminSession); 
	}
		
}
