package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import model.Ingredient;
import model.Product;

public class RecipeDao {
	private static RecipeDao instance;

	private RecipeDao() {
	}

	public static synchronized RecipeDao getInstance() {
		if (instance == null) {
			instance = new RecipeDao();
		}
		return instance;
	}

	public HashSet<Ingredient> getAllIngredientsFromRecipe(long product_id) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("SELECT * FROM pizza_store.recipe where product_id = ?");
		preparedStatement.setLong(1, product_id);
		HashSet<Ingredient> ingredients = new HashSet<>();
		ResultSet set = preparedStatement.executeQuery();
		while (set.next()) {
			long ingredientID = set.getLong("ingredient_id");
			Ingredient ingredient = IngredientDao.getInstance().getIngredient(ingredientID);
			ingredients.add(ingredient);
		}
		return ingredients;

	}

	public void addIngredientToRecipe(long ingredient_id, long product_id) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("INSERT INTO pizza_store.recipe (product_id, ingredient_id) VALUES (?, ?)");

		preparedStatement.setLong(1, product_id);
		preparedStatement.setLong(2, ingredient_id);
		preparedStatement.executeUpdate();
	}

	public void removeIngredientFromRecipe(Ingredient ingredient) throws SQLException {
		Connection con = DBManager.getInstance().getConnection();
		PreparedStatement preparedStatement = con
				.prepareStatement("DELETE FROM pizza_store.recipe WHERE ingredient_id=?");
		preparedStatement.setLong(1, ingredient.getId());
		preparedStatement.executeUpdate();
	}

}
