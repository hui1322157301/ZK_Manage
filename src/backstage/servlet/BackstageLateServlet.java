package backstage.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.domain.LateMessage;
import backstage.service.BackstageService;

public class BackstageLateServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");
		String late_sname = request.getParameter("late_sname");
		begin_time = begin_time+" 00:00:00";
		end_time = end_time+" 00:00:00";
		BackstageService service = new BackstageService();
		List<LateMessage> latelist = new ArrayList<LateMessage>();
		if(late_sname == ""){
			latelist = service.checkLateMessage(begin_time,end_time);
		}else{
			latelist = service.checkLateMessage(late_sname,begin_time,end_time);
		}
		request.setAttribute("latelist", latelist);
		request.getRequestDispatcher("/backstage/backstage_late.jsp").forward(request, response);
	}
}
