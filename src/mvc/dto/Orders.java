package mvc.dto;

import java.util.ArrayList;
import java.util.List;


public class Orders {
	private int orderCode; // pk
	private String orderTime;
	private String userId;// fk
	private String userCpCode;
	private int orderPrice;
	private int orderQuan;
	private String orderPayment;
	private String giftCode;
	private String takeOut;
	private int checkGiftCon;
	
	private List<OrderLine> orderLineList = new ArrayList<OrderLine>();	//상세주문
	
	public Orders() {}

	public Orders(int orderCode, String userId, String orderTime,int orderQuan, int orderPrice, String userCpCode, 
			String orderPayment, String giftCode, String takeOut) {
		super();
		this.orderCode = orderCode;
		this.orderTime = orderTime;
		this.userId = userId;
		this.userCpCode = userCpCode;
		this.orderPrice = orderPrice;
		this.orderQuan = orderQuan;
		this.orderPayment = orderPayment;
		this.giftCode = giftCode;
		this.takeOut = takeOut;
	}

	public int getCheckGiftCon() {
		return checkGiftCon;
	}

	public void setCheckGiftCon(int checkGiftCon) {
		this.checkGiftCon = checkGiftCon;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserCpCode() {
		return userCpCode;
	}

	public void setUserCpCode(String userCpCode) {
		this.userCpCode = userCpCode;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getOrderQuan() {
		return orderQuan;
	}

	public void setOrderQuan(int orderQuan) {
		this.orderQuan = orderQuan;
	}

	public String getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(String orderPayment) {
		this.orderPayment = orderPayment;
	}

	public String getGiftCode() {
		return giftCode;
	}

	public void setGiftCode(String giftCode) {
		this.giftCode = giftCode;
	}

	public String getTakeOut() {
		return takeOut;
	}

	public void setTakeOut(String takeOut) {
		this.takeOut = takeOut;
	}

	public List<OrderLine> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(List<OrderLine> orderLineList) {
		this.orderLineList = orderLineList;
	}
	
}
