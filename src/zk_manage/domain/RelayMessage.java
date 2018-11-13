package zk_manage.domain;

public class RelayMessage {
	private String sname;
	private String prename;
	private String workdate;
	private String worktime;
	private String location;
	private int worknum;
	/**
	 * @return the sname
	 */
	public String getSname() {
		return sname;
	}
	/**
	 * @param sname the sname to set
	 */
	public void setSname(String sname) {
		this.sname = sname;
	}
	/**
	 * @return the prename
	 */
	public String getPrename() {
		return prename;
	}
	/**
	 * @param prename the prename to set
	 */
	public void setPrename(String prename) {
		this.prename = prename;
	}
	/**
	 * @return the workdate
	 */
	public String getWorkdate() {
		return workdate;
	}
	/**
	 * @param workdate the workdate to set
	 */
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	/**
	 * @return the worktime
	 */
	public String getWorktime() {
		return worktime;
	}
	/**
	 * @param worktime the worktime to set
	 */
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the worknum
	 */
	public int getWorknum() {
		return worknum;
	}
	/**
	 * @param worknum the worknum to set
	 */
	public void setWorknum(int worknum) {
		this.worknum = worknum;
	}
	@Override
	public String toString() {
		return "RelayMessage [sname=" + sname + ", prename=" + prename
				+ ", workdate=" + workdate + ", worktime=" + worktime
				+ ", location=" + location + ", worknum=" + worknum + "]";
	}
	
	
}
