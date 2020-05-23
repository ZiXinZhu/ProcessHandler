package com.zzx.transactions.controller;

import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.entity.OtherDO;
import com.zzx.transactions.entity.TradeDO;
import com.zzx.transactions.service.share.IteratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class IteratorController {
    @SuppressWarnings("all")
    @Autowired
    private IteratorService iteratorService;

    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/iterator")
    public void insertMap() {
        Map<String, BaseDO> map = new ConcurrentHashMap<>();
        TradeDO one = new TradeDO();
        one.setId(1);
        one.setTradeDate(date.format(System.currentTimeMillis()));
        one.setTradeTime(time.format(System.currentTimeMillis()));
        one.setMoney("100");
        one.setTradeType("转账");
        one.setIdentity("zzx");
        one.setBank("ABC");
        one.setReport("00");
        one.setBankAccount("zzx");
        one.setInsertime(dateTime.format(System.currentTimeMillis()));

        TradeDO two = new TradeDO();
        two.setId(2);
        two.setTradeDate(date.format(System.currentTimeMillis()));
        two.setTradeTime(time.format(System.currentTimeMillis()));
        two.setMoney("200");
        two.setTradeType("转账");
        two.setIdentity("other");
        two.setBank("ICBC");
        two.setReport("00");
        two.setBankAccount("other");
        two.setInsertime(dateTime.format(System.currentTimeMillis()));

        OtherDO three = new OtherDO();
        three.setId(3);
        three.setName("xiaoming");
        three.setPassword("pwd");
        three.setCreateTime(dateTime.format(System.currentTimeMillis()));
        three.setUpdateTime(dateTime.format(System.currentTimeMillis()));

        map.put("zzx", one);
        map.put("other", two);
        map.put("xiaoming", three);
        iteratorService.mapIterator(map);
    }
}
