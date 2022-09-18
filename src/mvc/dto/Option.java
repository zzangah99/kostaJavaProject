package mvc.dto;

public class Option {
	private int detailCode;//pk,fk
	private int sizeCode;//fk
	private String sizeName;
	private String tem;
	private String syrup;
	private String def;
	private String whip;
	
	
	public Option() {}
	public Option(int detailCode, int sizeCode, String sizeName, String tem, String syrup, String def, String whip) {
		super();
		this.detailCode = detailCode;
		this.sizeCode = sizeCode;
		this.sizeName = sizeName;
		this.tem = tem;
		this.syrup = syrup;
		this.def = def;
		this.whip = whip;
	}
	
	public int getDetailCode() {
		return detailCode;
	}
	public void setDetailCode(int detailCode) {
		this.detailCode = detailCode;
	}
	public int getSizeCode() {
		return sizeCode;
	}
	public void setSizeCode(int sizeCode) {
		this.sizeCode = sizeCode;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
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
	@Override
	public String toString() {
		return "사이즈: " + sizeCode + " | 온도: " + tem + " | 시럽: " + syrup + " | 디카페인: " + def + " | 휘핑크림: " + whip;
	}
	
	
	
}
