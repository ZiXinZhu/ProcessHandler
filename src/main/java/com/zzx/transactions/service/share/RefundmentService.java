package com.zzx.transactions.service.share;

import com.zzx.transactions.entity.RefundmentDO;

/**
 * @Description
 * @Author Alon
 * @Date 2020/5/12 21:08
 */
public interface RefundmentService {

    /**
     * 退款业务处理
     *
     * @param refundmentDO
     * @return
     */
    RefundmentDO process(RefundmentDO refundmentDO);
}
