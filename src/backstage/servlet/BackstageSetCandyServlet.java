package backstage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backstage.service.BackstageService;

public class BackstageSetCandyServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		BackstageService service = new BackstageService();
		int candy_num = Integer.parseInt(request.getParameter("candy_num"));
		service.setCandy(candy_num);
		request.getRequestDispatcher("/backstage/BackstageCandyServlet").forward(request, response);
	}

}
