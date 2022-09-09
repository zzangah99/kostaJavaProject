package mvc.dto;

public class Notice {
	
	private int noticeNum;
	private String adminId;
	private String noticeDate;
	private String noticeTitel;
	private String noticeContent;

	public Notice() {}

	public Notice(int noticeNum, String adminId, String noticeDate, String noticeTitel, String noticeContent) {
		super();
		this.noticeNum = noticeNum;
		this.adminId = adminId;
		this.noticeDate = noticeDate;
		this.noticeTitel = noticeTitel;
		this.noticeContent = noticeContent;
	}

	public int getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeTitel() {
		return noticeTitel;
	}

	public void setNoticeTitel(String noticeTitel) {
		this.noticeTitel = noticeTitel;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notice [noticeNum=");
		builder.append(noticeNum);
		builder.append(", adminId=");
		builder.append(adminId);
		builder.append(", noticeDate=");
		builder.append(noticeDate);
		builder.append(", noticeTitel=");
		builder.append(noticeTitel);
		builder.append(", noticeContent=");
		builder.append(noticeContent);
		builder.append("]");
		return builder.toString();
	}


	
	
	
	
}
	
	

