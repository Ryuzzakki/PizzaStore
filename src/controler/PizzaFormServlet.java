package controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.db.ProductDao;

/**
 * Servlet implementation class PizzaFormServlet
 */
@WebServlet("/chooser")
public class PizzaFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String currentId = request.getParameter("productId");
		String size = request.getParameter("size");
		String dough = request.getParameter("dough");
		try {
			Product product = ProductDao.getInstance().getProduct(Long.valueOf(currentId));
			if (request.getSession().getAttribute("modifiedProduct" + currentId) == null) {
				request.getSession().setAttribute("modifiedProduct" + currentId, product);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		Product product = (Product) request.getSession().getAttribute("modifiedProduct" + currentId);
		product.setSize(size);
		product.setDough(dough);
		response.sendRedirect("modify.jsp?productId=" + currentId);

	}

}
