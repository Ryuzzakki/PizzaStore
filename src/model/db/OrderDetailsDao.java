package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import model.Order;
import model.Product;

public class OrderDetailsDao {
	// koshnica
	private static OrderDetailsDao instance;

	private OrderDetailsDao() {
	}

	public static synchronized OrderDetailsDao getInstance() {
		if (instance == null) {
			instance = new OrderDetailsDao();
		}
		return instance;
	}

	public void addProductToOrderDetails(Product product, Order order, int quantity) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(
				"INSERT INTO pizza_store.order_details (order_id, product_id, quantity) "
				+ "VALUES(?, (SELECT id FROM pizza_store.products WHERE name = ?),? );",
				Statement.RETURN_GENERATED_KEYS);

		preparedStatement.setLong(1, order.getId());
		preparedStatement.setString(2, product.getName());
		preparedStatement.setLong(3, quantity);
		preparedStatement.executeUpdate();

	}

	public void removeProductFromOrderDetails(Product product) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("DELETE FROM pizza_store.order_details WHERE product_id=?");
		preparedStatement.setLong(1, product.getId());
		preparedStatement.executeUpdate();
	}

	public HashMap<Product, Integer> getAllProductsFromOrder(long order_id) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("SELECT * FROM pizza_store.order_details where order_id = ?");
		preparedStatement.setLong(1, order_id);
		HashMap<Product, Integer> products = new HashMap<>();
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			long product_id = set.getLong("product_id");
			int productQuantity = set.getInt("quantity");
			Product product = ProductDao.getInstance().getProduct(product_id);
			if (products.containsKey(product)) {
				int quant = products.get(product);
				products.put(product, quant + productQuantity);
			} else {
				products.put(product, productQuantity);

			}
		}
		return products;
	}

}
