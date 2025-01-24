package org.klimefimov.restservice.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.klimefimov.restservice.dto.ProductDTO;
import org.klimefimov.restservice.dto.ProductInsertDTO;
import org.klimefimov.restservice.dto.ProductUpdateDTO;
import org.klimefimov.restservice.mappers.json.*;
import org.klimefimov.restservice.services.ProductService;
import java.io.InputStream;
import java.util.List;

@Path("/product")
public class ProductResource {

    private final ProductService productService;

    public ProductResource() {
        productService = new ProductService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> getAll() {
        return productService.getAllDTO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public ProductDTO get(@PathParam("id") int id) {
        return productService.getDTOById(id);
    }

    // FIX return type
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO insert(InputStream inputStream) {
        JsonMapper<ProductInsertDTO> dtoMapper = new ProductInsertDTOJsonMapper();
        ProductInsertDTO productDTO = dtoMapper.toDto(inputStream);
        int result = productService.insert(productDTO);
        if (result > 1) return productService.getDTOById(result);
        return new ProductDTO(0, null, null, 0, false, null);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO update(InputStream inputStream) {
        JsonMapper<ProductUpdateDTO> dtoMapper = new ProductUpdateDTOJsonMapper();
        ProductUpdateDTO dto = dtoMapper.toDto(inputStream);
        int result = productService.update(dto);
        if (result < 1) return new ProductDTO(0, null, null, 0, false, null);
        return productService.getDTOById(dto.getId());
    }

    @DELETE
    @Path("/{id}")
    public int delete(@PathParam("id") int id) {
        return productService.delete(id);
    }

}