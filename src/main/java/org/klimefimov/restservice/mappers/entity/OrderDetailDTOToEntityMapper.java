package org.klimefimov.restservice.mappers.entity;

import org.klimefimov.restservice.dto.OrderDetailDTO;
import org.klimefimov.restservice.entities.Customer;
import org.klimefimov.restservice.entities.OrderDetail;
import org.klimefimov.restservice.util.OrderStatus;

public class OrderDetailDTOToEntityMapper implements DTOToEntityMapper<OrderDetailDTO, OrderDetail>{

    @Override
    public OrderDetail toEntity(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailDTO.getId());
        orderDetail.setOrderStatus(Enum.valueOf(OrderStatus.class, orderDetailDTO.getStatus()));
        orderDetail.setTotalAmount(orderDetailDTO.getTotalAmount());
        Customer customer = new Customer();
        customer.setId(orderDetailDTO.getCustomer().getId());
        customer.setName(orderDetailDTO.getCustomer().getName());
        orderDetail.setCustomer(customer);
        return orderDetail;
    }
}
