package org.klimefimov.restservice.util;

import org.klimefimov.restservice.dao.OrderDetailDAO;
import org.klimefimov.restservice.entities.OrderDetail;

public class Application {

    public static void main(String[] args) {

        System.out.println(Math.random());

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        OrderDetail orderDetail = orderDetailDAO.get(2);
        System.out.println(orderDetail);




//        ProductDAOImpl productDAO = new ProductDAOImpl();
//        ProductResource productResource = new ProductResource();
//        Product product = new Product();
//        product.setName("Product 4");
//        product.setPrice(new BigDecimal("4.4"));
//        product.setQuantity(4);
//        product.setAvailable(false);




//        System.out.println(productResource.insert(product) + "JJJJJ");
//        productResource.getById(1);
//        System.out.println(Math.random());
//        ProductDAOImpl productDAO = new ProductDAOImpl();
//        productDAO.insert(new Product());

//        rowMapper.mapJSONStatic("[{\"id\" : 1, \"name\" : \"Category1\", \"type\" : \"Type1\"}, {\"id\" : 3, \"name\" : \"Category3\", \"type\" : \"Type2\"}]");


    }

}
