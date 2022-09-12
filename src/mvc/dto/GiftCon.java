package mvc.dto;

public class GiftCon {
	private int giftCode;//pk
	private int orderCode;//fk
	private String giftLimitDate;
	private String goodsName;
	private String giftFlag;
	
	public GiftCon() {}

	public GiftCon(int giftCode, int orderCode, String giftLimitDate, String giftFlag) {
		super();
		this.giftCode = giftCode;
		this.orderCode = orderCode;
		this.giftLimitDate = giftLimitDate;
		this.giftFlag = giftFlag;
	}

	public String getGiftFlag() {
		return giftFlag;
	}

	public void setGiftFlag(String giftFlag) {
		this.giftFlag = giftFlag;
	}

	public int getGiftCode() {
		return giftCode;
	}

	public void setGiftCode(int giftCode) {
		this.giftCode = giftCode;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public String getGiftLimitDate() {
		return giftLimitDate;
	}

	public void setGiftLimitDate(String giftLimitDate) {
		this.giftLimitDate = giftLimitDate;
	}

	@Override
	public String toString() {
		return giftCode+" 기프티콘 : "+ goodsName + "\n생성날짜 : " + giftLimitDate; 
	}
	
}
