package model.db;

import java.sql.SQLException;

import model.Restaurant;
import model.User;
import model.UserException;

public class TestingDemo {

	public static void main(String[] args) throws UserException {
		Restaurant restaurant = null;
		try {
			restaurant = RestaurantDao.getInstance().getRestaurant(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(restaurant.getId());
		System.out.println(restaurant.getName());

		User user = new User("ivo", "ivo", "safasf", "", "ivo@abv.bg");

	}

}
