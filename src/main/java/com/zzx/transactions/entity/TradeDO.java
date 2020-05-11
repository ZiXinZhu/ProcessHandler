package com.zzx.transactions.entity;

import com.zzx.transactions.exceptions.CommonException;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author Husky
 */
public class TradeDO extends BaseDO implements Serializable {
    private int id;
    private String tradeDate;
    private String tradeTime;
    private String money;
    private String tradeType;
    private String remark;
    private String identity;
    private String bank;
    private String report;
    private String bankAccount;
    private String insertime;


    @Override
    public void available() {
        if (this.getId()==0){
            throw new CommonException(502,"ID不能为空！");
        }
        Assert.notNull(this.getBank(), "银行缩写不能为空");
        Assert.notNull(this.getRemark(), "备注不能为空");
    }

    @Override
    public String toString() {
        return "TradeDO{" +
                "id=" + id +
                ", tradeDate='" + tradeDate + '\'' +
                ", tradeTime='" + tradeTime + '\'' +
                ", money='" + money + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", remark='" + remark + '\'' +
                ", identity='" + identity + '\'' +
                ", bank='" + bank + '\'' +
                ", report='" + report + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", insertime='" + insertime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getInsertime() {
        return insertime;
    }

    public void setInsertime(String insertime) {
        this.insertime = insertime;
    }
}
