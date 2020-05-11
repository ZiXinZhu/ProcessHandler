package com.zzx.transactions.config;


import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.enums.ContainEnum;
import com.zzx.transactions.service.ContainService;


public class ServerList {

    private ContainEnum containEnum;

    private ContainService<BaseDO> containService;

    public ServerList(){
        System.out.println("ServerList初始化");
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
