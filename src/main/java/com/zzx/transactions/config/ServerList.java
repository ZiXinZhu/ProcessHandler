package com.zzx.transactions.config;


import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.enums.ContainEnum;
import com.zzx.transactions.service.dal.ContainService;


public class ServerList {

    /**
     * ö�ٱ�ʾbean����
     */
    private ContainEnum containEnum;

    /**
     * �����ӿڶ�Ӧ����ʵ�ֵ�bean
     */
    private ContainService<BaseDO> containService;

    public ServerList() {
        System.out.println("ServerList��ʼ��");
    }


    public ContainEnum getContainEnum() {
        return containEnum;
    }

    public void setContainEnum(ContainEnum containEnum) {
        this.containEnum = containEnum;
    }

    public ContainService<BaseDO> getContainService() {
        return containService;
    }

    public void setContainService(ContainService<BaseDO> containService) {
        this.containService = containService;
    }
}
