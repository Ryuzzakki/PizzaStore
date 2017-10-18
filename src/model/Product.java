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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

}
