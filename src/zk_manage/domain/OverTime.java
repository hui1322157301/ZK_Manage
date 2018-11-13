package zk_manage.domain;

public class OverTime {
	private String sname;
	private String workdate;
	private String message;
	private int worknum;
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getWorknum() {
		return worknum;
	}
	public void setWorknum(int worknum) {
		this.worknum = worknum;
	}
	@Override
	public String toString() {
		return "OverTime [sname=" + sname + ", workdate=" + workdate
				+ ", message=" + message + ", worknum=" + worknum + "]";
	}
	
}
