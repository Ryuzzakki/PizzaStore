package controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.db.UserDao;


@WebServlet("/addressprofile")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newAddress = request.getParameter("address");
		User u = (User) request.getSession().getAttribute("user");
		try {
			UserDao.getInstance().addAddressForUser(u.getId(), newAddress);
			u.getAddress().add(newAddress);
			request.getRequestDispatcher("profileaddress.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String removeAddress = req.getParameter("address");
		User u = (User) req.getSession().getAttribute("user");
		HashSet<String> addresses = new HashSet<>();
		try {
			//if UserDao.getInstance().getAllAdrress(u.getId()).isEmpty()?
			addresses.addAll(UserDao.getInstance().getAllAdrress(u.getId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(addresses.contains(removeAddress)) {
			u.getAddress().remove(removeAddress);
			try {
				UserDao.getInstance().removeAddressForUser(removeAddress);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		req.getRequestDispatcher("profileaddress.jsp").forward(req, resp);
	}

}
