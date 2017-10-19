package controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import model.Product;
import model.Restaurant;
import model.User;
import model.db.OrderDao;
import model.db.OrderDetailsDao;
import model.db.ProductDao;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/addtocart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User currUser = (User) request.getSession().getAttribute("user");
		Restaurant currRestaurant = (Restaurant) request.getSession().getAttribute("restaurant");
		Order order = null;
		if (request.getAttribute("order") == null) {
			long orderId = OrderDao.getInstance().createOrder(currUser, currRestaurant);
		} else {
			
		}

		String id = request.getParameter("productId");
		try {
			Product p = ProductDao.getInstance().getProduct(Long.parseLong(id));
			OrderDetailsDao.getInstance().addProductToOrderDetails(p, order, 1);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

}
