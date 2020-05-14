package com.zzx.transactions.entity;

import com.zzx.transactions.enums.ContainEnum;

/**
 * 基础entity
 */
public class BaseDO {
    /**
     * 容器枚举类型
     */
    private ContainEnum containEnum;

    /**
     * 参数校验
     */
    public void available() {

    }

    public ContainEnum getContainEnum() {
        return containEnum;
    }

    public void setContainEnum(ContainEnum containEnum) {
        this.containEnum = containEnum;
    }
}
