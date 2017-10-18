package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.UserException;
import model.db.OrderDao;
import model.db.ProductDao;
import model.db.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		try {
			if (UserDao.getInstance().userExists(email, pass)) {
				// session scope setting to be logged
				request.getSession().setAttribute("logged", true);
				request.getSession().setAttribute("user", UserDao.getInstance().getUserByEmail(email));
				request.getRequestDispatcher("address.jsp").forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		} catch (SQLException | UserException e) {
			e.printStackTrace();
		}

	}

}
