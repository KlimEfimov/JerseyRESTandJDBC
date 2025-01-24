package org.klimefimov.restservice.mappers.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.klimefimov.restservice.dto.CustomerDTO;
import org.klimefimov.restservice.dto.OrderDetailDTO;
import org.klimefimov.restservice.dto.OrderDetailInsertDTO;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public class OrderDetailDTOJsonMapper implements JsonMapper<OrderDetailDTO>{

    @Override
    public OrderDetailDTO toDto(Object o) {
        try {
            JsonNode orderDetailJson = new ObjectMapper().readTree((InputStream) o);
            int id = orderDetailJson.get("id").asInt();
            String status = orderDetailJson.get("status").asText();
            BigDecimal totalAmount = orderDetailJson.get("totalAmount").decimalValue();
            JsonNode customerJson = orderDetailJson.get("customer");
            int customerId = customerJson.get("id").asInt();
            String name = customerJson.get("name").asText();
            return new OrderDetailDTO(id, status, new CustomerDTO(customerId, name), totalAmount);
        } catch (IOException e) {e.printStackTrace();}
        return new OrderDetailDTO(0, null, null, null);
    }
}
