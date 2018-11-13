package backstage.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.domain.WorkMessage;
import backstage.service.BackstageService;

public class BackstageWorkServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");
		String work_sname = request.getParameter("work_sname");
		begin_time = begin_time+" 00:00:00";
		end_time = end_time+" 00:00:00";
		BackstageService service = new BackstageService();
		List<WorkMessage> wmlist = new ArrayList<WorkMessage>();
		if(work_sname == ""){
			wmlist = service.checkWorkMessage(begin_time,end_time);
		}else{
			wmlist = service.checkWorkMessage(work_sname,begin_time,end_time);
		}
		request.setAttribute("wmlist", wmlist);
		request.getRequestDispatcher("/backstage/backstage_work.jsp").forward(request, response);
	}

}
