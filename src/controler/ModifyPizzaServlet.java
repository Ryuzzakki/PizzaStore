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
		System.out.println(ingredientid);
		
		
		String pizzaId = request.getParameter("productId");
		String currPizzaId = pizzaId;
		System.out.println(pizzaId);
		request.getRequestDispatcher("modify.jsp").forward(request, response);

	}

}
