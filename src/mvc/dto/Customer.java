package mvc.dto;

public class Customer {
	private String userId;
	private String userPw;
	private String userName;
	private String phoneNum;
	private String email;
	private String regDate; //가입일 
	private String pinNum;  //생년월일 
	private int stamp;
	
	
	public Customer() {
	}

	public Customer(String userId, String userPw, String userName, String phoneNum, String email, String regDate,
			String pinNum, int stamp) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.regDate = regDate;
		this.pinNum = pinNum;
		this.stamp = stamp;
	}

	/**
	 * 개인정보 보여주기
	 */
	               //user_name, user_pw, phone_num, email, reg_date, pin_num
	public Customer(String userName, String userPw, String phoneNum, String email, String pinNum, String regDate) {
		super();
		this.userName = userName;
		this.userPw = userPw;
		this.phoneNum = phoneNum;
		this.email = email;
		this.regDate = regDate;
		this.pinNum = pinNum;
	}
	
	public String getUserId() { //아이디 내놔라 
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getPinNum() {
		return pinNum;
	}

	public void setPinNum(String pinNum) {
		this.pinNum = pinNum;
	}

	public int getStamp() {
		return stamp;
	}

	public void setStamp(int stamp) {
		this.stamp = stamp;
	}
	
	
	
	
}
