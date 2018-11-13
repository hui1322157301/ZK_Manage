package backstage.service;

import java.util.ArrayList;
import java.util.List;

import zk_manage.dao.ManageDao;
import zk_manage.domain.Ip;
import zk_manage.domain.LateMessage;
import zk_manage.domain.LeaveMessage;
import zk_manage.domain.Other;
import zk_manage.domain.OverTime;
import zk_manage.domain.RelayMessage;
import zk_manage.domain.Student;
import zk_manage.domain.WorkMessage;
import zk_manage.domain.WorkTime;
import backstage.dao.BackstageDao;
import backstage.domain.BackstageCheck;

public class BackstageService {
	BackstageDao dao = new BackstageDao();
	public List<BackstageCheck> check(String begintime,String endtime){
		List<BackstageCheck> list = new ArrayList<BackstageCheck>();
		List<Student> student = dao.getStudent();
		for(Student s :student){
			BackstageCheck bc = check(s.getSname(),begintime,endtime);
			list.add(bc);
		}
		return list;
	}
	/**
	 * 查询某个同学的时间段工时
	 * @param sname
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	public BackstageCheck check(String sname,String begintime,String endtime){
		BackstageCheck bc = new BackstageCheck();
		bc.setWorktime(0);
		bc.setLatetime(0);
		bc.setOvertime(0);
		bc.setRelaytime(0);
		bc.setSumtime(0);
		int wt = bc.getWorktime();
		int lt = bc.getLatetime();
		int ot = bc.getOvertime();
		int rt = bc.getRelaytime();
		int st = bc.getSumtime();
		//查询该同学正常值班信息
		List<WorkMessage> workMessage = dao.getWorkTime(sname,begintime, endtime);
		for(WorkMessage wm : workMessage){
			wt += wm.getWorknum();
		}
		//查询该同学替班信息
		List<RelayMessage> relayTime = dao.getRelayTime(sname, begintime, endtime);
		for(RelayMessage rm : relayTime){
			rt += rm.getWorknum();
		}
		//查询该同学迟到信息
		List<LateMessage> lateTime = dao.getLateTime(sname, begintime, endtime);
		for(LateMessage lm : lateTime){
			lt += lm.getWorknum();
		}
		//查询该同学加班信息
		List<OverTime> overTime = dao.getOverTime(sname, begintime, endtime);
		for(OverTime om : overTime){
			ot += om.getWorknum();
		}
		//查询该同学总工时信息
		int sumTime = getSumTime(sname, begintime, endtime);
		st = sumTime;
		//将所有信息存入对象中
		bc.setWorktime(wt);
		bc.setLatetime(lt);
		bc.setOvertime(ot);
		bc.setRelaytime(rt);
		bc.setSumtime(st);
		bc.setSname(sname);
		return bc;
	}
	/**
	 * 获取总工时
	 * @param sname
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	public int getSumTime(String sname,String beginTime,String endTime){
		int sumTime = 0;
		int workTime = getWorkTime(sname, beginTime, endTime);
		int relayTime = getRelayTime(sname, beginTime, endTime);
		int lateTime = getLateTime(sname, beginTime, endTime);
		int OverTime = getOverTime(sname, beginTime, endTime);
		sumTime = workTime + relayTime + lateTime + OverTime;
		return sumTime;
	}
	/**
	 * 获取正常值班工时
	 * @param sname
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	public int getWorkTime(String sname,String beginTime,String endTime){
		List<WorkMessage> list = dao.getWorkTime(sname, beginTime, endTime);
		int workTime = 0;
		for(WorkMessage wm : list){
			workTime += wm.getWorknum();
		}
		return workTime;
	}
	/**
	 * 获取替班工时
	 * @param sname
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	public int getRelayTime(String sname,String beginTime,String endTime){
		List<RelayMessage> list = dao.getRelayTime(sname, beginTime, endTime);
		int relayTime = 0;
		for(RelayMessage rm : list){
			relayTime += rm.getWorknum();
		}
		return relayTime;
	}
	/**
	 * 获取迟到工时
	 * @param sname
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	public int getLateTime(String sname,String beginTime,String endTime){
		List<LateMessage> list = dao.getLateTime(sname, beginTime, endTime);
		int lateTime = 0;
		for(LateMessage lm : list){
			lateTime += lm.getWorknum();
		}
		return lateTime;
	}
	/**
	 * 获取加班工时
	 * @param sname
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	public int getOverTime(String sname,String beginTime,String endTime){
		List<OverTime> list = dao.getOverTime(sname, beginTime, endTime);
		int overTime = 0;
		for(OverTime ot : list){
			overTime += ot.getWorknum();
		}
		return overTime;
	}
	/**
	 * 设置糖果数
	 * @param candyNum
	 */
	public void setCandy(int candyNum){
		ManageDao d = new ManageDao();
		d.writeCandy(candyNum);
	}
	/**
	 * 获取正常值班信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<WorkMessage> checkWorkMessage(String sname,String beginTime,String endTime){
		List<WorkMessage> list = dao.getWorkMessage(sname, beginTime, endTime);
		return list;
	}
	/**
	 * 获取正常值班信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<WorkMessage> checkWorkMessage(String beginTime,String endTime){
		List<WorkMessage> alist = dao.getWorkMessage(beginTime, endTime);
		return alist;
	}
	/**
	 * 获取替班信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<RelayMessage> checkRelayMessage(String sname,String beginTime,String endTime){
		ManageDao mdao = new ManageDao();
		List<RelayMessage> list = mdao.getRelayTime(sname, beginTime, endTime);
		return list;
	}
	/**
	 * 获取替班信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<RelayMessage> checkRelayMessage(String beginTime,String endTime){
		List<RelayMessage> list = dao.getRelayTime(beginTime, endTime);
		return list;
	}
	/**
	 * 获取请假信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LeaveMessage> checkLeaveMessage(String sname,String beginTime,String endTime){
		List<LeaveMessage> list = dao.getLeaveMessage(sname, beginTime, endTime);
		return list;
	}
	/**
	 * 获取请假信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LeaveMessage> checkLeaveMessage(String beginTime,String endTime){
		List<LeaveMessage> list = dao.getLeaveMessage(beginTime, endTime);
		return list;
	}
	/**
	 * 获取迟到信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LateMessage> checkLateMessage(String sname,String beginTime,String endTime){
		ManageDao mdao = new ManageDao();
		List<LateMessage> list = mdao.getLateTime(sname, beginTime, endTime);
		return list;
	}
	/**
	 * 获取迟到信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LateMessage> checkLateMessage(String beginTime,String endTime){
		List<LateMessage> list = dao.getLateMessage(beginTime, endTime);
		return list;
	}
	/**
	 * 获加班到信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<OverTime> checkOverTime(String sname,String beginTime,String endTime){
		List<OverTime> list = dao.getOverTime(sname, beginTime, endTime);
		return list;
	}
	/**
	 * 获加班到信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<OverTime> checkOverTime(String beginTime,String endTime){
		List<OverTime> list = dao.getOverTime(beginTime, endTime);
		return list;
	}
	/**
	 * 设置加班
	 * @param sname
	 * @param workdate
	 * @param message
	 * @param worknum
	 */
	public void setOverTime(String sname,String workdate,String message,int worknum){
		dao.setOverTime(sname,workdate,message,worknum);
	}
	/**
	 * 删除学生
	 * @param sname
	 */
	public void deleteStudent(String sname){
		dao.deleteStudent(sname);
	}
	/**
	 * 根据姓名获取学生对象
	 * @param sname
	 * @return
	 */
	public Student getStudent(String sname){
		return dao.getStudent(sname);
	}
	/**
	 * 删除学生正常值班信息
	 * @param sname
	 */
	public void deleteWorkMessage(String sname){
		dao.deleteWorkMessage(sname);
	}
	/**
	 * 删除学生迟到信息
	 * @param sname
	 */
	public void deleteLateMessage(String sname){
		dao.deleteLateMessage(sname);
	}
	/**
	 * 删除学生替班信息
	 * @param sname
	 */
	public void deleteRelayMessage(String sname){
		dao.deleteRelayMessage(sname);
	}
	/**
	 * 删除学生请假信息
	 * @param sname
	 */
	public void deleteLeaveMessage(String sname){
		dao.deleteLeaveMessage(sname);
	}
	/**
	 * 删除学生加班信息
	 * @param sname
	 */
	public void deleteOverTime(String sname){
		dao.deleteOverTime(sname);
	}
	/**
	 * 设置注册码
	 * @param registecode
	 */
	public void setRegisteCode(String registecode) {
		dao.setRegisteCode(registecode);
		
	}
	public List<Ip> getIp() {
		return dao.getIp();
	}
	/**
	 * 删除IP
	 * @param set_ip
	 */
	public void deleteIp(String ip) {
		dao.deleteIp(ip);
	}
	/**
	 * 获取值班时间工时表
	 * @return
	 */
	public List<WorkTime> getWorkTime() {
		return dao.getWorkTime();
	}
	/**
	 * 设置值班时间及工时表
	 */
	public void setWorkDateAndWorkTime(String num,String begintime,String endtime,String worknum) {
		dao.setWorkDateAndWorkTime(num,begintime,endtime,worknum);
		
	}
	/**
	 * 获取全部学生对象
	 * @return
	 */
	public List<Student> showStudent() {
		return dao.showStudent();
	}
	/**
	 * 根据姓名获取学生对象
	 * @param student_sname
	 * @return
	 */
	public List<Student> showStudent(String sname) {
		return dao.showStudent(sname);
	}
	/**
	 * 添加IP
	 * @param set_ip
	 */
	public void addIp(String ip) {
		dao.addIp(ip);
	}
	public String getRegisteCode() {
		Other other = dao.getRegisteCode();
		return other.getRegistecode();
	}
}
