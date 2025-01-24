package org.klimefimov.restservice.dto;

import java.math.BigDecimal;

public class OrderDetailDTO {

    private final int id;
    private final String status;
    private final CustomerDTO customer;
    private final BigDecimal totalAmount;

    public OrderDetailDTO(int id, String status, CustomerDTO customer, BigDecimal totalAmount) {
        this.id = id;
        this.status = status;
        this.customer = customer;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
