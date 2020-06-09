package com.zzx.transactions.service.share.impl;

import com.zzx.transactions.config.ParamterDRMConfig;
import com.zzx.transactions.service.share.SystemParamterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemParamterServiceImpl implements SystemParamterService {

    @Autowired
    private ParamterDRMConfig paramterDRMConfig;

    @Override
    public boolean getSelectParamter(String key) {
        return paramterDRMConfig.getSelectParamter(key);
    }
}
