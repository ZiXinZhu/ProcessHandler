package com.zzx.transactions.service.share.impl;

import com.zzx.transactions.config.ServiceComponent;
import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.entity.OrderDO;
import com.zzx.transactions.service.dal.ContainService;
import com.zzx.transactions.service.share.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Description
 * @Author Alon
 * @Date 2020/5/12 20:59
 */

@Service
@SuppressWarnings("all")
public class OrderServerImpl implements OrderService {

    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    ServiceComponent component;

    @Override
    public OrderDO process(OrderDO orderDO) {
        return transactionTemplate.execute(new TransactionCallback<OrderDO>() {
            @Nullable
            @Override
            public OrderDO doInTransaction(TransactionStatus transactionStatus) {
                ContainService<BaseDO> containService = component.getServer(orderDO.getContainEnum().getCode());
                OrderDO order = (OrderDO) containService.lock(orderDO.getId());
                int res = containService.update(orderDO);
                return null != order && res == 1 ? order : null;
            }
        });
    }
}
