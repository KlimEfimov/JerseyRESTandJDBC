package org.klimefimov.restservice.mappers.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.klimefimov.restservice.dto.CategoryDTO;
import org.klimefimov.restservice.dto.CustomerDTO;
import org.klimefimov.restservice.dto.OrderDetailInsertDTO;
import org.klimefimov.restservice.dto.ProductInsertDTO;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailInsertDTOJsonMapper implements JsonMapper<OrderDetailInsertDTO>{

    @Override
    public OrderDetailInsertDTO toDto(Object o) {
        try {
            JsonNode orderDetailJson = new ObjectMapper().readTree((InputStream) o);
            String status = orderDetailJson.get("status").asText();
            BigDecimal totalAmount = orderDetailJson.get("totalAmount").decimalValue();
            JsonNode customerJson = orderDetailJson.get("customer");
            int customerId = customerJson.get("id").asInt();
            String name = customerJson.get("name").asText();
            return new OrderDetailInsertDTO(status, new CustomerDTO(customerId, name), totalAmount);
        } catch (IOException e) {e.printStackTrace();}
        return new OrderDetailInsertDTO(null, null, null);
    }
}



