package com.zzx.transactions.service.dal;

import com.zzx.transactions.entity.TradeDO;

public interface TradeDalService {

    int insertOne(TradeDO tradeDO);

    int updateBank(String bank, int id);

    TradeDO queryOne(int id);

    int updateRemark(String remark, int id);
}
