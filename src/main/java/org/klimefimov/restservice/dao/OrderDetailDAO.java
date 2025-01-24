package org.klimefimov.restservice.dao;

import org.klimefimov.restservice.entities.OrderDetail;
import org.klimefimov.restservice.mappers.row.OrderDetailRowMapper;
import org.klimefimov.restservice.mappers.row.RowMapper;
import org.klimefimov.restservice.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO implements DAO<OrderDetail> {

    private final String GET_MAX_ID = "SELECT max(id) FROM order_detail";
    static private final String GET = "SELECT order_detail.id, order_detail.order_status, order_detail.total_amount, json_agg(json_build_object('id', customer.id, 'name', customer.name)) as customer FROM order_detail LEFT JOIN customer ON order_detail.customer_id = customer.id WHERE order_detail.id = ? GROUP BY order_detail.id;";
    static private final String GET_ALL = "SELECT order_detail.id, order_detail.order_status, order_detail.total_amount, json_agg(json_build_object('id', customer.id, 'name', customer.name)) as customer FROM order_detail LEFT JOIN customer ON order_detail.customer_id = customer.id GROUP BY order_detail.id;";
    static private final String INSERT = "INSERT INTO order_detail (order_status, customer_id, total_amount) VALUES (?, ?, ?);";
    static private final String UPDATE = "UPDATE order_detail SET order_status = ?, customer_id = ?, total_amount = ? WHERE id = ?;";
    static private final String DELETE = "DELETE FROM product WHERE id = ?;";
    static private final String TEST = "SELECT * FROM order_detail WHERE id = ?;";



    @Override
    public OrderDetail get(int id) {
        OrderDetail orderDetail = new OrderDetail();
        RowMapper<OrderDetail> rowMapper = new OrderDetailRowMapper();
        try(Connection connection = DBConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET)) {
//            PreparedStatement statement = connection.prepareStatement(TEST)) {
            statement.setInt(1, id);
            List<OrderDetail> orderDetailList = rowMapper.mapRows(statement.executeQuery());
            if (!orderDetailList.isEmpty()) orderDetail = orderDetailList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    @Override
    public List<OrderDetail> getAll() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        RowMapper<OrderDetail> rowMapper = new OrderDetailRowMapper();
        try(Connection connection = DBConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            orderDetailList = rowMapper.mapRows(statement.executeQuery());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetailList;
    }

    @Override
    public int insert(OrderDetail orderDetail) {
        int result = -1;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)){
            statement.setString(1, orderDetail.getOrderStatus().toString());
            statement.setInt(2, orderDetail.getCustomer().getId());
            statement.setBigDecimal(3, orderDetail.getTotalAmount());
            result = statement.executeUpdate();
            if (result > 0) result = getMaxId(connection.createStatement());
        } catch (SQLException e) {e.printStackTrace();}
        return result;

    }

    @Override
    public int update(OrderDetail orderDetail) {
        int result = -1;
        try(PreparedStatement statement = DBConnector.getConnection().prepareStatement(UPDATE)) {

            statement.setString(1, orderDetail.getOrderStatus().toString());
            statement.setInt(2, orderDetail.getCustomer().getId());
            statement.setBigDecimal(3, orderDetail.getTotalAmount());
            statement.setInt(4, orderDetail.getId());
            result = statement.executeUpdate();
            if (result > 0) result = orderDetail.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public int delete(OrderDetail orderDetail) {
        return 0;
    }

    private int getMaxId(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(GET_MAX_ID);
        if (resultSet.next()) return resultSet.getInt(1);
        return -1;
    }



}
