package com.zzx.transactions.service.share.impl;

import com.zzx.transactions.entity.TradeDO;
import com.zzx.transactions.service.dal.TradeDalService;
import com.zzx.transactions.service.share.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@SuppressWarnings("all")
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private TradeDalService tradeDalService;

    @Override
    public String process(TradeDO trade) {
        return transactionTemplate.execute(transactionStatus -> {
            TradeDO tradeDO = tradeDalService.queryOne(trade.getId());
            int resultBank = tradeDalService.updateBank(trade.getBank(), trade.getId());
            int resultRemark = tradeDalService.updateRemark(trade.getRemark(), trade.getId());
            return String.format("%s-%s-%s", tradeDO, resultBank, resultRemark);
        });
    }
}
