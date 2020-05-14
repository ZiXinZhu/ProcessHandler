package com.zzx.transactions.config;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.service.ContainService;

import java.util.ArrayList;
import java.util.List;

public class ServiceComponent {

    /**
     * 存放不同实现的bean的list
     */
    private List<ServerList> list = new ArrayList<>();

    /**
     * 通过init方法将bean存放容器
     */
    private ImmutableMap<String, ServerList> service;

    public ServiceComponent() {
        System.out.println("ServiceComponent初始化");
    }

    /**
     * 初始方法
     */
    void init() {
        service = Maps.uniqueIndex(list, serverList ->
        {
            assert serverList != null;
            return serverList.getContainEnum().getCode();
        });
    }

    public void setList(List list) {
        this.list = list;
    }

    public ContainService<BaseDO> getServer(String code) {
        return service.get(code).getContainService();
    }
}
