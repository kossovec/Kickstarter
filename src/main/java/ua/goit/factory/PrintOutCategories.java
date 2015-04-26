package ua.goit.factory;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


//@WebServlet("/Categories")
public class PrintOutCategories extends HttpServlet {

	private DBConnectionManager connMgr;

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		connMgr = DBConnectionManager.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");	
		Connection con = connMgr.getConnection();
		if (con == null) {
			response.getWriter().print("Can't get connection");						
		}
		else {
			response.getWriter().print("Connection is done");
		}
		connMgr.freeConnection(con);
	}

	public void destroy() {
		connMgr.release();
		super.destroy();
	}
}
