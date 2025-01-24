package org.klimefimov.restservice.mappers.entity;

import org.klimefimov.restservice.dto.ProductUpdateDTO;
import org.klimefimov.restservice.entities.Product;

public class ProductUpdateDTOToEntityMapper implements DTOToEntityMapper<ProductUpdateDTO, Product> {
    @Override
    public Product toEntity(ProductUpdateDTO productUpdateDTO) {
        Product product = new Product();
        product.setId(productUpdateDTO.getId());
        product.setName(productUpdateDTO.getName());
        product.setPrice(productUpdateDTO.getPrice());
        product.setQuantity(productUpdateDTO.getQuantity());
        product.setAvailable(productUpdateDTO.isAvailable());
        product.setProductCategories(productUpdateDTO.getProductCategories());
        return product;
    }

}

