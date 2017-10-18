package controler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.User;
import model.db.UserDao;

@WebServlet("/avatar")
public class AvatarServlet extends HttpServlet {

	public static final String AVATAR_URL = "D:/upload/users/";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String email = user.getEmail();
		
		Part avatarPart = request.getPart("avatar");
		InputStream fis = avatarPart.getInputStream();
		File myFile = new File(AVATAR_URL + email + ".jpg");
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
		String avatarUrl = email + ".jpg";
		request.getSession().setAttribute("avatar", avatarUrl);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("user");
		String avatar = u.getAvatarUrl();
		if (avatar == null) {
			avatar = "default.jpg";
		}
		File myFile = new File(AvatarServlet.AVATAR_URL + avatar);

		try (OutputStream out = response.getOutputStream()) {
			Path path = myFile.toPath();
			Files.copy(path, out);
			out.flush();
		} catch (IOException e) {
			System.out.println("ops");
		}
	}

}
