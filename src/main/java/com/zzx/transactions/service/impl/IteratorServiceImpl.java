package com.zzx.transactions.service.impl;

import com.zzx.transactions.dao.TradeDao;
import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.entity.TradeDO;
import com.zzx.transactions.service.IteratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

@Service
public class IteratorServiceImpl implements IteratorService {

    @SuppressWarnings("all")
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private TradeDao tradeDao;
    @Override
    public void mapIterator(Map<String, ? extends BaseDO> map) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @NonNull
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                map.forEach((k,v)->{
                    if("other".equals(k)){
                        tradeDao.insertOne((TradeDO) v);
                        tradeDao.updateBank("ABC",((TradeDO) v).getId());
                        System.out.println(v.toString());
                    }
                });
            }
        });
    }
}
