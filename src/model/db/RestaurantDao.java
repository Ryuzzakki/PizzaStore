package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Restaurant;

public class RestaurantDao {
	private static RestaurantDao instance;

	private RestaurantDao() {
	}

	public static synchronized RestaurantDao getInstance() {
		if (instance == null) {
			instance = new RestaurantDao();
		}
		return instance;
	}

	public Restaurant getRestaurant(long RestorantId) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("SELECT * FROM pizza_store.restaurants where id = ?");
		preparedStatement.setLong(1, RestorantId);

		Restaurant restaurant = null;
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			long id = set.getLong("id");
			String restName = set.getString("name");
			String restLocation = set.getString("location");
			String phone = set.getString("phone_number");
			restaurant = new Restaurant(id, restName, restLocation, phone);
		}
		if (restaurant != null) {
			return restaurant;
		}
		return restaurant;

	}
}