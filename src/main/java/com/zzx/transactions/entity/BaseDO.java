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

    public ContainEnum getContainEnum() {
        return containEnum;
    }

    public void setContainEnum(ContainEnum containEnum) {
        this.containEnum = containEnum;
    }
}
