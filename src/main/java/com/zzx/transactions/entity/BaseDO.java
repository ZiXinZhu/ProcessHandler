package com.zzx.transactions.entity;

import com.zzx.transactions.enums.ContainEnum;
import com.zzx.transactions.exceptions.CommonException;
import org.springframework.util.Assert;

public class BaseDO{
    private ContainEnum containEnum;

    public void available() {

    }

    public ContainEnum getContainEnum() {
        return containEnum;
    }

    public void setContainEnum(ContainEnum containEnum) {
        this.containEnum = containEnum;
    }
}
