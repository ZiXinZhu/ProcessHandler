package com.zzx.transactions.service.impl;


import com.zzx.transactions.dao.OrderDOMapper;
import com.zzx.transactions.entity.OrderDO;
import com.zzx.transactions.service.ContainService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderContainServiceImpl implements ContainService<OrderDO> {


    public OrderContainServiceImpl() {
        System.out.println("OrderContainServiceImpl初始化");
    }

    @SuppressWarnings("all")
    @Autowired
    private OrderDOMapper orderDOMapper;

    @Override
    public OrderDO lock(int id) {
        return orderDOMapper.lock(id);
    }

    @Override
    public int update(OrderDO orderDO) {
        return orderDOMapper.updateByPrimaryKeySelective(orderDO);
    }

    @Override
    public int insert(OrderDO orderDO) {
        return orderDOMapper.insertSelective(orderDO);
    }
}
