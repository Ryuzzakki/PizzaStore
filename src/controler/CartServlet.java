package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import model.Product;
import model.Restaurant;
import model.User;
import model.UserException;
import model.db.OrderDao;
import model.db.OrderDetailsDao;
import model.db.ProductDao;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("invoked 1");
		User currUser = (User) request.getSession().getAttribute("user");
		Restaurant currRestaurant = (Restaurant) request.getSession().getAttribute("restaurant");

		if (request.getSession().getAttribute("order") == null) {
			try {
				System.out.println("invoked2 ");
				long orderId = OrderDao.getInstance().createOrder(currUser, currRestaurant);
				Order order = OrderDao.getInstance().getOrderById(orderId);
				request.getSession().setAttribute("order", order);
				System.out.println("hi");
			} catch (UserException | SQLException e) {
				e.printStackTrace();
			}
		}
		String id = request.getParameter("productId");
		System.out.println("invoked 3");
		try {
			Product p = ProductDao.getInstance().getProduct(Long.parseLong(id));
			Order order = (Order) request.getSession().getAttribute("order");
			OrderDetailsDao.getInstance().addProductToOrderDetails(p, order, 1);
			System.out.println("invoked 4");
			OrderDao.getInstance().updateOrderPrice(p.getPrice(), order.getId());
			// probably transaction
			// fix restaurant setting
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("main.jsp");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("order") == null) {
			req.getRequestDispatcher("main.jsp").forward(req, resp);
			return;
		}
		Order order = (Order) req.getSession().getAttribute("order");
		long orderId = order.getId();
		HashMap<Product, Integer> map = new HashMap<>();
		try {
			map = OrderDetailsDao.getInstance().getAllProductsFromOrder(orderId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("productsInCart", map);
		req.getRequestDispatcher("mycart.jsp").forward(req, resp);
	}
}
