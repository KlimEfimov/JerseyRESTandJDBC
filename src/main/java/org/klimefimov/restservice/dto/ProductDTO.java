package org.klimefimov.restservice.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@EqualsAndHashCode
//@ToString
//@XmlRootElement //????
public class ProductDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 44589136152095854L;

    private final int id;
    private final String name;
    private final BigDecimal price;
    private final int quantity;
    private final boolean available;
    private final List<CategoryDTO> productCategories; // (ManyToMany)

    public ProductDTO(int id, String name, BigDecimal price, int quantity, boolean available, List<CategoryDTO> productCategories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return id == that.id && quantity == that.quantity && available == that.available && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(productCategories, that.productCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity, available, productCategories);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", available=" + available +
                ", productCategories=" + productCategories +
                '}';
    }
}
