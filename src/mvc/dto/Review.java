package mvc.dto;

class ReviewService {
	private int reNum;
	private int orderCode;
	private String userId;
	private int goodsCode;
	private String reviewDate;
	private String reviewStar;

	public ReviewService() {
	}

	public ReviewService(int reNum, int orderCode, String userId, int goodsCode, String reviewDate, String reviewStar) {
		super();
		this.reNum = reNum;
		this.orderCode = orderCode;
		this.userId = userId;
		this.goodsCode = goodsCode;
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

