package backstage.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpdateTableServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(dfif);
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			FileItem a_table = list.get(0);
			FileItem c_table = list.get(1);
			String savepath = this.getServletContext().getRealPath("/worktable");
			File a_tableFile = new File(savepath,"a_table.png");
			File c_tableFile = new File(savepath,"c_table.png");
			a_table.write(a_tableFile);
			c_table.write(c_tableFile);
			request.getRequestDispatcher("/backstage/backstage_table.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
		
	}

}
