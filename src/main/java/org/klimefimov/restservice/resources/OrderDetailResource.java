package org.klimefimov.restservice.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.klimefimov.restservice.dto.*;
import org.klimefimov.restservice.mappers.json.*;
import org.klimefimov.restservice.services.OrderDetailService;
import org.klimefimov.restservice.services.ProductService;

import java.io.InputStream;
import java.util.List;

@Path("/order_detail")
public class OrderDetailResource {

    private final OrderDetailService orderDetailService;

    public OrderDetailResource() {
        orderDetailService = new OrderDetailService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public OrderDetailDTO get(@PathParam("id") int id) {
        return orderDetailService.getDTOById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDetailDTO> getAll() {
        return orderDetailService.getAllDTO();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetailDTO insert(InputStream inputStream) {
        JsonMapper<OrderDetailInsertDTO> dtoMapper = new OrderDetailInsertDTOJsonMapper();
        OrderDetailInsertDTO orderDetailInsertDTO = dtoMapper.toDto(inputStream);
        int result = orderDetailService.insert(orderDetailInsertDTO);
        if (result > 1) return orderDetailService.getDTOById(result);
        return new OrderDetailDTO(0, null, null, null);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetailDTO update(InputStream inputStream) {
        JsonMapper<OrderDetailDTO> dtoMapper = new OrderDetailDTOJsonMapper();
        OrderDetailDTO orderDetailDTO = dtoMapper.toDto(inputStream);
        int result = orderDetailService.update(orderDetailDTO);
        if (result < 1) return new OrderDetailDTO(0, null, null, null);
        return orderDetailService.getDTOById(orderDetailDTO.getId());
    }






}
