package com.zzx.transactions.enums;

public enum ContainEnum {

    ORDER("ORDER", "订单"),

    REFUNDMENT("REFUNDMENT", "退款");

    private String code;
    private String desc;

    ContainEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
