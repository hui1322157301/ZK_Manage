package zk_manage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zk_manage.domain.Student;
import zk_manage.service.ManageService;

public class LoginValidateServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//创建ManageService实体对象
		ManageService service = new ManageService();
		//获取参数
		String sid = request.getParameter("login_sid");
		String pwd = request.getParameter("login_pwd");
		//调用service验证注登录方法
		Student student = service.loginValidate(sid, pwd);
		//对结果进行判断
		if(student != null){
			//得到用户名
			String sname = student.getSname();
			//判断登录级别
			if("管理员".equals(student.getSname())){
				//登录者为管理员，跳转到管理员后台
				//获取session
				HttpSession session = request.getSession();
				session.setAttribute("god_sid", sid);
				session.setAttribute("god_sname", sname);
				response.sendRedirect("/ZK_Manage/backstage/backstage_index.jsp");
			}else{
				//普通用户，登录成功，跳转到客户端页面
				//获取session
				HttpSession session = request.getSession();
				session.setAttribute("sid", sid);
				session.setAttribute("sname", sname);
				response.sendRedirect("/ZK_Manage/frame/frame_index.jsp");
			}
		}else{
			//登录失败
			response.sendRedirect("/ZK_Manage/loginFail.html");
		}
	}
}
