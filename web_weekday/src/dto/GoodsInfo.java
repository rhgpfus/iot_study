package dto;

public class GoodsInfo {

	private int giNum;
	private String giName;
	private String giDesc;
	private int viNum;
	private String giCredat;
	private String giMofdat;
	private int giCreusr;
	private int giMofusr;
	
	public int getGiNum() {
		return giNum;
	}
	public void setGiNum(int giNum) {
		this.giNum = giNum;
	}
	public String getGiName() {
		return giName;
	}
	public void setGiName(String giName) {
		this.giName = giName;
	}
	public String getGiDesc() {
		return giDesc;
	}
	public void setGiDesc(String giDesc) {
		this.giDesc = giDesc;
	}
	public int getViNum() {
		return viNum;
	}
	public void setViNum(int viNum) {
		this.viNum = viNum;
	}
	public String getGiCredat() {
		return giCredat;
	}
	public void setGiCredat(String giCredat) {
		this.giCredat = giCredat;
	}
	public String getGiMofdat() {
		return giMofdat;
	}
	public void setGiMofdat(String giMofdat) {
		this.giMofdat = giMofdat;
	}
	public int getGiCreusr() {
		return giCreusr;
	}
	public void setGiCreusr(int giCreusr) {
		this.giCreusr = giCreusr;
	}
	public int getGiMofusr() {
		return giMofusr;
	}
	public void setGiMofusr(int giMofusr) {
		this.giMofusr = giMofusr;
	}
	@Override
	public String toString() {
		return "GoodsInfo [giNum=" + giNum + ", giName=" + giName + ", giDesc=" + giDesc + ", viNum=" + viNum
				+ ", giCredat=" + giCredat + ", giMofdat=" + giMofdat + ", giCreusr=" + giCreusr + ", giMofusr="
				+ giMofusr + "]";
	}
	
}
