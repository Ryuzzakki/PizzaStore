package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Ingredient;
import model.Product;

public class IngredientDao {
	private static IngredientDao instance;

	private IngredientDao() {
	}

	public static synchronized IngredientDao getInstance() {
		if (instance == null) {
			instance = new IngredientDao();
		}
		return instance;
	}

	public Ingredient getIngredient(long ingredientId) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("SELECT * FROM pizza_store.ingredients where id = ?");
		preparedStatement.setLong(1, ingredientId);
		Ingredient ingredient = null;
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			long id = set.getLong("id");
			String ingredientName = set.getString("name");
			double ingredientPrice = set.getDouble("price");
			ingredient = new Ingredient(id, ingredientName, ingredientPrice);

		}

		return ingredient;

	}

	public ArrayList<Ingredient> getAllIngredients() throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM pizza_store.ingredients");
		Ingredient ing = null;
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			long id = set.getLong("id");
			String productName = set.getString("name");
			double productPrice = set.getDouble("price");
			ing = new Ingredient(id, productName, productPrice);
			ingredients.add(ing);

		}
		return ingredients;

	}

}
