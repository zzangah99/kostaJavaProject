package mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderLine {
	private int detailCode;// pk
	private int orderCode;// fk
	private int goodsCode;// fk
	private String goodsName;//
	private int detailPrice;
	private int detailQuan;
	private int optionCode;
	
	private List<Option> optionList = new ArrayList<Option>();	//옵션
	
	public OrderLine() {}
	
	public OrderLine(int detailCode, int orderCode, int goodsCode, int detailPrice, int detailQuan, String goodsName) {
		super();
		this.detailCode = detailCode;
		this.orderCode = orderCode;
		this.goodsCode = goodsCode;
		this.detailPrice = detailPrice;
		this.detailQuan = detailQuan;
		this.goodsName = goodsName;
	}

	public int getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(int detailCode) {
		this.detailCode = detailCode;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public int getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}

	public int getDetailPrice() {
		return detailPrice;
	}

	public void setDetailPrice(int detailPrice) {
		this.detailPrice = detailPrice;
	}

	public int getDetailQuan() {
		return detailQuan;
	}

	public void setDetailQuan(int detailQuan) {
		this.detailQuan = detailQuan;
	}
	
	public List<Option> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}
	

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Override
	public String toString() {
		return "상세내역 [상품 이름 : " + goodsName + " | 상품 수량 : " + detailQuan + " | 금액 : " + detailPrice + " ]";
	}
}
