package zk_manage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.service.ManageService;

public class LeaveServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ManageService service = new ManageService();
		//获取所以参数
		String leave_location = request.getParameter("leave_location");
		String leave_date = request.getParameter("leave_date");
		String leave_time = request.getParameter("leave_time");
		String leave_sname = request.getParameter("leave_sname");
		String leave_message = request.getParameter("leave_message");
		service.leave(leave_sname,leave_date,leave_time,leave_location,leave_message);
		response.getWriter().print(1);
	}

}
