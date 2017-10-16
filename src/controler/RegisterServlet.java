package controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.db.UserDao;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fName = request.getParameter("first_name");
		String lName = request.getParameter("last_name");
		String pass = request.getParameter("pass");
		String confPass = request.getParameter("passconfirm");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		try {
			if (UserDao.getInstance().userExistsByEmail(email)) {
				response.sendRedirect("existsuser.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
