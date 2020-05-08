package com.zzx.transactions.service;

import com.zzx.transactions.entity.BaseDO;

import java.util.Map;

public interface IteratorService {
    void mapIterator(Map<String, ? extends BaseDO> map);
}
