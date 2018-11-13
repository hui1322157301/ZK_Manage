package backstage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backstage.service.BackstageService;

public class BackstageRegisteCodeServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String registecode = request.getParameter("registecode");;
		BackstageService service = new BackstageService();
		service.setRegisteCode(registecode);
		response.getWriter().print("1");
	}
}
