package zk_manage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.service.ManageService;

public class CheckServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ManageService service = new ManageService();
		//获取参数
		String sname = request.getParameter("sname");
		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");
		begin_time = begin_time+" 00:00:00";
		end_time = end_time+" 00:00:00";
		//查询值班工时
		String workTime = String.valueOf(service.getWorkTime(sname, begin_time, end_time));
		//查询替班工时
		String RelayTime = String.valueOf(service.getRelayTime(sname, begin_time, end_time));
		//查询迟到工时
		String LateTime = String.valueOf(service.getLateTime(sname, begin_time, end_time));
		//查询迟到工时
		String OverTime = String.valueOf(service.getOverTime(sname, begin_time, end_time));
		//总工时
		String SumTime = String.valueOf(service.getSumTime(sname, begin_time, end_time));
		//输出
		response.getWriter().print(workTime+"#"+RelayTime+"#"+LateTime+"#"+OverTime+"#"+SumTime);
	}

}
