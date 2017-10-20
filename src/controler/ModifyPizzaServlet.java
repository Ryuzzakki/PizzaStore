package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ingredient;
import model.Product;
import model.db.IngredientDao;
import model.db.ProductDao;

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
		Long id = Long.valueOf(currentId);
		Ingredient ingredient = null;
		try {
			Product product = ProductDao.getInstance().getProduct(id);
			if (request.getSession().getAttribute("modifiedProduct") == null) {
				request.getSession().setAttribute("modifiedProduct", product);
			}
			if (ingredientid != null) {
				ingredient = IngredientDao.getInstance().getIngredient(Long.valueOf(ingredientid));
				product = (Product) request.getSession().getAttribute("modifiedProduct");
				product.addIngredient(ingredient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HashSet<Ingredient> ingredients = ((Product) request.getSession().getAttribute("modifiedProduct"))
				.getIngredients();
		
		System.out.println(ingredients.size());
		//TODO hashcode stuffs!!! 
		
		response.sendRedirect("modify.jsp?productId=" + currentId);
	}
}
