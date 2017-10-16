package controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserException;
import model.db.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		try {
			if (UserDao.getInstance().userExists(email, pass)) {
				request.getSession().setAttribute("logged", true);
				request.getSession().setAttribute("user", UserDao.getInstance().getUserByEmail(email));
				response.sendRedirect("main.jsp");
				return;
			}
			else {
				response.sendRedirect("register.jsp");
			}
		} catch (SQLException | UserException e) {
			e.printStackTrace();
		}

	}

}
