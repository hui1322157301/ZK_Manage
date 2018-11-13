package zk_manage.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import zk_manage.domain.Ip;
import zk_manage.domain.LateMessage;
import zk_manage.domain.LeaveMessage;
import zk_manage.domain.Other;
import zk_manage.domain.OverTime;
import zk_manage.domain.RelayMessage;
import zk_manage.domain.Student;
import zk_manage.domain.WorkMessage;
import zk_manage.domain.WorkTime;
import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class ManageDao {
	/**
	 * 登录验证
	 * @param sid
	 * @param pwd
	 * @return
	 */
	public Student loginVaildate(String sid,String pwd){
		//sql语句
		String sql = "SELECT * FROM studentlist WHERE sid=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			Student student = tqr.query(sql, new BeanHandler<Student>(Student.class),sid);
			if(student != null){
				//验证密码
				if(student.getPwd().equals(pwd)){
					//密码正确
					return student;
				}else{
					//密码错误
					return null;
				}
			}else{
				//从数据库中为查询到该学号
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 验证学号
	 * @return
	 */
	public boolean SidValidate(String sid){
		//sql语句
		String sql = "SELECT * FROM studentlist WHERE sid=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			Student student = tqr.query(sql, new BeanHandler<Student>(Student.class),sid);
			if(student != null){
				//验证密码
				return true;
			}else{
				//从数据库中未查询到该学号
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 用户注册
	 * @param sid
	 * @param pwd
	 * @param sname
	 */
	public void registeValidate(String sid,String pwd,String sname){
		//sql语句
		String sql = "INSERT INTO studentlist VALUES(?,?,?)";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sid,pwd,sname};
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		//sql语句
		String sql = "UPDATE studentlist SET pwd=? WHERE sid=? AND pwd=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {newpwd,sid,prepwd};
			TxQueryRunner tqr = new TxQueryRunner();
			int i = tqr.update(sql,params);
			if(0 == i){
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 得到worktime对象
	 * @param num
	 * @return
	 */
	public WorkTime getWorkTime(String num){
		String sql = "SELECT * FROM worktime WHERE num=?";
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			return tqr.query(sql, new BeanHandler<WorkTime>(WorkTime.class),num);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 判断签到时间的状态
	 * @param signTime
	 * @param beginTime
	 * @return
	 */
	public int isTime(String signTime,String beginTime){
		int signHour = Integer.parseInt(signTime.substring(0, 2));
		int signMin = Integer.parseInt(signTime.substring(3, 5));
		int beginHour = Integer.parseInt(beginTime.substring(0, 2));
		int beginMin = Integer.parseInt(beginTime.substring(3, 5));
		int hour = beginHour - signHour;
		int min = beginMin - signMin;
		int result = hour * 60 + min;
		return result;
	}
	/**
	 * 签到次数验证
	 * 	判断是否为第一次签到
	 * @return
	 */
	public boolean signValidate(String sid,String signDate,String signTime){
		boolean key1 = true;
		boolean key2 = true;
		boolean key3 = true;
		//根据学号获取姓名
		String sname = getStuName(sid);
		//根据姓名查找正常，迟到，替班信息表中有无记录
		List<WorkMessage> wmList = selectWorkMessage(sname,signTime);
		List<LateMessage> lmList = selectLateMessage(sname,signTime);
		List<RelayMessage> rmList = selectRelayMessage(sname,signTime);
		if(wmList != null){
			for(WorkMessage wm : wmList){
				if(wm.getWorkdate().contains(signDate)){
					//有记录，签到失败
					key1 = false;
					break;
				}
			}
		}
		if(lmList != null){
			for(LateMessage lm : lmList){
				if(lm.getWorkdate().contains(signDate)){
					//有记录，签到失败
					key2 = false;
					break;
				}
			}
		}
		if(rmList != null){
			for(RelayMessage rm : rmList){
				if(rm.getWorkdate().contains(signDate)){
					//有记录，签到失败
					key3 = false;
					break;
				}
			}
		}
		return key1&&key2&&key3;
	}
	/**
	 * 根据学号获取学生姓名
	 * @param sid
	 * @return
	 */
	public String getStuName(String sid){
		String sql = "SELECT sname FROM studentlist WHERE sid=?";
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			return (String) tqr.query(sql, new ScalarHandler("sname"),sid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 在正常值班信息中查找有无记录
	 * @param sname
	 * @return
	 */
	public List<WorkMessage> selectWorkMessage(String sname,String worktime){
		//sql语句
		String sql = "SELECT * FROM workmessage WHERE sname=? AND worktime=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,worktime};
			TxQueryRunner tqr = new TxQueryRunner();
			return tqr.query(sql, new BeanListHandler<WorkMessage>(WorkMessage.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 在迟到值班信息中查找有无记录
	 * @param sname
	 * @return
	 */
	public List<LateMessage> selectLateMessage(String sname,String worktime){
		//sql语句
		String sql = "SELECT * FROM latemessage WHERE sname=? AND worktime=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,worktime};
			TxQueryRunner tqr = new TxQueryRunner();
			return tqr.query(sql, new BeanListHandler<LateMessage>(LateMessage.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 在替班信息中查找有无记录
	 * @param sname
	 * @return
	 */
	public List<RelayMessage> selectRelayMessage(String sname,String worktime){
		//sql语句
		String sql = "SELECT * FROM relaymessage WHERE sname=? AND worktime=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,worktime,};
			TxQueryRunner tqr = new TxQueryRunner();
			return tqr.query(sql, new BeanListHandler<RelayMessage>(RelayMessage.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 写入正常值班记录
	 * @param snaem
	 * @param workdate
	 * @param worktime
	 * @param location
	 * @param worknum
	 */
	public void writeWorkMessage(String sname,String workdate,String worktime,String location,int worknum){
		//sql语句
		String sql = "INSERT INTO workmessage VALUES(?,?,?,?,?)";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,workdate,worktime,location,worknum};
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 写入迟到值班记录
	 * @param snaem
	 * @param workdate
	 * @param worktime
	 * @param location
	 * @param worknum
	 */
	public void writeLateMessage(String sname,String workdate,String worktime,int latetime,String location,int worknum){
		//sql语句
		String sql = "INSERT INTO latemessage VALUES(?,?,?,?,?,?)";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,workdate,worktime,latetime,location,worknum};
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取迟到次数
	 * @param sid
	 * @return
	 */
	public int lateNum(String sid,String beginTime,String endTime){
		String sname = getStuName(sid);
		//sql语句
		String sql = "SELECT COUNT(*) FROM latemessage WHERE sname=? AND workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,beginTime,endTime};
			TxQueryRunner tqr = new TxQueryRunner();
			Object query = tqr.query(sql, new ScalarHandler(),params);
			int result = Integer.parseInt(query.toString());
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	/**
	 * 写入替班记录
	 * @param sname
	 * @param prename
	 * @param workdate
	 * @param worktime
	 * @param location
	 * @param worknum
	 */
	public void relay(String sname,String prename,String workdate,String worktime,String location,int worknum){
		//sql语句
		String sql = "INSERT INTO relaymessage VALUES(?,?,?,?,?,?)";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,prename,workdate,worktime,location,worknum};
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 请假功能
	 * @param sname
	 * @param workdate
	 * @param worktime
	 * @param location
	 * @param message
	 */
	public void leave(String sname,String workdate,String worktime,String location,String message){
		//sql语句
		String sql = "INSERT INTO leavemessage VALUES(?,?,?,?,?)";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,workdate,worktime,location,message};
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取正常值班信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<WorkMessage> getWorkTime(String sname,String beginTime,String endTime){
		//sql语句
		String sql = "SELECT * FROM workmessage WHERE sname=? AND workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,beginTime,endTime};
			TxQueryRunner tqr = new TxQueryRunner();
			List<WorkMessage> list = tqr.query(sql, new BeanListHandler<WorkMessage>(WorkMessage.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取当天正常值班信息
	 * @param beginTime
	 * @param endTime
	 * @param location
	 * @return
	 */
	public List<WorkMessage> getWorkMessage(String beginTime,String endTime,String location){
		//sql语句
		String sql = "SELECT * FROM workmessage WHERE workdate>=? AND workdate<=? AND location=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {beginTime,endTime,location};
			TxQueryRunner tqr = new TxQueryRunner();
			List<WorkMessage> list = tqr.query(sql, new BeanListHandler<WorkMessage>(WorkMessage.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取替班信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<RelayMessage> getRelayTime(String sname,String beginTime,String endTime){
		//sql语句
		String sql = "SELECT * FROM relaymessage WHERE sname=? AND workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,beginTime,endTime};
			TxQueryRunner tqr = new TxQueryRunner();
			List<RelayMessage> list = tqr.query(sql, new BeanListHandler<RelayMessage>(RelayMessage.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取替班信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<RelayMessage> getRelayTime(String beginTime,String endTime){
		//sql语句
		String sql = "SELECT * FROM relaymessage WHERE workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {beginTime,endTime};
			TxQueryRunner tqr = new TxQueryRunner();
			List<RelayMessage> list = tqr.query(sql, new BeanListHandler<RelayMessage>(RelayMessage.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取当天替班信息
	 * @param beginTime
	 * @param endTime
	 * @param location
	 * @return
	 */
	public List<RelayMessage> getRelayMessage(String beginTime,String endTime,String location){
		//sql语句
		String sql = "SELECT * FROM relaymessage WHERE workdate>=? AND workdate<=? AND location=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {beginTime,endTime,location};
			TxQueryRunner tqr = new TxQueryRunner();
			List<RelayMessage> list = tqr.query(sql, new BeanListHandler<RelayMessage>(RelayMessage.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取迟到信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LateMessage> getLateTime(String sname,String beginTime,String endTime){
		//sql语句
		String sql = "SELECT * FROM latemessage WHERE sname=? AND workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,beginTime,endTime};
			TxQueryRunner tqr = new TxQueryRunner();
			List<LateMessage> list = tqr.query(sql, new BeanListHandler<LateMessage>(LateMessage.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取加班信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<OverTime> getOverTime(String sname,String beginTime,String endTime){
		//sql语句
		String sql = "SELECT * FROM overtime WHERE sname=? AND workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,beginTime,endTime};
			TxQueryRunner tqr = new TxQueryRunner();
			List<OverTime> list = tqr.query(sql, new BeanListHandler<OverTime>(OverTime.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取时间段内的迟到信息
	 * @param beginTime
	 * @param endTime
	 * @param location
	 * @return
	 */
	public List<LateMessage> getLateMessage(String beginTime,String endTime,String location){
		//sql语句
		String sql = "SELECT * FROM latemessage WHERE workdate>=? AND workdate<=? AND location=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {beginTime,endTime,location};
			TxQueryRunner tqr = new TxQueryRunner();
			List<LateMessage> list = tqr.query(sql, new BeanListHandler<LateMessage>(LateMessage.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取时间段内的请假信息
	 * @param beginTime
	 * @param endTime
	 * @param location
	 * @return
	 */
	public List<LeaveMessage> getLeaveMessage(String beginTime,String endTime,String location){
		//sql语句
		String sql = "SELECT * FROM leavemessage WHERE workdate>=? AND workdate<=? AND location=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {beginTime,endTime,location};
			TxQueryRunner tqr = new TxQueryRunner();
			List<LeaveMessage> list = tqr.query(sql, new BeanListHandler<LeaveMessage>(LeaveMessage.class),params);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取other
	 * @return
	 */
	public Other getOther(){
		//sql语句
		String sql = "SELECT * FROM other WHERE num=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			return tqr.query(sql, new BeanHandler<Other>(Other.class),1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取ip对象
	 * @param ip
	 * @return
	 */
	public Ip ip(String ip){
		//sql语句
		String sql = "SELECT * FROM ip WHERE ip=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			return tqr.query(sql, new BeanHandler<Ip>(Ip.class) ,ip);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 写入糖果数
	 * @param num
	 */
	public void writeCandy(int num){
		//sql语句
		String sql = "UPDATE other SET candy=? WHERE num=1";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,num);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
}
