package zk_manage.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.domain.WorkTime;
import zk_manage.service.ManageService;

public class SignServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ManageService service = new ManageService();
		//获取所以参数
		String sid = request.getParameter("sid");
		String sign_date = request.getParameter("sign_date");
		String sign_time = request.getParameter("sign_time");
		String sign_location = request.getParameter("sign_location");
		String ip = request.getRemoteAddr();
		//获取签到的当前完整时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String times = df.format(new Date());
		//获取签到的当前年月日
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String nyr = df1.format(new Date());
		//判断迟到次数
		String year = nyr.substring(0, 4);
		String month = nyr.substring(5, 7);
		String beginTime = year+"-"+month+"-01 00:00:00";
		String endTime = year+"-"+(month+1)+"-01 00:00:00";
		int lateNum = service.lateNum(sid, beginTime, endTime);
		//获取签到的当前时分秒
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		String sfm = df2.format(new Date());
		//对时分秒进行判断
		WorkTime workTime = service.getWorkTime(sign_time);
		String begintime = workTime.getBegintime();
		String endtime = workTime.getEndtime();
		//判断签到时间和填写时间是否正确
		if(nyr.equals(sign_date)){
			//正确,再判断值班时间
			if(service.signValidate(sid, nyr, sign_time)){
				//可以签到
				int result = service.sign(ip,sid, times, begintime, sfm, sign_time, sign_location,lateNum);
				response.getWriter().print(result);
				return;
			}else{
				//非初次签到
				response.getWriter().print("-2");
				return;
			}
		}else{
			//错误，签到的年月日和选择的年月日不同
			response.getWriter().print("-1");
		}
	}

}
