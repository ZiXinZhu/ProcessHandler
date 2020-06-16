package com.zzx.transactions.controller;


import com.zzx.transactions.entity.vo.ArBillAndReceiptResultVO;
import com.zzx.transactions.entity.vo.ArBillAndReceiptVO;
import com.zzx.transactions.service.share.ArbillAndReceiptWeiteoff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArbillAndReceiptWeiteoffController {

    @Autowired
    ArbillAndReceiptWeiteoff arbillAndReceiptWeiteoff;

    @GetMapping("/writeoff")
    public List<ArBillAndReceiptResultVO> writeoff(){
        ArBillAndReceiptVO vo=new ArBillAndReceiptResultVO();
        vo.setBillGroupNo("1");
        vo.setBillNo("123");
        vo.setReceiptGroupNo("1");
        vo.setReceiptNo("321");
        ArBillAndReceiptVO vo1=new ArBillAndReceiptResultVO();
        vo1.setBillGroupNo("1");
        vo1.setBillNo("456");
        vo1.setReceiptGroupNo("1");
        vo1.setReceiptNo("654");
        ArBillAndReceiptVO vo2=new ArBillAndReceiptResultVO();
        vo2.setBillGroupNo("2");
        vo2.setBillNo("123");
        vo2.setReceiptGroupNo("2");
        vo2.setReceiptNo("321");
        ArBillAndReceiptVO vo3=new ArBillAndReceiptResultVO();
        vo3.setBillGroupNo("2");
        vo3.setBillNo("456");
        vo3.setReceiptGroupNo("2");
        vo3.setReceiptNo("654");
        ArBillAndReceiptVO vo4=new ArBillAndReceiptResultVO();
        vo4.setBillGroupNo("4");
        vo4.setBillNo("123");
        vo4.setReceiptGroupNo("4");
        vo4.setReceiptNo("321");
        ArBillAndReceiptVO vo5=new ArBillAndReceiptResultVO();
        vo5.setBillGroupNo("4");
        vo5.setBillNo("789");
        vo5.setReceiptGroupNo("4");
        vo5.setReceiptNo("987");
        ArBillAndReceiptVO vo6=new ArBillAndReceiptResultVO();
        vo6.setBillGroupNo("5");
        vo6.setBillNo("123");
        vo6.setReceiptGroupNo("5");
        vo6.setReceiptNo("321");
        List<ArBillAndReceiptVO> vos=new ArrayList<>();
        vos.add(vo1);
        vos.add(vo2);
        vos.add(vo3);
        vos.add(vo4);
        vos.add(vo5);
        vos.add(vo6);
        vos.add(vo);
        return arbillAndReceiptWeiteoff.explainFile(vos);
    }
}
