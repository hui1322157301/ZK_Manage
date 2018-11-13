package backstage.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.domain.Ip;
import zk_manage.domain.WorkTime;
import backstage.service.BackstageService;

public class BackstageSetServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		BackstageService service = new BackstageService();
		List<WorkTime> wtlist = service.getWorkTime();
		List<Ip> iplist = service.getIp();
		String registeCode = service.getRegisteCode();
		request.setAttribute("iplist", iplist);
		request.setAttribute("wtlist", wtlist);
		request.setAttribute("registecode", registeCode);
		request.getRequestDispatcher("/backstage/backstage_set.jsp").forward(request, response);
	}

}
