package org.klimefimov.restservice.services;

import org.klimefimov.restservice.dao.DAO;
import org.klimefimov.restservice.dao.ProductDAO;
import org.klimefimov.restservice.dao.auxil.AuxDAO;
import org.klimefimov.restservice.dao.auxil.CategoryDAO;
import org.klimefimov.restservice.dao.auxil.Products_CategoriesDAO;
import org.klimefimov.restservice.dto.*;
import org.klimefimov.restservice.entities.Category;
import org.klimefimov.restservice.entities.Product;
import org.klimefimov.restservice.mappers.entity.*;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static final DAO<Product> productDAO = new ProductDAO();
    public ProductDTO getDTOById(int id) {
        EntityToDTOMapper<Product, ProductDTO> dtoMapper = new EntityToProductDTOMapper();
        AuxDAO<Products_CategoriesDTO> junctionDAO = new Products_CategoriesDAO();
        Product product = productDAO.get(id);
        addCategories(product, junctionDAO);
        return dtoMapper.toDTO(product);
    }

    public List<ProductDTO> getAllDTO() {
        EntityToDTOMapper<Product, ProductDTO> dtoMapper = new EntityToProductDTOMapper();
        List<Product> productList = productDAO.getAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        AuxDAO<Products_CategoriesDTO> junctionDAO = new Products_CategoriesDAO();
        for (Product product : productList) {
            addCategories(product, junctionDAO);
            productDTOList.add(dtoMapper.toDTO(product));
        }
        return productDTOList;
    }

    public int insert(ProductInsertDTO dto) {
        DTOToEntityMapper<ProductInsertDTO, Product> dtoMapper = new ProductInsertDTOToEntityMapper();
        Product product = dtoMapper.toEntity(dto);
        int result = productDAO.insert(product);
        if (result > 0) {
            AuxDAO<Products_CategoriesDTO> auxDAO = new Products_CategoriesDAO();
            List<Products_CategoriesDTO> dtoList = prepareJunctionDto(result, product);
            List<Integer> junctionInsertResult = new ArrayList<>();
            dtoList.forEach(dto1 -> junctionInsertResult.add(auxDAO.insert(dto1)));
            if (junctionInsertResult.stream().anyMatch(integer -> integer < 1)) result = -1;
        }
        return result;
    }

    public int update(ProductUpdateDTO dto) {
        DTOToEntityMapper<ProductUpdateDTO, Product> mapper = new ProductUpdateDTOToEntityMapper();
        Product product = mapper.toEntity(dto);
        int result = productDAO.update(product);
        if (result > 0) {
            AuxDAO<Products_CategoriesDTO> auxDAO = new Products_CategoriesDAO();
            result = auxDAO.deleteByFirstId(dto.getId());
            List<Products_CategoriesDTO> dtoList = prepareJunctionDto(dto.getId(), product);
            List<Integer> junctionInsertResult = new ArrayList<>();
            dtoList.forEach(dto1 -> junctionInsertResult.add(auxDAO.insert(dto1)));
            if (junctionInsertResult.stream().anyMatch(integer -> integer < 1)) result = -1;
        }
        return result;
    }

    public int delete(int id) {
        Product product = new Product();
        product.setId(id);
        AuxDAO<Products_CategoriesDTO> auxDAO = new Products_CategoriesDAO();
        auxDAO.deleteByFirstId(id);
        return productDAO.delete(product);
    }

    private List<Products_CategoriesDTO> prepareJunctionDto(int product_id, Product product) {
        List<CategoryDTO> list = product.getProductCategories();
        List<Products_CategoriesDTO> result = new ArrayList<>();
        for (CategoryDTO dto : list) {
            result.add(new Products_CategoriesDTO(product_id, dto.getId()));
        }
        return result;
    }

    private Product addCategories(Product product, AuxDAO<Products_CategoriesDTO> junctionDAO) {
        List<Products_CategoriesDTO> junctionList = junctionDAO.getByFirstId(product.getId());
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Products_CategoriesDTO dto : junctionList) {
            int category_id = dto.getCategory_id();
            Category category = CategoryDAO.getCategory(category_id);
            categoryDTOList.add(new CategoryDTO(category.getId(), category.getName(), category.getType().toString()));
        }
        product.setProductCategories(categoryDTOList);
        return product;
    }

}
