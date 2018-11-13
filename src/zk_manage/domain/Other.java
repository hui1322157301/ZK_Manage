package zk_manage.domain;

public class Other {
	private int num;
	private int candy;
	private String registecode;
	
	public int getNum() {
		return num;
	}
	public int getCandy() {
		return candy;
	}
	public void setCandy(int candy) {
		this.candy = candy;
	}
	public String getRegistecode() {
		return registecode;
	}
	public void setRegistecode(String registecode) {
		this.registecode = registecode;
	}
	@Override
	public String toString() {
		return "Other [num=" + num + ", candy=" + candy + ", registecode="
				+ registecode + "]";
	}
	
}
