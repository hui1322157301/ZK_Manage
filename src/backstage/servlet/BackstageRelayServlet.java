package backstage.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.domain.RelayMessage;
import backstage.service.BackstageService;

public class BackstageRelayServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");
		String relay_sname = request.getParameter("relay_sname");
		begin_time = begin_time+" 00:00:00";
		end_time = end_time+" 00:00:00";
		BackstageService service = new BackstageService();
		List<RelayMessage> rmlist = new ArrayList<RelayMessage>();
		if(relay_sname == ""){
			rmlist = service.checkRelayMessage(begin_time,end_time);
		}else{
			rmlist = service.checkRelayMessage(relay_sname,begin_time,end_time);
		}
		request.setAttribute("rmlist", rmlist);
		request.getRequestDispatcher("/backstage/backstage_relay.jsp").forward(request, response);
	}

}
