package controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ingredient;
import model.Order;
import model.Product;
import model.db.IngredientDao;

/**
 * Servlet implementation class RemoveIngredientServlet
 */
@WebServlet("/removeing")
public class RemoveIngredientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String product = request.getParameter("productId");
		String ingredient = request.getParameter("ingredientId");
		Order order = (Order) request.getSession().getAttribute("order");

		try {
			Product currProduct = order.findProductInMap(Long.valueOf(product));
			Ingredient currIngredient = IngredientDao.getInstance().getIngredient(Long.valueOf(ingredient));
			System.out.println(currProduct);
			currProduct.removeIngredient(currIngredient);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("modify.jsp?productId=" + product);

	}

}
