package mvc.dto;

public class MyMenu {
	private String userId;
	private int goodsCode;
	private String mmName;   //나만의메뉴 이름 
	private String tem;      //온도  
	private String syrup;
	private String def;      //디카페인 
	private String whip;     //휘핑 
	private String sizeSize; //사이즈  
	
	public MyMenu() {
		
	}
	
	public MyMenu(String userId, int goodsCode, String mmName, String tem, String syrup, String def, String whip,
			String sizeSize) {
		super();
		this.userId = userId;
		this.goodsCode = goodsCode;
		this.mmName = mmName;
		this.tem = tem;
		this.syrup = syrup;
		this.def = def;
		this.whip = whip;
		this.sizeSize = sizeSize;
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

	public String getMmName() {
		return mmName;
	}

	public void setMmName(String mmName) {
		this.mmName = mmName;
	}

	public String getTem() {
		return tem;
	}

	public void setTem(String tem) {
		this.tem = tem;
	}

	public String getSyrup() {
		return syrup;
	}

	public void setSyrup(String syrup) {
		this.syrup = syrup;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public String getWhip() {
		return whip;
	}

	public void setWhip(String whip) {
		this.whip = whip;
	}

	public String getSizeSize() {
		return sizeSize;
	}

	public void setSizeSize(String sizeSize) {
		this.sizeSize = sizeSize;
	}
	
	

	
	
}	
