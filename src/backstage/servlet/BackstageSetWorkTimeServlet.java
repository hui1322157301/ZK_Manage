package backstage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backstage.service.BackstageService;

public class BackstageSetWorkTimeServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		BackstageService service = new BackstageService();
		String set_num = request.getParameter("set_num");
		String set_begintime = request.getParameter("set_begintime");
		String set_endtime = request.getParameter("set_endtime");
		String set_worknum = request.getParameter("set_worknum");
		service.setWorkDateAndWorkTime(set_num,set_begintime,set_endtime,set_worknum);
		response.getWriter().print("1");
	}

}
