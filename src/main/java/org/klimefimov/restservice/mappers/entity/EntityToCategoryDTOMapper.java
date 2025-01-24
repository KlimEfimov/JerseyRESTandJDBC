package org.klimefimov.restservice.mappers.entity;


import org.klimefimov.restservice.dto.CategoryDTO;
import org.klimefimov.restservice.entities.Category;

public class EntityToCategoryDTOMapper implements EntityToDTOMapper<Category, CategoryDTO>{

    @Override
    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getType().toString());
    }
}
