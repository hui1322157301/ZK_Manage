package zk_manage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.service.ManageService;

public class RevisePwdServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//创建ManageService实体对象
		ManageService service = new ManageService();
		//获取参数
		String sid = request.getParameter("sid");
		String prepwd = request.getParameter("prepwd");
		String newpwd = request.getParameter("newpwd");
		//调用service修改密码方法
		if(service.revisePwd(sid,prepwd,newpwd)){
			response.getWriter().print("1");
		}else{
			response.getWriter().print("0");
		}
	}

}
