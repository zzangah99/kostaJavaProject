package mvc.dto;

public class Coupon {
	private String cpCode;
	private String cpName;
	private int salePrice;
	private int goodsCode;
	private String cpLitDate;  //날짜제한 사용기한  
	private String cpCountDate;//카운트 사용기한 
	
	public Coupon() {
		
	}
	
	public Coupon(String cpCode, String cpName, int salePrice, int goodsCode) {
		super();
		this.cpCode = cpCode;
		this.cpName = cpName;
		this.salePrice = salePrice;
		this.goodsCode = goodsCode;
    }

	public String getCpCode() {
		return cpCode;
	}

	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}

	
}

