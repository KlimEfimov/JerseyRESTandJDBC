package org.klimefimov.restservice.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Products_CategoriesDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7787992068534607L;  //!!!!

    private int id;
    private final int product_id;
    private final int category_id;

    public Products_CategoriesDTO(int id, int product_id, int category_id) {
        this.id = id;
        this.product_id = product_id;
        this.category_id = category_id;
    }

    public Products_CategoriesDTO(int product_id, int category_id) {
        this.id = -1;
        this.product_id = product_id;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products_CategoriesDTO that = (Products_CategoriesDTO) o;
        return id == that.id && product_id == that.product_id && category_id == that.category_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product_id, category_id);
    }

    @Override
    public String toString() {
        return "Products_CategoriesDTO{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", category_id=" + category_id +
                '}';
    }
}
