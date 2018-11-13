package backstage.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.domain.OverTime;
import backstage.service.BackstageService;

public class BackstageOverServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");
		String over_sname = request.getParameter("over_sname");
		begin_time = begin_time+" 00:00:00";
		end_time = end_time+" 00:00:00";
		BackstageService service = new BackstageService();
		List<OverTime> overlist = new ArrayList<OverTime>();
		if(over_sname == ""){
			overlist = service.checkOverTime(begin_time,end_time);
		}else{
			overlist = service.checkOverTime(over_sname,begin_time,end_time);
		}
		request.setAttribute("omlist", overlist);
		request.getRequestDispatcher("/backstage/backstage_over.jsp").forward(request, response);
	}

}
