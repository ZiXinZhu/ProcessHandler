package com.zzx.transactions.service.dal.impl;

import com.zzx.transactions.dao.RefundmentDOMapper;
import com.zzx.transactions.entity.RefundmentDO;
import com.zzx.transactions.service.dal.ContainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundmentContainServiceImpl implements ContainService<RefundmentDO> {


    public RefundmentContainServiceImpl() {
        System.out.println("RefundmentContainServiceImpl初始化");
    }

    @SuppressWarnings("all")
    @Autowired
    private RefundmentDOMapper refundmentDOMapper;

    @Override
    public RefundmentDO lock(int id) {
        return refundmentDOMapper.lock(id);
    }

    @Override
    public int update(RefundmentDO refundmentDO) {
        return refundmentDOMapper.updateByPrimaryKeySelective(refundmentDO);
    }

    @Override
    public int insert(RefundmentDO refundmentDO) {
        return refundmentDOMapper.insertSelective(refundmentDO);
    }
}
