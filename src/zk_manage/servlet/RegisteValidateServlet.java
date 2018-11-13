package zk_manage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.service.ManageService;

public class RegisteValidateServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//创建ManageService实体对象
		ManageService service = new ManageService();
		//获取参数
		String sid = request.getParameter("registe_sid");
		String pwd = request.getParameter("registe_pwd");
		String sname = request.getParameter("registe_sname");
		String code = request.getParameter("registe_code");
		//对学号进行判断
		if(!service.SidValidate(sid)){
			//调用service验证注册方法
			boolean result = service.registeValidate(sid,pwd,sname,code);
			//对结果进行判断
			if(result){
				//注册成功
				response.sendRedirect("/ZK_Manage/registeSuccess.html");
			}else{
				//注册失败
				response.sendRedirect("/ZK_Manage/registeFail.html");
			}
		}else{
			//学号已注册
			response.sendRedirect("/ZK_Manage/registeFail.html");
		}
	}

}
