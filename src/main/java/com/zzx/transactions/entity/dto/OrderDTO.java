package com.zzx.transactions.entity.dto;

import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.entity.OrderDO;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * 订单数据表数据
 */
public class OrderDTO extends BaseDO {
    /**
     * id
     */
    private Integer id;

    /**
     * 订单id
     */
    @NonNull
    private String orderId;

    /**
     * 金额
     */
    @NonNull
    private String money;

    /**
     * 商品
     */
    @NonNull
    private String goods;

    /**
     * 用户id
     */
    @NonNull
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", money='" + money + '\'' +
                ", goods='" + goods + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public OrderDO convertOrderDTOToOrderDO(){
        OrderDO orderDO=new OrderDO();
        orderDO.setId(this.id);
        orderDO.setUserId(this.userId);
        orderDO.setGoods(this.goods);
        orderDO.setMoney(this.money);
        orderDO.setOrderId(this.orderId);
        orderDO.setCreateTime(this.createTime);
        orderDO.setUpdateTime(this.updateTime);
        return orderDO;
    }

    public OrderDTO convertOrderDOToOrderDTO(OrderDO orderDO){
        this.id=orderDO.getId();
        this.goods=orderDO.getGoods();
        this.money=orderDO.getMoney();
        this.orderId=orderDO.getOrderId();
        this.userId=orderDO.getUserId();
        this.createTime=orderDO.getCreateTime();
        this.updateTime=orderDO.getUpdateTime();
        return this;
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