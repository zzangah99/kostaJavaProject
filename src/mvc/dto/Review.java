package mvc.dto;

public class Review {
	private int reNum;
	private int orderCode;
	private String userId;
	private int goodsCode;
	private String reviewContent;
	private String reviewDate;
	private String reviewStar;

	public Review() {
	}
	
	public Review(int orderCode, String userId, int goodsCode, String reviewContent) {
		this.orderCode = orderCode;
		this.userId = userId;
		this.goodsCode = goodsCode;
		this.reviewContent = reviewContent;
	}

	public Review(int reNum, int orderCode, String userId, int goodsCode, String reviewContent, String reviewDate,
			String reviewStar) {
		//super();
		this(orderCode, userId, goodsCode, reviewContent);
		this.reNum = reNum;
		this.reviewDate = reviewDate;
		this.reviewStar = reviewStar;
	}

	public int getReNum() {
		return reNum;
	}

	public void setReNum(int reNum) {
		this.reNum = reNum;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public int getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewStar() {
		return reviewStar;
	}

	public void setReviewStar(String reviewStar) {
		this.reviewStar = reviewStar;
	}

}
