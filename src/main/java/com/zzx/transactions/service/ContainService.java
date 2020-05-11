package com.zzx.transactions.service;

public interface ContainService<T> {

    /**
     * 锁住需要处理的数据
     * * @param id 通过主键查找
     *
     * @return
     */
    T lock(int id);

    /**
     * 更新操作
     * * @param t boy and girl
     *
     * @return
     */
    int update(T t);

    /**
     * 数据插入操作
     *
     * @param t boy and girl
     * @return
     */
    int insert(T t);
}
