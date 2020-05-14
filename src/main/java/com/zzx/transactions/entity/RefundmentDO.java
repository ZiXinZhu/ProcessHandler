package com.zzx.transactions.entity;

import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * 退款处理表数据
 */
public class RefundmentDO extends BaseDO {
    /**
     * id
     */
    private Integer id;

    /**
     * 退款id
     */
    @NonNull
    private String refundmentId;

    /**
     * 关联订单号id
     */
    @NonNull
    private String orderId;

    /**
     * 金额
     */
    @NonNull
    private String money;

    /**
     * 备注
     */
    @NonNull
    private String mark;

    /**
     * 创建时间
     */
    private Date creadeTime;

    /**
     * 更新时间
     */
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