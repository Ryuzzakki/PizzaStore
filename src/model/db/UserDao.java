package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import model.Order;
import model.Product;
import model.User;
import model.UserException;

public class UserDao {
	private static UserDao instance;

	private UserDao() {
	}

	public static synchronized UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	public void addUser(User u) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(
				"insert into users (first_name,last_name,password,phone_number,email) values (?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

		preparedStatement.setString(1, u.getFirst_name());
		preparedStatement.setString(2, u.getLast_name());
		preparedStatement.setString(3, u.getPassword());
		preparedStatement.setString(4, u.getPhone_number());
		preparedStatement.setString(5, u.getEmail());
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		rs.next();
		u.setId(rs.getLong(1));

	}

	public boolean userExists(String email, String password) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("select * from users where email=? and password = ?");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		ResultSet set = preparedStatement.executeQuery();
		if (set.next()) {
			return true;
		}

		return false;
	}

	public void addAddressForUser(long user_id, String address) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(
				"INSERT INTO pizza_store.user_address (user_id, location) VALUES (?,?)",
				Statement.RETURN_GENERATED_KEYS);

		preparedStatement.setLong(1, user_id);
		preparedStatement.setString(2, address);
		preparedStatement.executeUpdate();

	}

	public void removeAddressForUser(String location) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM pizza_store.user_address WHERE location=?");

		preparedStatement.setString(1, location);
		preparedStatement.executeUpdate();

	}

	public String getAdrress(long id) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("SELECT * FROM pizza_store.user_address where id = ?");
		preparedStatement.setLong(1, id);
		String address = null;
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			address = set.getString("location");

		}
		return address;

	}

	public HashSet<String> getAllAdrress(long user_id) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("SELECT * FROM pizza_store.user_address where user_id = ?");
		preparedStatement.setLong(1, user_id);
		HashSet<String> address = new HashSet<>();
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			String location = set.getString("location");
			address.add(location);
		}
		return address;

	}

	public User getUser(long id) throws SQLException, UserException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM pizza_store.users where id = ?");
		preparedStatement.setLong(1, id);
		User user = null;
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			long userId = set.getLong("id");
			String firstName = set.getString("first_name");
			String lastName = set.getString("last_name");
			String password = set.getString("password");
			String phone = set.getString("phone_number");
			String email = set.getString("email");
			user = new User(firstName, lastName, password, phone, email);

		}
		return user;

	}

	public boolean userExistsByEmail(String email) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement("select * from users where email=?");
		preparedStatement.setString(1, email);

		ResultSet set = preparedStatement.executeQuery();
		if (set.next()) {
			return true;
		}

		return false;
	}

	public User getUserByEmail(String userEmail) throws SQLException, UserException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM pizza_store.users where email = ?");
		preparedStatement.setString(1, userEmail);
		User user = null;
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			long userId = set.getLong("id");
			String firstName = set.getString("first_name");
			String lastName = set.getString("last_name");
			String password = set.getString("password");
			String phone = set.getString("phone_number");
			String email = set.getString("email");
			String avatarUrl = set.getString("avatarUrl");
			user = new User(userId, firstName, lastName, password, phone, email);
			user.setAvatarUrl(avatarUrl);
			HashSet<Order> orders = OrderDao.getInstance().getAllOrders(user.getId());
			user.setOrders(orders);
			HashSet<String> address = UserDao.getInstance().getAllAdrress(userId);
			user.setAddresses(address);
		}
		return user;

	}

	public void insertAvatar(String userEmail, String url) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("UPDATE pizza_store.users SET avatarUrl=? WHERE email=?");
		preparedStatement.setString(1, url);
		preparedStatement.setString(2, userEmail);
		preparedStatement.executeUpdate();
	}
}
