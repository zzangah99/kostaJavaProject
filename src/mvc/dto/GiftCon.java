package mvc.dto;

public class GiftCon {
	private int giftCode;//pk
	private int orderCode;//fk
	private String giftLimitDate;
	private String goodsName;
	
	public GiftCon() {}

	public GiftCon(int giftCode, int orderCode, String giftLimitDate) {
		super();
		this.giftCode = giftCode;
		this.orderCode = orderCode;
		this.giftLimitDate = giftLimitDate;
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
		return giftCode+" 기프티콘 : "+ goodsName + "\n사용기한 : " + giftLimitDate; 
	}
	
}
