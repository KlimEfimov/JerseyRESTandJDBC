package org.klimefimov.restservice.mappers.entity;

import org.klimefimov.restservice.dto.CustomerDTO;
import org.klimefimov.restservice.dto.OrderDetailDTO;
import org.klimefimov.restservice.entities.Customer;
import org.klimefimov.restservice.entities.OrderDetail;

import java.math.BigDecimal;

public class EntityToOrderDetailDTOMapper implements EntityToDTOMapper<OrderDetail, OrderDetailDTO>{

    @Override
    public OrderDetailDTO toDTO(OrderDetail orderDetail) {
        int id = orderDetail.getId();
        String status = orderDetail.getOrderStatus().toString();
        Customer customer = orderDetail.getCustomer();
        BigDecimal totalAmount = orderDetail.getTotalAmount();
        return new OrderDetailDTO(id, status, new CustomerDTO(customer.getId(), customer.getName()), totalAmount);
    }
}
