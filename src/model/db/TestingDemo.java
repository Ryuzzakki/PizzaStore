package model.db;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;
import model.Restaurant;
import model.User;
import model.UserException;

public class TestingDemo {

	public static void main(String[] args) throws UserException, SQLException {
		Restaurant restaurant = null;
		try {
			restaurant = RestaurantDao.getInstance().getRestaurant(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(restaurant.getId());
		System.out.println(restaurant.getName());

		ArrayList<Product> products = ProductDao.getInstance().getAllProducts();
		System.out.println(products.size());
		System.out.println(products.get(3).getName());

		User user = new User("Testingas", "Idfasf", "zxcvbn2", "31231232132", "abfz@abv.abv");
		UserDao.getInstance().addUser(user);
		System.out.println(user.getId());

	}

}
