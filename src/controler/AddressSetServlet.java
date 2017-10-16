package controler;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddressSetServlet
 */
@WebServlet("/address")
public class AddressSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean restaurant = request.getParameter("rest") != null;
		boolean home = request.getParameter("home") != null;

		System.out.println(restaurant);
		System.out.println(home);
		
		//TODO update sesion
		
		response.sendRedirect("main.jsp");
		
		

	}

}
