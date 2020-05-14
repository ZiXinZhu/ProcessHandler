package com.zzx.transactions.service;

import com.zzx.transactions.entity.BaseDO;

import java.util.Map;

public interface IteratorService {

    /**
     * 遍历map入库数据
     * @param map
     */
    void mapIterator(Map<String, ? extends BaseDO> map);
}
