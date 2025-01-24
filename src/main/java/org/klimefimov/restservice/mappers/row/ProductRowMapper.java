package org.klimefimov.restservice.mappers.row;

import org.klimefimov.restservice.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public List<Product> mapRows(ResultSet resultSet) throws SQLException {
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt(1));
            product.setName(resultSet.getString(2));
            product.setPrice(resultSet.getBigDecimal(3));
            product.setQuantity(resultSet.getInt(4));
            product.setAvailable(resultSet.getBoolean(5));
//            JsonMapper<Category2DTO> mapper = new CategoryDTOJsonMapperImpl();
//            mapper.toDto();


//            CategoryDTO.getDTO((PGobject) resultSet.getObject(6));

//            PGobject pGobject = (PGobject) resultSet.getObject(6);
//            System.out.println(pGobject.getValue());
//            System.out.println(resultSet.getObject(6));
//            product.setProductCategories(Category2DTO.getDTO((PGobject) resultSet.getObject(6))); // FIX
            productList.add(product);
        }
        return productList;
    }

//    private void mapJSON(PGobject value) {
//        System.out.println("----------mapJSON----------");
//        String json = value.getValue();
//        json = Objects.requireNonNull(json).substring(1, json.length());
//        List<String> stringList = Arrays.stream(json.split(", ")).toList();
//
//        List<String> stringList2 = Arrays.stream(json.split(", "))
////                .map(s -> s.substring(1, s.length() - 1))
//                .flatMap(s -> Arrays.stream(s.split(" : ")))
//                .toList();
//        for (String s : stringList2) System.out.println(s);
//        System.out.println("----------mapJSON----------");
//    }

//    public void mapJSONStatic(String value) {
////        System.out.println(value);
//        Category category = new Category();
//        try {
//            JsonNode jsonNode = new ObjectMapper().readTree(value);
//            System.out.println(jsonNode);
////            System.out.println(jsonNode.get(0).asText());
//            jsonNode.elements().forEachRemaining(jsonNode1 -> System.out.println(jsonNode1.get("id").asInt()));
////            CategoryWithoutProductsDTO categoryDTO = jsonNode.
////            category = new ObjectMapper().readValue(value, Category.class);
////            System.out.println(category);
//        } catch (Exception e) {e.printStackTrace();}
////        category = new ObjectMapper().readValue(value, Category.class);
//    }

}
