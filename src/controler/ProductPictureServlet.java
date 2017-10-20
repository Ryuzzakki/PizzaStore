package controler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.db.ProductDao;

/**
 * Servlet implementation class ProductPictureServlet
 */
@WebServlet("/productPic")
public class ProductPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String PICTURE_URL = "D:/upload/products/";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int currProduct = Integer.parseInt(req.getParameter("currProductId"));
		String pictureUrl = null;

		try {
			pictureUrl = ProductDao.getInstance().getProduct(Long.valueOf(currProduct)).getProductPicture();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (pictureUrl == null || pictureUrl.isEmpty()) {
			pictureUrl = "defaultPizza.png";
		}
		File myFile = new File(PICTURE_URL + pictureUrl);
		OutputStream out = resp.getOutputStream();
		Path path = myFile.toPath();
		Files.copy(path, out);
		out.flush();

	}

}
