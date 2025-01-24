package org.klimefimov.restservice.entities;


import org.klimefimov.restservice.dto.CategoryDTO;

import java.math.BigDecimal;
import java.util.List;


public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private boolean available;
    private List<CategoryDTO> productCategories; // (ManyToMany)

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setProductCategories(List<CategoryDTO> productCategories) {
        this.productCategories = productCategories;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public List<CategoryDTO> getProductCategories() {
        return productCategories;
    }
}
