package com.zzx.transactions.controller;

import com.zzx.transactions.common.CommonResult;
import com.zzx.transactions.common.ParamterHandler;
import com.zzx.transactions.common.ProcessHandler;
import com.zzx.transactions.entity.OrderDO;
import com.zzx.transactions.enums.ContainEnum;
import com.zzx.transactions.service.share.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/order")
    public CommonResult<OrderDO> order() {
        OrderDO orderDO = new OrderDO();
        orderDO.setId(1);
        orderDO.setUserId(1);
        orderDO.setOrderId("202004050000000012344322");
        orderDO.setMoney("998");
        orderDO.setGoods("手机");
        orderDO.setContainEnum(ContainEnum.ORDER);
        CommonResult<OrderDO> result = new CommonResult<>();
        ProcessHandler.handler(result, new ParamterHandler() {
            @Override
            public void check() {
                orderDO.available();
            }

            @Override
            public void process() {
                result.setResult(orderService.process(orderDO));
            }
        });
        return result;
    }
}
