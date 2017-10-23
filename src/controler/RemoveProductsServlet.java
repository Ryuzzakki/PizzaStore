package controler;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ingredient;
import model.Order;
import model.Product;
import model.db.OrderDao;

/**
 * Servlet implementation class RemoveProductsServlet
 */
@WebServlet("/remove")
public class RemoveProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String valueToBeRemoved = request.getParameter("productValue");
		
		if (valueToBeRemoved.isEmpty() || valueToBeRemoved == null) {
			request.getRequestDispatcher("mycart.jsp").forward(request, response);
			return;
		}
		Order order = (Order) request.getSession().getAttribute("order");
		
		order.removeProductFromOrder(Long.valueOf(productId), Integer.valueOf(valueToBeRemoved));
		double totalPrice = order.getTotal_price();


		request.getSession().setAttribute("totalPrice", totalPrice);
		request.getRequestDispatcher("mycart.jsp").forward(request, response);

	}

}
