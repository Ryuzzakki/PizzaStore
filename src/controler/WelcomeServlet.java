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

import model.Ingredient;
import model.Product;
import model.db.IngredientDao;
import model.db.ProductDao;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// app scope setting all products
		ServletContext application = getServletConfig().getServletContext();
		synchronized (application) {
			if (application.getAttribute("products") == null || application.getAttribute("ingredients") == null) {
				ArrayList<Product> products = new ArrayList<>();
				ArrayList<Ingredient> ingredients = new ArrayList<>();
				try {
					products = ProductDao.getInstance().getAllProducts();
					ingredients = IngredientDao.getInstance().getAllIngredients();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				application.setAttribute("products", products);
				application.setAttribute("ingredients", ingredients);
			}
		}

		boolean logged = false;

		if (request.getSession().getAttribute("logged") != null) {
			logged = (boolean) request.getSession().getAttribute("logged");
		}
		if (logged) {
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
