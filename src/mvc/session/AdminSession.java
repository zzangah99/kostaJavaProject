package mvc.session;

import java.util.HashMap;
import java.util.Map;


public class AdminSession {

	private String adminssionId;
	private Map<String,Object> attributes;

	
	public AdminSession(String adminssionId) {
		this.adminssionId = adminssionId;
		attributes = new HashMap<>();
	}
	
	public AdminSession() {}

	public String getAdminSessionId() {
		return adminssionId;
	}

	public void setAdminSessionId(String adminssionId) {
		this.adminssionId = adminssionId;
	}



}
