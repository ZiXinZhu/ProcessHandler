package com.zzx.transactions.service.share;

import com.zzx.transactions.entity.OrderDO;

public interface OrderService {

    /**
     * 下单业务处理
     *
     * @param orderDO
     * @return
     */
    OrderDO process(OrderDO orderDO);
}
