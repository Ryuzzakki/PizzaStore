package controler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.User;
import model.db.UserDao;

@WebServlet("/avatar")
@MultipartConfig
public class AvatarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String AVATAR_URL = "D:/upload/users/";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		Part avatarPart = request.getPart("avatar");
		InputStream fis = avatarPart.getInputStream();
		File myFile = new File(AVATAR_URL + user.getFirst_name() + ".jpg");
		if (!myFile.exists()) {
			myFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(myFile);
		int b = fis.read();
		while (b != -1) {
			fos.write(b);
			b = fis.read();
		}
		fis.close();
		fos.close();
		String avatarUrl = user.getFirst_name() + ".jpg";
		request.getSession().setAttribute("avatar", avatarUrl);
		try {
			UserDao.getInstance().insertAvatar(user.getEmail(), avatarUrl);
		} catch (SQLException e) {
			request.getRequestDispatcher("error.jsp").forward(request, resp);
			return;
		}
		request.getRequestDispatcher("main.jsp").forward(request, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute("user");

		String avatar = u.getAvatarUrl();

		if (avatar == null) {
			avatar = "default.jpeg";
		}

		File myFile = new File(AVATAR_URL + avatar);
		OutputStream out = resp.getOutputStream();
		Path path = myFile.toPath();
		Files.copy(path, out);
		out.flush();

	}

}
