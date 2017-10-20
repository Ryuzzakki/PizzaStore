package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import model.User;
import model.UserException;
import model.db.OrderDao;
import model.db.ProductDao;

@WebServlet("/sortOrders")
public class OrderServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sort = request.getParameter("sort");
		Object o = request.getSession().getAttribute("user");
		if(o == null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		User u = (User) o;
		Set<Order> orders = (Set<Order>) request.getAttribute("orders");
		
		TreeSet<Order> set = new TreeSet<>(new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				if(sort.equals("asc")){
					return o1.getOrder_date().compareTo(o2.getOrder_date());
				}
				else{
					return o2.getOrder_date().compareTo(o1.getOrder_date());
				}
			}
		});
		set.addAll(orders);
		u.setOrders(set);
		request.getRequestDispatcher("orders.jsp").forward(request, response);
		System.out.println("tuk4");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object o = request.getSession().getAttribute("user");
		if(o == null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		User u = (User) o;
		long id = u.getId();
		
		try {
			HashSet<Order> orders = OrderDao.getInstance().getAllOrders(id);
			System.out.println("tuk1");
			request.getSession().setAttribute("orders", orders);
			System.out.println("tuk2");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("orders.jsp").forward(request, response);
		System.out.println("tuk3");
	}
}
