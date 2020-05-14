package com.zzx.transactions.exceptions;

public class CommonException extends RuntimeException {
    /**
     * 异常code
     */
    private int code;
    /**
     * 异常详细信息
     */
    private String message;
    /**
     * 源异常
     */
    private Throwable throwable;

    public CommonException() {
    }

    public CommonException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonException(Throwable throwable) {
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
