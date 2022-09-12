package mvc.dto;

public class Nutrition {
	
	private int goodsCode;
	private String calorie;
	private String sugar;
	private String natrium;
	private String caffeine;
	private String warning;
	
	
	public Nutrition(){}


	public Nutrition(int goodsCode, String calorie, String sugar, String natrium, String caffeine, String warning) {
		super();
		this.goodsCode = goodsCode;
		this.calorie = calorie;
		this.sugar = sugar;
		this.natrium = natrium;
		this.caffeine = caffeine;
		this.warning = warning;
	}


	public int getGoodsCode() {
		return goodsCode;
	}


	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}


	public String getCalorie() {
		return calorie;
	}


	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}


	public String getSugar() {
		return sugar;
	}


	public void setSugar(String sugar) {
		this.sugar = sugar;
	}


	public String getNatrium() {
		return natrium;
	}


	public void setNatrium(String natrium) {
		this.natrium = natrium;
	}


	public String getCaffeine() {
		return caffeine;
	}


	public void setCaffeine(String caffeine) {
		this.caffeine = caffeine;
	}


	public String getWarning() {
		return warning;
	}


	public void setWarning(String warning) {
		this.warning = warning;
	}
	
	
	
}
