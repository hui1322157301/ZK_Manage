package backstage.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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

public class BackstageDao {
	
	/**
	 * 获取所有正常值班信息
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	public List<WorkMessage> getWorkMessage(String begintime,String endtime){
		//sql语句
		String sql = "SELECT * FROM workmessage WHERE workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {begintime,endtime};
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
	 * 查询某人正常值班信息
	 * @param sname
	 * @param begintime
	 * @param endtime
	 * @return
	 */
	public List<WorkMessage> getWorkMessage(String sname,String begintime,String endtime){
		//sql语句
		String sql = "SELECT * FROM workmessage WHERE sname=? AND workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,begintime,endtime};
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
	 * 获取所有替班信息
	 * @param sname
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
	 * 查询某人替班信息
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
	 * 获取所有迟到信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LateMessage> getLateTime(String beginTime,String endTime){
		//sql语句
		String sql = "SELECT * FROM latemessage WHERE workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {beginTime,endTime};
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
	 * 查询某人迟到信息
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
	 * 获取迟所有加班信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<OverTime> getOverTime(String beginTime,String endTime){
		//sql语句
		String sql = "SELECT * FROM overtime WHERE workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {beginTime,endTime};
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
	 * 查询某人加班信息
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
	 * 得到所有学生
	 * @return
	 */
	public List<Student> getStudent(){
		//sql语句
		String sql = "SELECT * FROM studentlist";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			List<Student> list = tqr.query(sql, new BeanListHandler<Student>(Student.class));
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
	 * 获取请假信息
	 * @param sname
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LeaveMessage> getLeaveMessage(String sname,String beginTime,String endTime){
		//sql语句
		String sql = "SELECT * FROM leavemessage WHERE sname=? AND workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,beginTime,endTime};
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
	 * 获取请假信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LeaveMessage> getLeaveMessage(String beginTime,String endTime){
		//sql语句
		String sql = "SELECT * FROM leavemessage WHERE workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {beginTime,endTime};
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
	 * 获取迟到信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<LateMessage> getLateMessage(String beginTime,String endTime){
		//sql语句
		String sql = "SELECT * FROM latemessage WHERE workdate>=? AND workdate<=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {beginTime,endTime};
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
	 * 设置加班
	 * @param sname
	 * @param workdate
	 * @param message
	 * @param worknum
	 */
	public void setOverTime(String sname,String workdate,String message,int worknum){
		//sql语句
		String sql = "INSERT INTO overtime VALUES(?,?,?,?)";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {sname,workdate,message,worknum};
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
	 * 删除学生
	 * @param sname
	 */
	public void deleteStudent(String sname){
		//sql语句
		String sql = "DELETE FROM studentlist WHERE sname=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,sname);
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
	 * 根据姓名获取学生对象
	 * @param sname
	 * @return
	 */
	public Student getStudent(String sname){
		//sql语句
		String sql = "SELECT * FROM studentlist WHERE sname=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			return tqr.query(sql, new BeanHandler<Student>(Student.class),sname);
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
	 * 删除学生正常值班信息
	 * @param sname
	 */
	public void deleteWorkMessage(String sname) {
		//sql语句
		String sql = "DELETE FROM workmessage WHERE sname=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,sname);
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
	 * 删除学生迟到信息
	 * @param sname
	 */
	public void deleteLateMessage(String sname) {
		//sql语句
		String sql = "DELETE FROM latemessage WHERE sname=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,sname);
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
	 * 删除学生替班信息
	 * @param sname
	 */
	public void deleteRelayMessage(String sname) {
		//sql语句
		String sql = "DELETE FROM relaymessage WHERE sname=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,sname);
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
	 * 删除学生请假信息
	 * @param sname
	 */
	public void deleteLeaveMessage(String sname) {
		//sql语句
		String sql = "DELETE FROM leavemessage WHERE sname=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,sname);
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
	 * 删除学生加班信息
	 * @param sname
	 */
	public void deleteOverTime(String sname) {
		//sql语句
		String sql = "DELETE FROM overtime WHERE sname=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,sname);
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
	 * 设置注册码
	 * @param registecode
	 */
	public void setRegisteCode(String registecode) {
		//sql语句
		String sql = "UPDATE other SET registecode=? WHERE num=1";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,registecode);
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
	 * 获取IP对象
	 * @return
	 */
	public List<Ip> getIp() {
		//sql语句
		String sql = "SELECT * FROM ip";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			return tqr.query(sql, new BeanListHandler<Ip>(Ip.class));
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
	 * 删除IP
	 * @param ip
	 */
	public void deleteIp(String ip) {
		//sql语句
		String sql = "DELETE FROM ip WHERE ip=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,ip);
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
	 * 获取值班时间工时表
	 * @return
	 */
	public List<WorkTime> getWorkTime() {
		//sql语句
		String sql = "SELECT * FROM worktime";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			return tqr.query(sql, new BeanListHandler<WorkTime>(WorkTime.class));
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
	 * 设置值班时间及工时表
	 */
	public void setWorkDateAndWorkTime(String num,String begintime,String endtime,String worknum) {
		//sql语句
		String sql = "UPDATE worktime SET begintime=?,endtime=?,worknum=? WHERE num=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			Object[] params = {begintime,endtime,worknum,num};
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
	 * 获取全部学生对象
	 * @return
	 */
	public List<Student> showStudent() {
		//sql语句
		String sql = "SELECT * FROM studentlist";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			List<Student> list = tqr.query(sql, new BeanListHandler<Student>(Student.class));
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
	 * 根据姓名获取学生对象
	 * @param sname
	 * @return
	 */
	public List<Student> showStudent(String sname) {
		//sql语句
		String sql = "SELECT * FROM studentlist WHERE sname=?";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			List<Student> list = tqr.query(sql, new BeanListHandler<Student>(Student.class),sname);
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
	 * 添加IP
	 * @param ip
	 */
	public void addIp(String ip) {
		//sql语句
		String sql = "INSERT INTO ip VALUES(?)";
		//得到Connection
		Connection con = null;
		try {
			con = JdbcUtils.getConnection();
			TxQueryRunner tqr = new TxQueryRunner();
			tqr.update(sql,ip);
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
	public Other getRegisteCode() {
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
}
