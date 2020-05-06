package com.zzx.transactions.common;

public class CommonResult<T> extends BaseResult {
    private T result;

    @Override
    public String toString() {
        return "CommonResult{" +
                "result=" + result +
                '}';
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
