package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.sun.prism.Image;

import model.Ingredient;
import model.Order;
import model.Product;
import model.Restaurant;
import model.User;
import model.db.IngredientDao;
import model.db.OrderDao;
import model.db.OrderDetailsDao;
import model.db.ProductDao;
import model.db.RecipeDao;

@WebServlet("/sortOrders")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sort = request.getParameter("sort");
		Object o = request.getSession().getAttribute("user");
		if (o == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		User u = (User) o;

		TreeSet<Order> set = new TreeSet<>(new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				if (sort.equals("asc")) {
					return o1.getOrder_date().compareTo(o2.getOrder_date());
				} else {
					return o2.getOrder_date().compareTo(o1.getOrder_date());
				}
			}
		});
		set.addAll(u.getOrders());
		u.setOrders(set);
		request.getRequestDispatcher("orders.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Order order = (Order) req.getSession().getAttribute("order");
		User u = (User) req.getSession().getAttribute("user");
		Restaurant r = (Restaurant) req.getSession().getAttribute("restaurant");
		HashMap<Product, Integer> map = order.getProducts();

		try {
			OrderDao.getInstance().createOrder(u, r);
			System.out.println("Adding order");
			for (Product p : map.keySet()) {
				System.out.println("Adding product");
				OrderDetailsDao.getInstance().addProductToOrderDetails(p, order, map.get(p));
				for (Ingredient ing : p.getIngredients()) {
					System.out.println("Adding ingredient");
					RecipeDao.getInstance().addIngredientToRecipe(ing.getId(), p.getId());
				}
			}
		} catch (SQLException e) {
			e.getMessage();
		}
	}
}
