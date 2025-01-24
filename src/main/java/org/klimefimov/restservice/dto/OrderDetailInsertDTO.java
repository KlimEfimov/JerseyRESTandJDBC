package org.klimefimov.restservice.dto;

import java.math.BigDecimal;

public class OrderDetailInsertDTO {

    //Serialize

    private final String status;
    private final CustomerDTO customer;
    private final BigDecimal totalAmount;

    public OrderDetailInsertDTO(String status, CustomerDTO customer, BigDecimal totalAmount) {
        this.status = status;
        this.customer = customer;
        this.totalAmount = totalAmount;
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
