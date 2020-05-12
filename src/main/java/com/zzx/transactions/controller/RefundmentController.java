package com.zzx.transactions.controller;

import com.zzx.transactions.common.CommonResult;
import com.zzx.transactions.common.ParamterHandler;
import com.zzx.transactions.common.ProcessHandler;
import com.zzx.transactions.config.ServiceComponent;
import com.zzx.transactions.entity.RefundmentDO;
import com.zzx.transactions.service.RefundmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
public class RefundmentController {
    @Autowired
    RefundmentService refundmentService;

    @GetMapping("/refund")
    public CommonResult<RefundmentDO> refundment(RefundmentDO refundmentDO){
        CommonResult<RefundmentDO> result=new CommonResult<>();
        ProcessHandler.handler(result, new ParamterHandler() {
            @Override
            public void check() {
                refundmentDO.available();
            }

            @Override
            public void process() {
              result.setResult(refundmentService.process(refundmentDO));
            }
        });
        return new CommonResult<>();
    }
}
