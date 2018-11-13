package zk_manage.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.domain.AWorkSign;
import zk_manage.domain.CWorkSign;
import zk_manage.service.ManageService;

public class WorkSignServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ManageService service = new ManageService();
		//获取签到的当前年月日
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String nyr = df1.format(new Date());
		//查询A教值班情况
		List<AWorkSign> alist = service.getAWorkSign(nyr);
		//查询C教值班情况
		List<CWorkSign> clist = service.getCWorkSign(nyr);
		//将数据存入request中
		request.setAttribute("alist", alist);
		request.setAttribute("clist", clist);
		request.getRequestDispatcher("/frame/frame_right.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
