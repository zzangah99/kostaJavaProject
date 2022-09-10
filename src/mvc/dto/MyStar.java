package mvc.dto;

public class MyStar {
	private int reNum;//리뷰번호 
	private int reviewStar;
	private String reviewDate;//리뷰작성날짜 
	
	public MyStar() {
	}

	public MyStar(int reNum, int reviewStar, String reviewDate) {
		super();
		this.reNum = reNum;
		this.reviewStar = reviewStar;
		this.reviewDate = reviewDate;
	}

	/**
	 * 마이페이지->내가쓴 별점 보기(not null) 
	 */
	//order_code, goods_code, REVIEW_STAR , REVIEW_DATE from REVIEW where user_id = ?;
	public MyStar(int int1, int int2, int int3, String string) {
		super();
		this.reNum = reNum;
		this.reviewStar = reviewStar;
		this.reviewDate = reviewDate;
	}

	public int getReNum() {
		return reNum;
	}

	public void setReNum(int reNum) {
		this.reNum = reNum;
	}

	public int getReviewStar() {
		return reviewStar;
	}

	public void setReviewStar(int reviewStar) {
		this.reviewStar = reviewStar;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	

	
	
	
	
	
}


