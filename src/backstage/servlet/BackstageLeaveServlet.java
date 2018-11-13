package backstage.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.domain.LeaveMessage;
import backstage.service.BackstageService;

public class BackstageLeaveServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");
		String leave_sname = request.getParameter("leave_sname");
		begin_time = begin_time+" 00:00:00";
		end_time = end_time+" 00:00:00";
		BackstageService service = new BackstageService();
		List<LeaveMessage> lmlist = new ArrayList<LeaveMessage>();
		if(leave_sname == ""){
			lmlist = service.checkLeaveMessage(begin_time,end_time);
		}else{
			lmlist = service.checkLeaveMessage(leave_sname,begin_time,end_time);
		}
		request.setAttribute("lmlist", lmlist);
		request.getRequestDispatcher("/backstage/backstage_leave.jsp").forward(request, response);
	}

}
