package model;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Order {

	private long id;
	private User user;
	private Restaurant restaurant;
	private double total_price;
	private LocalDateTime order_date;
	private HashMap<Product, Integer> products = new HashMap<>();

	public Order(User user, Restaurant restaurant, double total_price, LocalDateTime order_date,
			HashMap<Product, Integer> products) {
		this.user = user;
		this.restaurant = restaurant;
		this.total_price = total_price;
		this.order_date = order_date;
		this.products = products;
	}

	public Order(Restaurant restaurant, double total_price, LocalDateTime order_date,
			HashMap<Product, Integer> products) {
		this.restaurant = restaurant;
		this.total_price = total_price;
		this.order_date = order_date;
		this.products = products;
	}

	public Order(long id, User user, Restaurant restaurant, double total_price, LocalDateTime order_date) {
		this.id = id;
		this.user = user;
		this.restaurant = restaurant;
		this.total_price = total_price;
		this.order_date = order_date;

	}

	public long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public double getTotal_price() {
		return total_price;
	}

	public LocalDateTime getOrder_date() {
		return order_date;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HashMap<Product, Integer> getProducts() {
		return products;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
