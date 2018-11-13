package zk_manage.domain;

public class WorkTime {
	private String num;
	private String begintime;
	private String endtime;
	private int worknum;
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public int getWorknum() {
		return worknum;
	}
	public void setWorknum(int worknum) {
		this.worknum = worknum;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	@Override
	public String toString() {
		return "WorkTime [num=" + num + ", begintime=" + begintime
				+ ", endtime=" + endtime + ", worknum=" + worknum + "]";
	}
	
	
}
