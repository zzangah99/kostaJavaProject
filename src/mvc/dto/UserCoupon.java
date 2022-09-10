package mvc.dto;

/**
 * 사용가능 쿠폰 
 */
public class UserCoupon { 
	
	private String userCpCode; //pk
	private String cpCode;
	private String cpFlag; //쿠폰사용 여부 
	
	public UserCoupon() {
	}

	public UserCoupon(String userCpCode, String cpCode, String cpFlag) {
		super();
		this.userCpCode = userCpCode;
		this.cpCode = cpCode;
		this.cpFlag = cpFlag;
	}

	public String getUserCpCode() {
		return userCpCode;
	}

	public void setUserCpCode(String userCpCode) {
		this.userCpCode = userCpCode;
	}

	public String getCpCode() {
		return cpCode;
	}

	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}

	public String getCpFlag() {
		return cpFlag;
	}

	public void setCpFlag(String cpFlag) {
		this.cpFlag = cpFlag;
	}
	
	
	
}





	
   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


      
 