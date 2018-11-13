package zk_manage.test;

import java.util.List;

import org.junit.Test;

import zk_manage.dao.ManageDao;
import zk_manage.domain.Student;
import zk_manage.domain.WorkMessage;
import backstage.dao.BackstageDao;
import backstage.service.BackstageService;



public class UtilsTest {
	
	@Test
	public void fun01(){
		ManageDao dao = new ManageDao();
		String sid = "15151315";
		String pwd = "12345678";
		Student stu = dao.loginVaildate(sid, pwd);
		System.out.println(stu);
	}
	@Test
	public void fun02(){
		ManageDao dao = new ManageDao();
		String sid = "15151315";
		boolean stu = dao.SidValidate(sid);
		System.out.println(stu);
	}
	@Test
	public void fun03(){
		ManageDao dao = new ManageDao();
		String sid = "15151314";
		String pwd = "123456";
		String sname = "张三";
		dao.registeValidate(sid, pwd, sname);
	}
	@Test
	public void fun04(){
		ManageDao dao = new ManageDao();
		String sid = "15151314";
		String prepwd = "1111111111";
		String newpwd = "123456";
		System.out.println(dao.revisePwd(sid, prepwd, newpwd));
	}
	@Test
	public void fun05(){
		ManageDao dao = new ManageDao();
		String t1 = "08:50:00";
		String t2 = "07:40:00";
		System.out.println(dao.isTime(t1, t2));
	}
	@Test
	public void fun06(){
		ManageDao dao = new ManageDao();
		String sid = "15151315";
		String sname = dao.getStuName(sid);
		System.out.println(sname);
	}
	@Test
	public void fun07(){
		ManageDao dao = new ManageDao();
		String sname = "张文辉";
		String datetime = "one";
		List<WorkMessage> wm = dao.selectWorkMessage(sname,datetime);
		System.out.println(wm);
	}
	@Test
	public void fun08(){
		ManageDao dao = new ManageDao();
		String sid = "15151315";
		String signDate = "2018-10-26";
		String signTime = "two";
		System.out.println(dao.signValidate(sid, signDate, signTime));
	}

	@Test
	public void fun10(){
		ManageDao dao = new ManageDao();
		String sid = "15151315";
		String beginTime = "2018-10-01 00:00:00";
		String endTime = "2018-11-01 00:00:00";
		int i = dao.lateNum(sid,beginTime,endTime);
		System.out.println(i);
	}
	@Test
	public void fun11(){
		ManageDao dao = new ManageDao();
		String sname = "张文辉";
		String prename = "轩轩";
		String workdate = "2018-10-01 00:00:00";
		String worktime = "three";
		String location = "a";
		int worknum = 2;
		dao.relay(sname, prename, workdate, worktime, location, worknum);
	}
	@Test
	public void fun12(){
		ManageDao dao = new ManageDao();
		String sname = "张文辉";
		String workdate = "2018-10-01 00:00:00";
		String worktime = "three";
		String location = "a";
		String message = "123";
		dao.leave(sname, workdate, worktime, location, message);
	}
	@Test
	public void fun13(){
		BackstageDao dao = new BackstageDao();
		String beginTime = "2018-10-01 00:00:00";
		String endTime = "2018-10-31 00:00:00";
		List<WorkMessage> workMessage = dao.getWorkMessage(beginTime, endTime);
		for(WorkMessage wm : workMessage){
			System.out.println(wm);
		}
	}
	@Test
	public void fun14(){
		BackstageService service = new BackstageService();
		String beginTime = "2018-10-01 00:00:00";
		String endTime = "2018-10-31 00:00:00";
		List<WorkMessage> workMessage = service.checkWorkMessage(beginTime, endTime);
		for(WorkMessage wm : workMessage){
			System.out.println(wm);
		}
	}
}
