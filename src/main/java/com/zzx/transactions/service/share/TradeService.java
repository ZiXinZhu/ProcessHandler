package com.zzx.transactions.service.share;

import com.zzx.transactions.entity.TradeDO;

public interface TradeService {

    /**
     * 回调业务处理
     *
     * @param tradeDO
     * @return
     */
    String process(TradeDO tradeDO);
}
