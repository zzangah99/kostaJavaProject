package mvc.session;

import java.util.HashMap;
import java.util.Map;

import mvc.dto.Customer;

public class AdminSession {

	private String adminssionId;
	//private Map<String, Customer> attributes; //다른 회원 정보 저장
	
	public AdminSession() {}
	public AdminSession(String adminssionId) {
		this.adminssionId = adminssionId;
		//attributes = new HashMap<>();
	}

	public String getAdminSessionId() {
		return adminssionId;
	}

	public void setAdminSessionId(String adminssionId) {
		this.adminssionId = adminssionId;
	}

	@Override
	public String toString() {
		return "AdminSession [adminssionId=" + adminssionId + "]";
	}

	@Override
	public int hashCode() {
		return adminssionId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		AdminSession other = (AdminSession) obj;
		if (adminssionId.equals(other.adminssionId)) {
			return true;
		} else {
			return false;
		}

	}

}
