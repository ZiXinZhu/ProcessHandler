package com.zzx.transactions.service.share.impl;

import com.zzx.transactions.config.ServiceComponent;
import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.entity.RefundmentDO;
import com.zzx.transactions.service.dal.ContainService;
import com.zzx.transactions.service.share.RefundmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Description
 * @Author Alon
 * @Date 2020/5/12 21:11
 */

@Service
@SuppressWarnings("all")
public class RefundmentServiceImpl implements RefundmentService {

    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    ServiceComponent component;

    @Override
    public RefundmentDO process(RefundmentDO refundmentDO) {
        return transactionTemplate.execute(new TransactionCallback<RefundmentDO>() {
            @Nullable
            @Override
            public RefundmentDO doInTransaction(TransactionStatus transactionStatus) {
                ContainService<BaseDO> containService = component.getServer(refundmentDO.getContainEnum().getCode());
                RefundmentDO result = (RefundmentDO) containService.lock(refundmentDO.getId());
                int res = containService.update(refundmentDO);
                return null != result && res == 1 ? result : null;
            }
        });
    }
}
