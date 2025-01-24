package org.klimefimov.restservice.mappers.entity;

import org.klimefimov.restservice.dto.CategoryDTO;
import org.klimefimov.restservice.dto.ProductDTO;
import org.klimefimov.restservice.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public class EntityToProductDTOMapper implements EntityToDTOMapper<Product, ProductDTO> {

    @Override
    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        int id = product.getId();
        String name = product.getName();
        BigDecimal price = product.getPrice();
        int quantity = product.getQuantity();
        boolean available = product.isAvailable();
        List<CategoryDTO> productCategories = product.getProductCategories(); // (ManyToMany)
        return new ProductDTO(id, name, price, quantity, available, productCategories);
    }


}
