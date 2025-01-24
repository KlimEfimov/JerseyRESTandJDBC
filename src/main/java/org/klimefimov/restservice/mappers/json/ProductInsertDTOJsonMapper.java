package org.klimefimov.restservice.mappers.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.klimefimov.restservice.dto.CategoryDTO;
import org.klimefimov.restservice.dto.ProductInsertDTO;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductInsertDTOJsonMapper implements JsonMapper<ProductInsertDTO> {

    private final JsonMapper<CategoryDTO> categoryMapper = new CategoryDTOJsonMapper();
    @Override
    public ProductInsertDTO toDto(Object o) {
        try {
            JsonNode productJson = new ObjectMapper().readTree((InputStream) o);
            String name = productJson.get("name").asText();
            BigDecimal price = productJson.get("price").decimalValue();
            int quantity = productJson.get("quantity").asInt();
            boolean available = productJson.get("available").asBoolean();
            List<CategoryDTO> categoryList = new ArrayList<>();
            productJson.get("productCategories").elements().forEachRemaining(node -> categoryList.add(categoryMapper.toDto(node)));
            return new ProductInsertDTO(name, price, quantity, available, categoryList);

        } catch (IOException e) {e.printStackTrace();}
        return new ProductInsertDTO(null, null, 0, false, null);
    }
}
