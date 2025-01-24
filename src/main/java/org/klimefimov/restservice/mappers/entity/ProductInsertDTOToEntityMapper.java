package org.klimefimov.restservice.mappers.entity;

import org.klimefimov.restservice.dto.ProductInsertDTO;
import org.klimefimov.restservice.entities.Product;

public class ProductInsertDTOToEntityMapper implements DTOToEntityMapper<ProductInsertDTO, Product> {

    @Override
    public Product toEntity(ProductInsertDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setAvailable(dto.isAvailable());
        product.setProductCategories(dto.getProductCategories());
        return product;
    }
}
