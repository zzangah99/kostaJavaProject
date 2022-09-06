package mvc.session;

public class AdminSession {

	private String adminssionId;

	public AdminSession() {}

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
