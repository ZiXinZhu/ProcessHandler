package com.zzx.transactions.service;

import com.zzx.transactions.entity.OrderDO;

public interface OrderService {

    OrderDO process(OrderDO orderDO);
}
