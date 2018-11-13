package zk_manage.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.service.ManageService;

public class RelayServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ManageService service = new ManageService();
		//获取参数
		String sid = request.getParameter("sid");
		String relay_location = request.getParameter("relay_location");
		String relay_date = request.getParameter("relay_date");
		String relay_time = request.getParameter("relay_time");
		String relay_prename = request.getParameter("relay_prename");
		String relay_sname = request.getParameter("relay_sname");
		//获取签到的当前完整时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String times = df.format(new Date());
		//对时间进行判断
		if(times.contains(relay_date)){
			//正确,再判断值班时间
			if(service.signValidate(sid, relay_date, relay_time)){
				//当天时间
				int result = service.relay(relay_sname,relay_prename,times,relay_time,relay_location);
				response.getWriter().print(result);
			}else{
				//非初次签到
				response.getWriter().print("-3");
				return;
			}
		}else{
			//非当天时间
			response.getWriter().print("-1");
		}
	}

}
