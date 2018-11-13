package zk_manage.domain;

public class LeaveMessage {
	private String sname;
	private String workdate;
	private String worktime;
	private String location;
	private String message;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "LeaveMessage [sname=" + sname + ", workdate=" + workdate
				+ ", worktime=" + worktime + ", location=" + location
				+ ", message=" + message + "]";
	}
	
}
