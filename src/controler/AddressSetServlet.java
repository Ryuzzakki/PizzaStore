package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Restaurant;
import model.db.RestaurantDao;

/**
 * Servlet implementation class AddressSetServlet
 */
@WebServlet("/address")
public class AddressSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String choice = request.getParameter("address");
		request.getSession().setAttribute("address", choice);
		//TODO See this
		try {
			Restaurant defaultRest = RestaurantDao.getInstance().getRestaurant(1);
			request.getSession().setAttribute("restaurant", defaultRest);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("main.jsp");

	}

}
