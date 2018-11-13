package zk_manage.domain;

public class AWorkSign {
	private String worktime;
	private String sname;
	private String workdate;
	private String workstate;
	
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
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
	public String getWorkstate() {
		return workstate;
	}
	public void setWorkstate(String workstate) {
		this.workstate = workstate;
	}
	@Override
	public String toString() {
		return "WorkSign [worktime=" + worktime + ", sname=" + sname
				+ ", workdate=" + workdate + ", workstate=" + workstate + "]";
	}
	

}
