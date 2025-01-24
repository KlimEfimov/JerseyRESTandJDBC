package org.klimefimov.restservice.mappers.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.klimefimov.restservice.dto.CategoryDTO;

public class CategoryDTOJsonMapper implements JsonMapper<CategoryDTO> {

    @Override
    public CategoryDTO toDto(Object o) {
        JsonNode node = (JsonNode) o;
        int catId = node.get("id").asInt();
        String catName = node.get("name").asText();
        String catType = node.get("type").asText();
        return new CategoryDTO(catId, catName, catType);
    }
}
