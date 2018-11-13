package zk_manage.domain;

public class WorkMessage {
	private String sname;
	private String workdate;
	private String worktime;
	private String location;
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
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getWorknum() {
		return worknum;
	}
	public void setWorknum(int worknum) {
		this.worknum = worknum;
	}
	@Override
	public String toString() {
		return "WorkMessage [sname=" + sname + ", workdate=" + workdate
				+ ", worktime=" + worktime + ", location=" + location
				+ ", worknum=" + worknum + "]";
	}
	
}
