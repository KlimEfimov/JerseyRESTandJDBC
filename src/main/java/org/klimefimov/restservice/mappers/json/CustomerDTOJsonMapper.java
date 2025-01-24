package org.klimefimov.restservice.mappers.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.klimefimov.restservice.dto.CustomerDTO;
import org.postgresql.util.PGobject;

public class CustomerDTOJsonMapper implements JsonMapper<CustomerDTO>{

    @Override
    public CustomerDTO toDto(Object o) {
        PGobject pGobject = (PGobject) o;
        ObjectMapper mapper = new ObjectMapper();
        CustomerDTO customerDTO = null;
        try {
            JsonNode jsonNode = mapper.readTree(pGobject.getValue());
            jsonNode = jsonNode.get(0);
            customerDTO = new CustomerDTO(jsonNode.get("id").asInt(), jsonNode.get("name").asText());
        } catch (JsonProcessingException e) {e.printStackTrace();}
        return customerDTO;
    }
}
