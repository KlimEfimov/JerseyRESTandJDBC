package org.klimefimov.restservice.services;

import org.klimefimov.restservice.dao.DAO;
import org.klimefimov.restservice.dao.OrderDetailDAO;
import org.klimefimov.restservice.dao.auxil.AuxDAO;
import org.klimefimov.restservice.dao.auxil.Products_CategoriesDAO;
import org.klimefimov.restservice.dto.*;
import org.klimefimov.restservice.entities.OrderDetail;
import org.klimefimov.restservice.entities.Product;
import org.klimefimov.restservice.mappers.entity.*;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailService {

    private static final DAO<OrderDetail> orderDetailDAO = new OrderDetailDAO();

    public OrderDetailDTO getDTOById(int id) {
        EntityToDTOMapper<OrderDetail, OrderDetailDTO> mapper = new EntityToOrderDetailDTOMapper();
        OrderDetail orderDetail = orderDetailDAO.get(id);
        return mapper.toDTO(orderDetail);
    }

    public List<OrderDetailDTO> getAllDTO() {
        EntityToDTOMapper<OrderDetail, OrderDetailDTO> mapper = new EntityToOrderDetailDTOMapper();
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailDAO.getAll()) {
            orderDetailDTOList.add(mapper.toDTO(orderDetail));
        }
        return orderDetailDTOList;
    }

    public int insert(OrderDetailInsertDTO dto) {
        DTOToEntityMapper<OrderDetailInsertDTO, OrderDetail> dtoMapper = new OrderDetailInsertDTOToEntityMapper();
        OrderDetail orderDetail = dtoMapper.toEntity(dto);
        return orderDetailDAO.insert(orderDetail);
    }

    public int update(OrderDetailDTO dto) {
        DTOToEntityMapper<OrderDetailDTO, OrderDetail> mapper = new OrderDetailDTOToEntityMapper();
        OrderDetail orderDetail = mapper.toEntity(dto);
        return orderDetailDAO.update(orderDetail);
    }



}
