package backstage.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backstage.domain.BackstageCheck;
import backstage.service.BackstageService;

public class BackstageCheckServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		BackstageService service = new BackstageService();
		//获取参数
		String check_sname = request.getParameter("check_sname");
		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");
		begin_time = begin_time+" 00:00:00";
		end_time = end_time+" 00:00:00";
		List<BackstageCheck> list = new ArrayList<BackstageCheck>(); 
		BackstageCheck bc = new BackstageCheck();
		if(check_sname == ""){
			list = service.check(begin_time,end_time);
		}else{
			bc = service.check(check_sname,begin_time,end_time);
			list.add(bc);
		}
		request.setAttribute("bclist", list);
		request.getRequestDispatcher("/backstage/backstage_check.jsp").forward(request,response);
	}

}
