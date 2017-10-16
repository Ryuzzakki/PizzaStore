package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import model.Ingredient;
import model.Order;
import model.Product;
import model.User;

public class ProductDao {
	private static ProductDao instance;

	private ProductDao() {
	}

	public static synchronized ProductDao getInstance() {
		if (instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}

	public Product getProduct(long proId) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM pizza_store.products where id = ?");
		preparedStatement.setLong(1, proId);
		Product product = null;
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			long id = set.getLong("id");
			String productName = set.getString("name");
			double productPrice = set.getDouble("price");
			product = new Product(id, productName, productPrice);

		}
		if (product != null) {
			return product;
		}
		return product;

	}

	public long createEmptyProduct() throws SQLException {

		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(
				"INSERT INTO pizza_store.products (name, price) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, "Custom");
		preparedStatement.setDouble(2, 0);
		preparedStatement.executeUpdate();
		ResultSet rs = preparedStatement.getGeneratedKeys();
		rs.next();
		return rs.getLong(1);

	}

	public void calculatePrice(long productId) throws SQLException {
		double price = 0;
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(
				"SELECT sum(price) as price FROM pizza_store.recipe join ingredients on ingredient_id = ingredients.id where product_id = ?");
		preparedStatement.setLong(1, productId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			price = resultSet.getDouble("price");
		}
		updateProductPrice(price, productId);

	}

	public void updateProductPrice(double price, long productId) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("UPDATE pizza_store.products SET price =? WHERE id=?");
		preparedStatement.setDouble(1, price);
		preparedStatement.setDouble(2, productId);
		preparedStatement.executeUpdate();

	}

	public ArrayList<Product> getAllProducts() throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM pizza_store.products");
		Product product = null;
		ArrayList<Product> products = new ArrayList<>();
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			long id = set.getLong("id");
			String productName = set.getString("name");
			double productPrice = set.getDouble("price");
			product = new Product(id, productName, productPrice);
			products.add(product);

		}

		return products;

	}

}
