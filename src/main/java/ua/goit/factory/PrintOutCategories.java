package ua.goit.factory;

import java.io.IOException;
import java.sql.Connection;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ua.goit.factory.DBConnectionManager;

@WebServlet("/Categories")
public class PrintOutCategories extends HttpServlet {

	private DBConnectionManager connMgr;

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		connMgr = DBConnectionManager.getInstance();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");	
		Connection con = connMgr.getConnection("idb");
		if (con == null) {
			response.getWriter().print("Can't get connection");						
		}
		else {
			response.getWriter().print("Connection is done");
		}



		/*Set<String> categories = new Categories().getCategories();

				//String jspUrl = request.getContextPath() + "/Projects.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Projects");
				request.setAttribute("set", categories);
				dispatcher.forward(request, response);*/
		//
		//		response.getWriter().print("<html>");
		//		response.getWriter().print("<body>");
		//		for (String i : categories) {
		//			response.getWriter().println("<a href=http://localhost:8080/group1/Projects?category=" 
		//					+ i + ">" + i + "</a><br>");	
		//		}
		//		response.getWriter().print("</body>");
		//		response.getWriter().print("</html>");
		
		connMgr.freeConnection("idb", con);
	}

	public void destroy() {
		connMgr.release();
		super.destroy();
	}
}
