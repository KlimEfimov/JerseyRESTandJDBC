package org.klimefimov.restservice.dao.auxil;

import org.klimefimov.restservice.dto.Products_CategoriesDTO;
import org.klimefimov.restservice.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Products_CategoriesDAO implements AuxDAO<Products_CategoriesDTO> {
    private static final String INSERT = "INSERT INTO products_categories (product_id, category_id) VALUES (?, ?);";
    private static final String GET_MAX_ID = "SELECT count(product_id) FROM products_categories;";
    private static final String DELETE_BY_PRODUCT_ID = "DELETE FROM products_categories WHERE product_id = ?;";
    private static final String GET_BY_PRODUCT_ID = "SELECT * FROM products_categories WHERE product_id = ?;";

    @Override
    public int insert(Products_CategoriesDTO dto) {
        int result = -1;
        try(Connection connection = DBConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, dto.getProduct_id());
            statement.setInt(2, dto.getCategory_id());
            result = statement.executeUpdate();
            if (result > 0) return getMaxId(connection.createStatement());
        } catch (SQLException e) {e.printStackTrace();}
        return result;
    }

    @Override
    public int getMaxId(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(GET_MAX_ID);
        if (resultSet.next()) return resultSet.getInt(1);
        return -1;
    }

    @Override
    public int deleteByFirstId(int id) {
        int result = -1;
        try(Connection connection = DBConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_PRODUCT_ID)) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
//            System.out.println(result);
        } catch (SQLException e) {e.printStackTrace();}
        return result;
    }

    @Override
    public int deleteBySecondId(int id) {
        return 0;
    }

    @Override
    public List<Products_CategoriesDTO> getByFirstId(int id) {
        List<Products_CategoriesDTO> DTOList = new ArrayList<>();
        try(Connection connection = DBConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BY_PRODUCT_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DTOList.add(new Products_CategoriesDTO(resultSet.getInt(1), resultSet.getInt(2)));
            }
        } catch (SQLException e) {e.printStackTrace();}
        return DTOList;
    }
}
