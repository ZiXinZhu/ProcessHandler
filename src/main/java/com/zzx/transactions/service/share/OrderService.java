package com.zzx.transactions.service.share;

import com.zzx.transactions.entity.dto.OrderDTO;

public interface OrderService {

    /**
     * 下单业务处理
     *
     * @param orderDO
     * @return
     */
    OrderDTO process(OrderDTO orderDO);
}
