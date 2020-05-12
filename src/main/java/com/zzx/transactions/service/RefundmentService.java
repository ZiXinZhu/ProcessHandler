package com.zzx.transactions.service;

import com.zzx.transactions.entity.RefundmentDO;

/**
 * @Description
 * @Author Alon
 * @Date 2020/5/12 21:08
 */
public interface RefundmentService {

    RefundmentDO process(RefundmentDO refundmentDO);
}
