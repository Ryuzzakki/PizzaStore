package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean logged = false;

		if (request.getSession().getAttribute("logged") != null) {
			logged = (boolean) request.getSession().getAttribute("logged");
		}
		if (logged) {
			response.sendRedirect("main.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}
	}

}
