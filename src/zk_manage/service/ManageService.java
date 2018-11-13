package zk_manage.service;

import java.util.ArrayList;
import java.util.List;

import zk_manage.dao.ManageDao;
import zk_manage.domain.AWorkSign;
import zk_manage.domain.CWorkSign;
import zk_manage.domain.Ip;
import zk_manage.domain.LateMessage;
import zk_manage.domain.LeaveMessage;
import zk_manage.domain.Other;
import zk_manage.domain.OverTime;
import zk_manage.domain.RelayMessage;
import zk_manage.domain.Student;
import zk_manage.domain.WorkMessage;
import zk_manage.domain.WorkTime;

public class ManageService {
	ManageDao dao = new ManageDao();
	/**
	 * 获取注册码
	 * @return
	 */
	public String getRegisteCode(){
		Other other = dao.getOther();
		return other.getRegistecode();
	}
	/**
	 * 登录验证
	 * @param sid
	 * @param pwd
	 * @return
	 */
	public Student loginValidate(String sid,String pwd){
		return dao.loginVaildate(sid, pwd);
	}
	/**
	 * 学号验证
	 * @param sid
	 * @return
	 */
	public boolean SidValidate(String sid){
		return dao.SidValidate(sid);
	}
	
	/**
	 * 注册验证
	 * @param sid
	 * @param pwd
	 * @param sname
	 * @param code
	 * @return
	 */
	public boolean registeValidate(String sid,String pwd,String sname,String code){
		String registeCode = getRegisteCode();
		if(registeCode.equals(code)){
			dao.registeValidate(sid, pwd, sname);
			return true; 
		}else{
			return false;
		}
	}
	/**
	 * 修改密码
	 * @param sid
	 * @param prepwd
	 * @param newpwd
	 * @return
	 */
	public boolean revisePwd(String sid,String prepwd,String newpwd){
		return dao.revisePwd(sid,prepwd,newpwd);
	}
	/**
	 * 获取worktime对象
	 * @param num
	 * @return
	 */
	public WorkTime getWorkTime(String num){
		return dao.getWorkTime(num);
	}
	/**
	 * 签到次数验证
	 * @param sid
	 * @param signDate
	 * @param signTime
	 * @return
	 */
	public boolean signValidate(String sid,String signDate,String signTime){
		return dao.signValidate(sid, signDate, signTime);
	}
	/**
	 * 签到功能
	 * @param sid
	 * @param workdate
	 * @param begintime
	 * @param signtime
	 * @param worktime
	 * @param location
	 * @param num
	 */
	public int sign(String ip,String sid,String workdate,String begintime,String signtime,String worktime,String location,int lateNum){
		/*
		 * 思路：
		 * 1.对签到时间进行判断，
		 * 	正常：加2个工时
		 * 	迟到：
		 * 		30分钟以内，扣一个工时
		 * 		30分钟意外，扣两个工时
		 * 2.从签到的时间对工时进行判断
		 * 3.将参数写入数据库
		 * 4.将结果返回
		 */
		//验证ip
		Ip ipObject = dao.ip(ip);
		if(ipObject == null){
			return 10;
		}
		//得到糖果数
		Other other = dao.getOther();
		int candy = other.getCandy();
		//得到学生姓名
		String sname = dao.getStuName(sid);
		//得到时间差
		int resultTime = dao.isTime(signtime, begintime);
		//得到工时
		WorkTime wt = dao.getWorkTime(worktime);
		int worknum = wt.getWorknum();
		if(resultTime > 30){
			//太早
			return 2;
		}else if(resultTime <= 30 && resultTime >= 0){
			//正常签到
			dao.writeWorkMessage(sname,workdate,worktime,location,worknum);
			return 1;
		}else{
			//迟到,判断迟到次数
			if(lateNum < 2){
				//写入迟到信息表，不扣工时
				if(-resultTime <= 130){
					dao.writeLateMessage(sname, workdate, worktime, -resultTime, location, worknum);
					return -5;
				}else{
					return -4;
				}
			}else{
				//超过2次，写入迟到信息表，扣除工时
				if(-resultTime <= 30){
					dao.writeLateMessage(sname, workdate, worktime, -resultTime, location, worknum-1);
					candy += 1;
					dao.writeCandy(candy);
					return -3;
				}else if(-resultTime <= 130 && -resultTime > 30){
					dao.writeLateMessage(sname, workdate, worktime, -resultTime, location, 0);
					candy += 2;
					dao.writeCandy(candy);
					return -3;
				}else{
					return -4;
				}
			}
		}
	}
	/**
	 * 本月迟到次数
	 * @param sid
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public int lateNum(String sid,String beginTime,String endTime){
		return dao.lateNum(sid,beginTime,endTime);
	}
	
	public int relay(String sname,String prename,String workdate,String worktime,String location){
		WorkTime wt = dao.getWorkTime(worktime);
		String begintime = wt.getBegintime();
		String signtime = workdate.substring(11);
		//得到时间差
		int resultTime = dao.isTime(signtime, begintime);
		//得到工时
		int worknum = wt.getWorknum();
		if(resultTime > 30){
			//太早
			return 2;
		}else if(resultTime <= 30 && resultTime >= 0){
			//正常签到
			dao.relay(sname, prename, workdate, worktime, location, worknum);
			return 1;
		}else{
			//迟到
			if(-resultTime <= 130){
				dao.relay(sname, prename, workdate, worktime, location, worknum);
				return 1;
			}else{
				return -2;
			}
		}
	}
	/**
	 * 请假功能
	 */
	public void leave(String sname,String workdate,String worktime,String location,String message){
		dao.leave(sname,workdate,worktime,location,message);
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
	 * 查询A教值班情况
	 * @param date
	 * @return
	 */
	public List<AWorkSign> getAWorkSign(String date){
		List<AWorkSign> alist = new ArrayList<AWorkSign>();
		//处理时间
		String beginTime = date + " 00:00:00";
		String endTime = date + " 23:59:59";
		//查询正常值班信息
		List<WorkMessage> workMessage = dao.getWorkMessage(beginTime, endTime, "a");
		if(workMessage != null){
			for(WorkMessage wm : workMessage){
				AWorkSign aws = new AWorkSign();
				if(wm.getWorktime().equals("one")){
					aws.setWorktime("上午第一节");
				}else if(wm.getWorktime().equals("two")){
					aws.setWorktime("上午第二节");
				}else if(wm.getWorktime().equals("three")){
					aws.setWorktime("下午第一节");
				}else if(wm.getWorktime().equals("four")){
					aws.setWorktime("下午第二节");
				}else if(wm.getWorktime().equals("five")){
					aws.setWorktime("晚上");
				}
				aws.setSname(wm.getSname());
				aws.setWorkdate(wm.getWorkdate().substring(11,19));
				aws.setWorkstate("正常");
				alist.add(aws);
			}
		}
		//查询替班信息
		List<RelayMessage> relayMessage = dao.getRelayMessage(beginTime, endTime, "a");
		if(relayMessage != null){
			for(RelayMessage rm : relayMessage){
				AWorkSign aws = new AWorkSign();
				if(rm.getWorktime().equals("one")){
					aws.setWorktime("上午第一节");
				}else if(rm.getWorktime().equals("two")){
					aws.setWorktime("上午第二节");
				}else if(rm.getWorktime().equals("three")){
					aws.setWorktime("下午第一节");
				}else if(rm.getWorktime().equals("four")){
					aws.setWorktime("下午第二节");
				}else if(rm.getWorktime().equals("five")){
					aws.setWorktime("晚上");
				}
				aws.setSname(rm.getSname());
				aws.setWorkdate(rm.getWorkdate().substring(11,19));
				aws.setWorkstate("替班");
				alist.add(aws);
			}
		}
		//查询迟到信息
		List<LateMessage> lateMessage = dao.getLateMessage(beginTime, endTime, "a");
		if(lateMessage != null){
			for(LateMessage lm : lateMessage){
				AWorkSign aws = new AWorkSign();
				if(lm.getWorktime().equals("one")){
					aws.setWorktime("上午第一节");
				}else if(lm.getWorktime().equals("two")){
					aws.setWorktime("上午第二节");
				}else if(lm.getWorktime().equals("three")){
					aws.setWorktime("下午第一节");
				}else if(lm.getWorktime().equals("four")){
					aws.setWorktime("下午第二节");
				}else if(lm.getWorktime().equals("five")){
					aws.setWorktime("晚上");
				}
				aws.setSname(lm.getSname());
				aws.setWorkdate(lm.getWorkdate().substring(11,19));
				aws.setWorkstate("迟到");
				alist.add(aws);
			}
		}
		//查询请假信息
		List<LeaveMessage> leaveMessage = dao.getLeaveMessage(beginTime, endTime, "a");
		if(leaveMessage != null){
			for(LeaveMessage lms : leaveMessage){
				AWorkSign aws = new AWorkSign();
				if(lms.getWorktime().equals("one")){
					aws.setWorktime("上午第一节");
				}else if(lms.getWorktime().equals("two")){
					aws.setWorktime("上午第二节");
				}else if(lms.getWorktime().equals("three")){
					aws.setWorktime("下午第一节");
				}else if(lms.getWorktime().equals("four")){
					aws.setWorktime("下午第二节");
				}else if(lms.getWorktime().equals("five")){
					aws.setWorktime("晚上");
				}
				aws.setSname(lms.getSname());
				aws.setWorkdate("00:00:00");
				aws.setWorkstate("请假");
				alist.add(aws);
			}
		}
		return alist;
	}
	/**
	 * 查询C教值班情况
	 * @param date
	 * @return
	 */
	public List<CWorkSign> getCWorkSign(String date){
		List<CWorkSign> clist = new ArrayList<CWorkSign>();
		//处理时间
		String beginTime = date + " 00:00:00";
		String endTime = date + " 23:59:59";
		//查询正常值班信息
		List<WorkMessage> workMessage = dao.getWorkMessage(beginTime, endTime, "c");
		if(workMessage != null){
			for(WorkMessage wm : workMessage){
				CWorkSign cws = new CWorkSign();
				if(wm.getWorktime().equals("one")){
					cws.setWorktime("上午第一节");
				}else if(wm.getWorktime().equals("two")){
					cws.setWorktime("上午第二节");
				}else if(wm.getWorktime().equals("three")){
					cws.setWorktime("下午第一节");
				}else if(wm.getWorktime().equals("four")){
					cws.setWorktime("下午第二节");
				}else if(wm.getWorktime().equals("five")){
					cws.setWorktime("晚上");
				}
				cws.setSname(wm.getSname());
				cws.setWorkdate(wm.getWorkdate().substring(11,19));
				cws.setWorkstate("正常");
				clist.add(cws);
			}
		}
		//查询替班信息
		List<RelayMessage> relayMessage = dao.getRelayMessage(beginTime, endTime, "c");
		if(relayMessage != null){
			for(RelayMessage rm : relayMessage){
				CWorkSign cws = new CWorkSign();
				if(rm.getWorktime().equals("one")){
					cws.setWorktime("上午第一节");
				}else if(rm.getWorktime().equals("two")){
					cws.setWorktime("上午第二节");
				}else if(rm.getWorktime().equals("three")){
					cws.setWorktime("下午第一节");
				}else if(rm.getWorktime().equals("four")){
					cws.setWorktime("下午第二节");
				}else if(rm.getWorktime().equals("five")){
					cws.setWorktime("晚上");
				}
				cws.setSname(rm.getSname());
				cws.setWorkdate(rm.getWorkdate().substring(11,19));
				cws.setWorkstate("替班");
				clist.add(cws);
			}
		}
		//查询迟到信息
		List<LateMessage> lateMessage = dao.getLateMessage(beginTime, endTime, "c");
		if(lateMessage != null){
			for(LateMessage lm : lateMessage){
				CWorkSign cws = new CWorkSign();
				if(lm.getWorktime().equals("one")){
					cws.setWorktime("上午第一节");
				}else if(lm.getWorktime().equals("two")){
					cws.setWorktime("上午第二节");
				}else if(lm.getWorktime().equals("three")){
					cws.setWorktime("下午第一节");
				}else if(lm.getWorktime().equals("four")){
					cws.setWorktime("下午第二节");
				}else if(lm.getWorktime().equals("five")){
					cws.setWorktime("晚上");
				}
				cws.setSname(lm.getSname());
				cws.setWorkdate(lm.getWorkdate().substring(11,19));
				cws.setWorkstate("迟到");
				clist.add(cws);
			}
		}
		//查询请假信息
		List<LeaveMessage> leaveMessage = dao.getLeaveMessage(beginTime, endTime, "c");
		if(leaveMessage != null){
			for(LeaveMessage lms : leaveMessage){
				CWorkSign cws = new CWorkSign();
				if(lms.getWorktime().equals("one")){
					cws.setWorktime("上午第一节");
				}else if(lms.getWorktime().equals("two")){
					cws.setWorktime("上午第二节");
				}else if(lms.getWorktime().equals("three")){
					cws.setWorktime("下午第一节");
				}else if(lms.getWorktime().equals("four")){
					cws.setWorktime("下午第二节");
				}else if(lms.getWorktime().equals("five")){
					cws.setWorktime("晚上");
				}
				cws.setSname(lms.getSname());
				cws.setWorkdate("00:00:00");
				cws.setWorkstate("请假");
				clist.add(cws);
			}
		}
		return clist;
	}
	/**
	 * 查询糖果池数
	 * @return
	 */
	public int getCandy(){
		Other other = dao.getOther();
		int candy = other.getCandy();
		return candy;
	}

}
