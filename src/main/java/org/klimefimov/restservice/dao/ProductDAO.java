package org.klimefimov.restservice.dao;

import org.klimefimov.restservice.entities.Product;
import org.klimefimov.restservice.mappers.row.ProductRowMapper;
import org.klimefimov.restservice.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAO<Product>{

    private final String GET_MAX_ID = "SELECT max(id) FROM product";
    static private final String GET = "SELECT product.*, json_agg(json_build_object('id', category.id, 'name', category.name, 'type', category.type)) AS categories FROM product LEFT OUTER JOIN products_categories ON product.id = products_categories.product_id LEFT OUTER JOIN category ON products_categories.category_id = category.id WHERE product.id = ? GROUP BY product.id;";
    static private final String GET_ALL = "SELECT product.*, json_agg(json_build_object('id', category.id, 'name', category.name, 'type', category.type)) AS categories FROM product LEFT OUTER JOIN products_categories ON product.id = products_categories.product_id LEFT OUTER JOIN category ON products_categories.category_id = category.id GROUP BY product.id;";
    static private final String SAVE_TO_PRODUCT = "INSERT INTO product (name, price, quantity, available) VALUES (?, ?, ?, ?);";
    static private final String UPDATE = "UPDATE product SET name = ?, price = ?, quantity = ?, available = ? WHERE id = ?;";
    static private final String DELETE = "DELETE FROM product WHERE id = ?;";

    @Override
    public Product get(int id) {
        Product product = new Product();
        try (Connection connection = DBConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET)) {
            statement.setInt(1, id);
            ProductRowMapper productMapper = new ProductRowMapper();
            List<Product> productList = productMapper.mapRows(statement.executeQuery());
            if (!productList.isEmpty()) product = productList.get(0);
        } catch (SQLException e) {e.printStackTrace();}
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = DBConnector.getConnection().prepareStatement(GET_ALL)){
            ProductRowMapper productMapper = new ProductRowMapper();
            products = productMapper.mapRows(statement.executeQuery());
        } catch (SQLException e) {e.printStackTrace();}
        return products;
    }


    @Override
    public int insert(Product product) {
        int result = -1;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_TO_PRODUCT)){
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setBoolean(4, product.isAvailable());
            result = statement.executeUpdate();
            if (result > 0) result = getMaxId(connection.createStatement());
        } catch (SQLException e) {e.printStackTrace();}
        return result;
    }

    @Override
    public int update(Product product) {
        int result = -1;
        try(PreparedStatement statement = DBConnector.getConnection().prepareStatement(UPDATE)) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setBoolean(4, product.isAvailable());
            statement.setInt(5, product.getId());
            result = statement.executeUpdate();
            if (result > 0) result = product.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Product product) {
        int result = -1;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)){
            if (product.getId() <= getMaxId(connection.createStatement())) {
                statement.setInt(1, product.getId());
                result = statement.executeUpdate();
            }


        } catch (SQLException e) {e.printStackTrace();}
        return result;
    }

    private int getMaxId(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(GET_MAX_ID);
        if (resultSet.next()) return resultSet.getInt(1);
        return -1;
    }

}


