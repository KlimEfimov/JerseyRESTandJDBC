package org.klimefimov.restservice.mappers.row;

import org.klimefimov.restservice.dto.CustomerDTO;
import org.klimefimov.restservice.entities.Customer;
import org.klimefimov.restservice.entities.OrderDetail;
import org.klimefimov.restservice.mappers.json.CustomerDTOJsonMapper;
import org.klimefimov.restservice.mappers.json.JsonMapper;
import org.klimefimov.restservice.util.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailRowMapper implements RowMapper<OrderDetail> {

    @Override
    public List<OrderDetail> mapRows(ResultSet resultSet) throws SQLException {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        while (resultSet.next()) {
            JsonMapper<CustomerDTO> customerDTOJsonMapper = new CustomerDTOJsonMapper();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(resultSet.getInt(1));
            orderDetail.setOrderStatus(Enum.valueOf(OrderStatus.class, resultSet.getString(2)));
            orderDetail.setTotalAmount(resultSet.getBigDecimal(3));



            CustomerDTO customerDTO = customerDTOJsonMapper.toDto(resultSet.getObject(4));
            Customer customer = new Customer();
            customer.setId(customerDTO.getId());
            customer.setName(customerDTO.getName());
            orderDetail.setCustomer(customer);
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }
}
