package org.klimefimov.restservice.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ProductInsertDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 743950473103333L;

    private final String name;
    private final BigDecimal price;
    private final int quantity;
    private final boolean available;
    private final List<CategoryDTO> productCategories; // (ManyToMany)

    public ProductInsertDTO(String name, BigDecimal price, int quantity, boolean available, List<CategoryDTO> productCategories) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
        this.productCategories = productCategories;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInsertDTO that = (ProductInsertDTO) o;
        return quantity == that.quantity && available == that.available && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(productCategories, that.productCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, available, productCategories);
    }

    @Override
    public String toString() {
        return "ProductSaveDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", available=" + available +
                ", productCategories=" + productCategories +
                '}';
    }



}
