package backstage.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.domain.Ip;
import backstage.service.BackstageService;

public class BackstageDeleteIpServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		BackstageService service = new BackstageService();
		String set_ip = request.getParameter("set_ip");
		//验证ip
		List<Ip> ip = service.getIp();
		for(Ip i : ip){
			if(set_ip.equals(i.getIp())){
				//删除IP
				service.deleteIp(set_ip);
				response.getWriter().print("1");
				return;
			}
		}
		response.getWriter().print("-1");
	}

}
