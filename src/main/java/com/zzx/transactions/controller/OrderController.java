package com.zzx.transactions.controller;

import com.zzx.transactions.common.CommonResult;
import com.zzx.transactions.common.ParamterHandler;
import com.zzx.transactions.common.ProcessHandler;
import com.zzx.transactions.config.ServiceComponent;
import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.entity.OrderDO;
import com.zzx.transactions.entity.TradeDO;
import com.zzx.transactions.service.ContainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
public class OrderController {

    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    ServiceComponent component;


    @GetMapping("/order")
    public CommonResult<OrderDO> order() {
        OrderDO orderDO = new OrderDO();
        orderDO.setId(1);
        orderDO.setUserId(1);
        orderDO.setOrderId("202004050000000012344322");
        orderDO.setMoney("998");
        orderDO.setGoods("手机");


        CommonResult<OrderDO> result = new CommonResult<>();
        ProcessHandler.handler(result, new ParamterHandler() {
            @Override
            public void check() {
                orderDO.available();
            }

            @Override
            public void process() {
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @NonNull
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                        ContainService<BaseDO> containService = component.getServer("ORDER");
                        OrderDO order = (OrderDO) containService.lock(orderDO.getId());
                        int res = containService.update(orderDO);
                        System.out.println(order.toString());
                        if(res == 1) {
                            result.setResult(order);
                        }else {
                            result.setResult(null);
                        }
                    }
                });

            }
        });
        return result;
    }
}
