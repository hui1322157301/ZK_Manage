package backstage.domain;

public class BackstageCheck {
	private String sname;
	private int worktime;
	private int relaytime;
	private int latetime;
	private int overtime;
	private int sumtime;
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getWorktime() {
		return worktime;
	}
	public void setWorktime(int worktime) {
		this.worktime = worktime;
	}
	public int getRelaytime() {
		return relaytime;
	}
	public void setRelaytime(int relaytime) {
		this.relaytime = relaytime;
	}
	public int getLatetime() {
		return latetime;
	}
	public void setLatetime(int latetime) {
		this.latetime = latetime;
	}
	public int getOvertime() {
		return overtime;
	}
	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}
	public int getSumtime() {
		return sumtime;
	}
	public void setSumtime(int sumtime) {
		this.sumtime = sumtime;
	}
	@Override
	public String toString() {
		return "BackstageCheck [sname=" + sname + ", worktime=" + worktime
				+ ", relaytime=" + relaytime + ", latetime=" + latetime
				+ ", overtime=" + overtime + ", sumtime=" + sumtime + "]";
	}
}
