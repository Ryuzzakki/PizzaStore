package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifyPizzaServlet
 */
@WebServlet("/modify")
public class ModifyPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ingredientid = request.getParameter("ingredientId");
		String currentId = request.getParameter("productId");

		if (currentId != null) {

		}

		System.out.println(ingredientid);
		System.out.println(currentId);

		response.sendRedirect("modify.jsp?productId=" + currentId);
		// request.getRequestDispatcher("modify.jsp?productId=" +
		// currentId).forward(request, response);
	}
}
