package model;

import java.util.HashSet;

public class Product {

	private long id;
	private String name;
	private double price;
	private HashSet<Ingredient> ingredients = new HashSet<>();
	private String productPicture;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Product(long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HashSet<Ingredient> getIngredients() {
		return ingredients;
	}

	public long getId() {
		return id;
	}

	public void setIngredients(HashSet<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getProductPicture() {
		return productPicture;
	}

	public void setProductPicture(String productPicture) {
		this.productPicture = productPicture;
	}

}
