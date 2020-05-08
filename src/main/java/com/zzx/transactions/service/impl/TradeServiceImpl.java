package com.zzx.transactions.service.impl;

import com.zzx.transactions.dao.TradeDao;
import com.zzx.transactions.entity.TradeDO;
import com.zzx.transactions.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@SuppressWarnings("all")
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private TradeDao tradeDao;

    @Override
    public String process(TradeDO trade) {
        return transactionTemplate.execute(transactionStatus -> {
            TradeDO tradeDO = tradeDao.queryOne(trade.getId());
            int resultBank = tradeDao.updateBank(trade.getBank(), trade.getId());
            int resultRemark = tradeDao.updateRemark(trade.getRemark(), trade.getId());
            return String.format("%s-%s-%s", tradeDO, resultBank, resultRemark);
        });
    }
}
