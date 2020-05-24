package com.zzx.transactions.config;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.zzx.transactions.entity.BaseDO;
import com.zzx.transactions.service.dal.ContainService;

import java.util.ArrayList;
import java.util.List;

public class ServiceComponent {

    /**
     * ��Ų�ͬʵ�ֵ�bean��list
     */
    private List<ServerList> list = new ArrayList<>();

    /**
     * ͨ��init������bean�������
     */
    private ImmutableMap<String, ServerList> service;

    public ServiceComponent() {
        System.out.println("ServiceComponent��ʼ��");
    }

    /**
     * ��ʼ����
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
