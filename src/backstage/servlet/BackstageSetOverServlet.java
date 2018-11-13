package backstage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backstage.service.BackstageService;

public class BackstageSetOverServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String over_sname1 = request.getParameter("over_sname1");
		String over_date = request.getParameter("over_date");
		String over_message = request.getParameter("over_message");
		int over_num = Integer.parseInt(request.getParameter("over_num"));
		BackstageService service = new BackstageService();
		service.setOverTime(over_sname1,over_date,over_message,over_num);
		response.getWriter().print("1");
	}

}
