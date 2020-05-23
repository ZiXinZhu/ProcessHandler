package com.zzx.transactions.service.dal.impl;

import com.zzx.transactions.dao.TradeDao;
import com.zzx.transactions.entity.TradeDO;
import com.zzx.transactions.service.dal.TradeDalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TradeDalServiceImpl implements TradeDalService {

    @Autowired
    private TradeDao dao;

    @Override
    public int insertOne(TradeDO tradeDO) {
        return dao.insertOne(tradeDO);
    }

    @Override
    public int updateBank(String bank, int id) {
        return dao.updateBank(bank, id);
    }

    @Override
    public TradeDO queryOne(int id) {
        return dao.queryOne(id);
    }

    @Override
    public int updateRemark(String remark, int id) {
        return dao.updateRemark(remark, id);
    }


}
