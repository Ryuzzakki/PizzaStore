package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

public class Order {
	//
	private static long count = 20;
	//
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

	/**
	 * for creating empty order
	 * 
	 */
	public Order(User user, Restaurant restaurant) {
		this.user = user;
		this.restaurant = restaurant;
		this.total_price = 0;

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

	public void addToProducts(Product p) {
		p.setId(count++);
		if (products.containsKey(p)) {
			int quant = products.get(p);
			products.put(p, quant + 1);
		//  set random id
			
					
			
			products.put(p, 1);
			System.out.println("adding product" + p.getId());
		} else {
			this.products.put(p, 1);
		}
		this.total_price += p.getPrice();
	}

	public Product findProductInMap(long id) {
		System.out.println(products);
		for (Product p : products.keySet()) {
			if (p.getId() == id) {
				return p;
			}

		}
		return null;
	}

	public void removeProductFromOrder(long id, int quanitity) {
		for (Product p : products.keySet()) {
			if (p.getId() == id) {
				if (products.get(p) == quanitity) {
					products.remove(p);
					this.total_price -= p.getPrice() * quanitity;
					return;
				} else {
					products.put(p, products.get(p) - quanitity);
					this.total_price -= p.getPrice() * quanitity;
					return;
				}

			}
		}
	}

}
