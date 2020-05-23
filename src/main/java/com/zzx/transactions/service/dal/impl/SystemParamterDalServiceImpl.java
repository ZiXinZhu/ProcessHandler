package com.zzx.transactions.service.dal.impl;

import com.zzx.transactions.dao.ParameterDOMapper;
import com.zzx.transactions.entity.ParameterDO;
import com.zzx.transactions.service.dal.SystemParamterDalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemParamterDalServiceImpl implements SystemParamterDalService {

    @Autowired
    private ParameterDOMapper parameterDOMapper;

    @Override
    public ParameterDO getSelectParameter(String keyWord) {
        return parameterDOMapper.getSelectParameter(keyWord);
    }
}
