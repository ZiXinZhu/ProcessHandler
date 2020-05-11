package com.zzx.transactions.controller;

import com.zzx.transactions.config.ServiceComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
public class RefundmentController {
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    ServiceComponent component;


}
