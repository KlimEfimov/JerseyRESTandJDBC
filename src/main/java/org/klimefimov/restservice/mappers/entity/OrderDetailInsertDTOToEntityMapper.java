package org.klimefimov.restservice.mappers.entity;

import org.klimefimov.restservice.dto.OrderDetailInsertDTO;
import org.klimefimov.restservice.entities.Customer;
import org.klimefimov.restservice.entities.OrderDetail;
import org.klimefimov.restservice.util.OrderStatus;

public class OrderDetailInsertDTOToEntityMapper implements DTOToEntityMapper<OrderDetailInsertDTO, OrderDetail>{
    @Override
    public OrderDetail toEntity(OrderDetailInsertDTO orderDetailInsertDTO) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderStatus(Enum.valueOf(OrderStatus.class, orderDetailInsertDTO.getStatus()));
        orderDetail.setTotalAmount(orderDetailInsertDTO.getTotalAmount());
        Customer customer = new Customer();
        customer.setId(orderDetailInsertDTO.getCustomer().getId());
        customer.setName(orderDetailInsertDTO.getCustomer().getName());
        orderDetail.setCustomer(customer);
        return orderDetail;
    }
}
