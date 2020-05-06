package com.zzx.transactions.controller;

import com.zzx.transactions.common.CommonResult;
import com.zzx.transactions.common.ParamterHandler;
import com.zzx.transactions.common.ProcessHandler;
import com.zzx.transactions.entity.TradeDO;
import com.zzx.transactions.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

    @SuppressWarnings("all")
    @Autowired
    TradeService tradeService;

    @GetMapping("/transaction")
    public CommonResult<String> process() {
        TradeDO tradeDO = new TradeDO();
        tradeDO.setId(311076);
        tradeDO.setBank("ICBC");
        tradeDO.setRemark("TEST");
        CommonResult<String> result = new CommonResult<>();
        ProcessHandler.handler(result, new ParamterHandler() {
            @Override
            public void check() {
                tradeDO.available();
            }

            @Override
            public void process() {
                result.setResult(tradeService.process(tradeDO));
            }
        });
        return result;
    }
}
