package zk_manage.domain;

public class LateMessage {
	private String sname;
	private String workdate;
	private String worktime;
	private int latetime;
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
	 * @return the latetime
	 */
	public int getLatetime() {
		return latetime;
	}
	/**
	 * @param latetime the latetime to set
	 */
	public void setLatetime(int latetime) {
		this.latetime = latetime;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LateMessage [sname=" + sname + ", workdate=" + workdate
				+ ", worktime=" + worktime + ", latetime=" + latetime
				+ ", location=" + location + ", worknum=" + worknum + "]";
	}
	
}
