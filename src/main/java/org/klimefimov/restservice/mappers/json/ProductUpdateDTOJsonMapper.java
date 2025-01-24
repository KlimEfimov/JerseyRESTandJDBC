package org.klimefimov.restservice.mappers.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.klimefimov.restservice.dto.CategoryDTO;
import org.klimefimov.restservice.dto.ProductUpdateDTO;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductUpdateDTOJsonMapper implements JsonMapper<ProductUpdateDTO> {
    @Override
    public ProductUpdateDTO toDto(Object o) {
        try {
            JsonMapper<CategoryDTO> categoryMapper = new CategoryDTOJsonMapper();
            JsonNode productJson = new ObjectMapper().readTree((InputStream) o);
            int id = productJson.get("id").asInt();
            String name = productJson.get("name").asText();
            BigDecimal price = productJson.get("price").decimalValue();  //Extract to distinct method
            int quantity = productJson.get("quantity").asInt();
            boolean available = productJson.get("available").asBoolean();
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            productJson.get("productCategories").elements().forEachRemaining(node -> categoryDTOList.add(categoryMapper.toDto(node)));
            return new ProductUpdateDTO(id, name, price, quantity, available, categoryDTOList);

        } catch (IOException e) {e.printStackTrace();}
        return new ProductUpdateDTO(-1, null, null, 0, false, null);
    }
}


