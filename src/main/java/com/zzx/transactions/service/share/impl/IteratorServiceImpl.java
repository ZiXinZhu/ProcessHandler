package com.zzx.transactions.service.share.impl;

import com.zzx.transactions.config.ParamterDRMConfig;
import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.entity.TradeDO;
import com.zzx.transactions.service.dal.TradeDalService;
import com.zzx.transactions.service.share.IteratorService;
import com.zzx.transactions.service.share.SystemParamterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

@Service
public class IteratorServiceImpl implements IteratorService {
    private static Logger logger = LoggerFactory.getLogger("info");

    private static final String SELECT_PARAMETER = "SELECT_PARAMETER";
    @SuppressWarnings("all")
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private TradeDalService tradeDalService;

    @Autowired
    private SystemParamterService systemParamterService;
    @Autowired
    private ParamterDRMConfig paramterDRMConfig;

    @Override
    public void mapIterator(Map<String, ? extends BaseDO> map) {
        if (!systemParamterService.getSelectParamter(SELECT_PARAMETER)) {
            return;
        }
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @NonNull
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                map.forEach((k, v) -> {
                    if ("other".equals(k)) {
                        TradeDO tradeDO = (TradeDO) v;
                        tradeDO.setIdentity(paramterDRMConfig.getOldListOU(0));
                        logger.info("tradeDO¶ÔÏóÎª:{}", tradeDO.toString());
                        tradeDalService.insertOne(tradeDO);
                        tradeDalService.updateBank("ABC", tradeDO.getId());
                        System.out.println(v.toString());
                    }
                });
            }
        });
    }
}
