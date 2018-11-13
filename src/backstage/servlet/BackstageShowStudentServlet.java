package backstage.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk_manage.domain.Student;
import backstage.service.BackstageService;

public class BackstageShowStudentServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String student_sname = request.getParameter("student_sname");
		BackstageService service = new BackstageService();
		List<Student> stulist = new ArrayList<Student>();
		if(student_sname == ""){
			stulist = service.showStudent();
		}else{
			stulist = service.showStudent(student_sname);
		}
		request.setAttribute("stulist", stulist);
		request.getRequestDispatcher("/backstage/backstage_showstu.jsp").forward(request, response);
	}

}
