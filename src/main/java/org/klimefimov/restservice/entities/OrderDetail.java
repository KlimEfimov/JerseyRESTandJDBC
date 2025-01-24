package org.klimefimov.restservice.entities;

import org.klimefimov.restservice.util.OrderStatus;

import java.math.BigDecimal;

public class OrderDetail {
    private int id;
    private OrderStatus orderStatus;
    private Customer customer;
    private BigDecimal totalAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", customer=" + customer +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
