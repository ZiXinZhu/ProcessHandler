package com.zzx.transactions.entity;

import java.util.Date;

public class OrderDO extends BaseDO{
    private Integer id;

    private String orderId;

    private String money;

    private String goods;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "OrderDO{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", money='" + money + '\'' +
                ", goods='" + goods + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}