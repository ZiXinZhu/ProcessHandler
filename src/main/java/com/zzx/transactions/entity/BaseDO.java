package com.zzx.transactions.entity;

import com.zzx.transactions.enums.ContainEnum;

/**
 * ����entity
 */
public class BaseDO {
    /**
     * ����ö������
     */
    private ContainEnum containEnum;

    /**
     * ����У��
     */
    public void available() {

    }

    @Override
    public BaseDO clone() throws CloneNotSupportedException {
        return (BaseDO) super.clone();
    }

    public ContainEnum getContainEnum() {
        return containEnum;
    }

    public void setContainEnum(ContainEnum containEnum) {
        this.containEnum = containEnum;
    }
}
