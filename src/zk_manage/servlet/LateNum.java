package zk_manage.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.service.ManageService;

public class LateNum extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ManageService service = new ManageService();
		String sid = request.getParameter("sid");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int m = calendar.get(Calendar.MONTH)+1;
		String month = "";
		switch (m) {
			case 1:
				month = "01";
				break;
			case 2:
				month = "02";
				break;
			case 3:
				month = "03";
				break;
			case 4:
				month = "04";
				break;
			case 5:
				month = "05";
				break;
			case 6:
				month = "06";
				break;
			case 7:
				month = "07";
				break;
			case 8:
				month = "08";
				break;
			case 9:
				month = "09";
				break;
			case 10:
				month = "10";
				break;
			case 11:
				month = "11";
				break;
			case 12:
				month = "12";
				break;
		}
		String beginTime = year+"-"+month+"-01 00:00:00";
		String endTime = year+"-"+(month+1)+"-01 00:00:00";
		int lateNum = service.lateNum(sid,beginTime,endTime);
		response.getWriter().print(lateNum);
	}

}
