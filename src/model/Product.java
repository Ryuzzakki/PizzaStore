package model;

import java.util.HashSet;

public class Product {

	private long id;
	private String name;
	private double price;
	private HashSet<Ingredient> ingredients = new HashSet<>();
	private String productPicture;
	private boolean isPizza = false;
	private String size;
	private String dough;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Product(long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Product(long id, String name, double price, boolean isPizza, String size, String dough) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.isPizza = isPizza;
		this.size = size;
		this.dough = dough;
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

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);

	}

	public void removeIngredient(Ingredient ingredient) {
		if (this.ingredients.contains(ingredient)) {
			this.ingredients.remove(ingredient);
		}
	}

	public boolean getPizza() {
		return isPizza;
	}

	public String getDough() {
		return dough;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setDough(String dough) {
		this.dough = dough;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dough == null) ? 0 : dough.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productPicture == null) ? 0 : productPicture.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		if (dough == null) {
			if (other.dough != null)
				return false;
		} else if (!dough.equals(other.dough))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productPicture == null) {
			if (other.productPicture != null)
				return false;
		} else if (!productPicture.equals(other.productPicture))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

}
