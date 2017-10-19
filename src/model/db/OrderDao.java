package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import model.Order;
import model.Product;
import model.Restaurant;
import model.User;
import model.UserException;

public class OrderDao {
	private static OrderDao instance;

	private OrderDao() {
	}

	public static synchronized OrderDao getInstance() {
		if (instance == null) {
			instance = new OrderDao();
		}
		return instance;
	}

	// get user and restaurant from session
	public long createOrder(User u, Restaurant r) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(
				"insert into orders (user_id,restaurant_id, total_price, order_date) values(?,?,?,now())",
				Statement.RETURN_GENERATED_KEYS);

		preparedStatement.setLong(1, u.getId());
		preparedStatement.setLong(2, r.getId());
		preparedStatement.setDouble(3, 0);
		preparedStatement.executeUpdate();

		ResultSet rs = preparedStatement.getGeneratedKeys();
		rs.next();
		Long id = rs.getLong(1);
		u.setId(id);

		return id;

	}

	public void calculatePrice(long id) throws SQLException {
		double price = 0;
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("select product_id, quantity from order_details where order_id = ?");
		preparedStatement.setLong(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			long productId = resultSet.getLong("product_id");
			int productQuantity = resultSet.getInt("quantity");
			Product product = ProductDao.getInstance().getProduct(productId);
			price += product.getPrice() * productQuantity;
		}
		updateOrderPrice(price, id);

	}

	public void updateOrderPrice(double price, long orderId) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("UPDATE pizza_store.orders SET total_price =? WHERE id=?");
		preparedStatement.setDouble(1, price);
		preparedStatement.setDouble(2, orderId);
		preparedStatement.executeUpdate();

	}

	public HashSet<Order> getAllOrders(long user_id) throws SQLException, UserException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("SELECT * FROM pizza_store.orders where user_id = ?");
		preparedStatement.setLong(1, user_id);

		HashSet<Order> orders = new HashSet<>();
		ResultSet set = preparedStatement.executeQuery();

		while (set.next()) {
			long orderId = set.getLong("id");
			long userId = user_id;
			long restorantId = set.getLong("restaurant_id");
			double totalPrice = set.getDouble("total_price");
			LocalDateTime dateTime = set.getTimestamp("order_date").toLocalDateTime();

			Order order = new Order(UserDao.getInstance().getUser(userId),
					RestaurantDao.getInstance().getRestaurant(restorantId), totalPrice, dateTime,
					OrderDetailsDao.getInstance().getAllProductsFromOrder(orderId));
			orders.add(order);

		}
		return orders;

	}

	public Order getOrderById(long id) throws SQLException, UserException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM pizza_store.orders where id  =?");
		preparedStatement.setLong(1, id);

		ResultSet set = preparedStatement.executeQuery();
		Order order = null;
		while (set.next()) {
			long orderId = set.getLong("id");
			long userId = set.getLong("user_id");
			long restorantId = set.getLong("restaurant_id");
			double totalPrice = set.getDouble("total_price");
			LocalDateTime dateTime = set.getTimestamp("order_date").toLocalDateTime();
			Order order = new Order(userId, UserDao.getInstance().getUser(userId),
					RestaurantDao.getInstance().getRestaurant(restorantId), totalPrice, dateTime);

		}
		return order;

	}

}
