package mvc.dto;


public class Admin {

	  private String adminId;
	  private String adminPw;

	  public Admin() {}
		public Admin(String adminId, String adminPw) {
		super();
		this.adminId = adminId;
		this.adminPw = adminPw;
		}



		public String getAdminId() {
			return adminId;
		}
		public void setAdminId(String userId) {
			this.adminId = adminId;
		}
		public String getAdminPw() {
			return adminPw;
		}
		public void getAdminPw(String userPwd) {
			this.adminPw = adminPw;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Admin [adminId=");
			builder.append(adminId);
			builder.append(", adminPw=");
			builder.append(adminPw);
			builder.append("]");
			return builder.toString();
		}

	}
