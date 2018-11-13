package backstage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.domain.Student;
import backstage.service.BackstageService;

public class BackstageDeleteStudentServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String delete_sname = request.getParameter("delete_sname");
		BackstageService service = new BackstageService();
		Student student = service.getStudent(delete_sname);
		if(student == null){
			response.getWriter().print("-1");
			return;
		}
		service.deleteStudent(delete_sname);
		service.deleteWorkMessage(delete_sname);
		service.deleteLateMessage(delete_sname);
		service.deleteRelayMessage(delete_sname);
		service.deleteLeaveMessage(delete_sname);
		service.deleteOverTime(delete_sname);
		response.getWriter().print("1");
	}

}
