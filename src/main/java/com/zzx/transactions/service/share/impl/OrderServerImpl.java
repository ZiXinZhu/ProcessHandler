package com.zzx.transactions.service.share.impl;

import com.zzx.transactions.config.ServiceComponent;
import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.entity.dto.OrderDTO;
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
    public OrderDTO process(OrderDTO orderDTO) {
        return transactionTemplate.execute(new TransactionCallback<OrderDTO>() {
            @Nullable
            @Override
            public OrderDTO doInTransaction(TransactionStatus transactionStatus) {
                ContainService<BaseDO> containService = component.getServer(orderDTO.getContainEnum().getCode());
                OrderDTO order = (OrderDTO) containService.lock(orderDTO.getId());
                int res = containService.update(orderDTO);
                return null != order && res == 1 ? order : null;
            }
        });
    }


}
