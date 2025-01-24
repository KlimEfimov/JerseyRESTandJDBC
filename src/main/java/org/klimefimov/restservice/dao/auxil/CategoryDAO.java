package org.klimefimov.restservice.dao.auxil;

import org.klimefimov.restservice.entities.Category;
import org.klimefimov.restservice.util.CategoryType;
import org.klimefimov.restservice.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private static final List<Category> categories = new ArrayList<>();
    private static final String INSERT = "INSERT INTO category (name, type) VALUES (?, ?);";
    private static final String GET = "SELECT * FROM category";

    public int insert(Category category) {
        try(Connection connection = DBConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getType().toString());
            return statement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
        return 0;
    }

    private static void loadCategories() {
        try(Connection connection = DBConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                category.setType(Enum.valueOf(CategoryType.class, resultSet.getString(3)));
                categories.add(category);
            }
        } catch (SQLException e) {e.printStackTrace();}
    }

    public static Category getCategory(int id) {
        if (categories.isEmpty()) loadCategories();
        return categories.get(id - 1);
    }


}
