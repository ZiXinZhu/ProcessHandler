package com.zzx.transactions.common;

public class BaseResult {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回代码
     */
    private int code;
    /**
     * 返回描述
     */
    private String describe;

    @Override
    public String toString() {
        return "BaseResult{" +
                "success=" + success +
                ", code=" + code +
                ", describe='" + describe + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
