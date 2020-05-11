package com.zzx.transactions.entity;

import java.util.Date;

public class RefundmentDO extends BaseDO{
    private Integer id;

    private String refundmentId;

    private String orderId;

    private String money;

    private String mark;

    private Date creadeTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "RefundmentDO{" +
                "id=" + id +
                ", refundmentId='" + refundmentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", money='" + money + '\'' +
                ", mark='" + mark + '\'' +
                ", creadeTime=" + creadeTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefundmentId() {
        return refundmentId;
    }

    public void setRefundmentId(String refundmentId) {
        this.refundmentId = refundmentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getCreadeTime() {
        return creadeTime;
    }

    public void setCreadeTime(Date creadeTime) {
        this.creadeTime = creadeTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}