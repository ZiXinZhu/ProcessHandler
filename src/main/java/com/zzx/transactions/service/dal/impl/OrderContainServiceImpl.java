package com.zzx.transactions.service.dal.impl;


import com.zzx.transactions.dao.OrderDOMapper;
import com.zzx.transactions.entity.OrderDO;
import com.zzx.transactions.entity.dto.OrderDTO;
import com.zzx.transactions.service.dal.ContainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderContainServiceImpl implements ContainService<OrderDTO> {


    public OrderContainServiceImpl() {
        System.out.println("OrderContainServiceImpl≥ı ºªØ");
    }

    @SuppressWarnings("all")
    @Autowired
    private OrderDOMapper orderDOMapper;

    @Override
    public OrderDTO lock(int id) {
        return new OrderDTO().convertOrderDOToOrderDTO(orderDOMapper.lock(id));
    }

    @Override
    public int update(OrderDTO orderDTO) {
        return orderDOMapper.updateByPrimaryKeySelective(orderDTO.clone() == null ? new OrderDO() : orderDTO.clone().convertOrderDTOToOrderDO());
    }

    @Override
    public int insert(OrderDTO orderDTO) {
        return orderDOMapper.insertSelective(orderDTO.convertOrderDTOToOrderDO());
    }
}
