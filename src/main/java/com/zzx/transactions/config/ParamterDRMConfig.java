package com.zzx.transactions.config;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.zzx.transactions.entity.ParameterDO;
import com.zzx.transactions.service.dal.SystemParamterDalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class ParamterDRMConfig {

    @SuppressWarnings("all")
    @Autowired
    private SystemParamterDalService systemParamterDalService;

    private LoadingCache<String, ParameterDO> systemParameterCaches;

    /**
     * 老OU列表
     */
    private String oldListOU = "B01,B02,B03,B04";
    /**
     * 云OU列表
     */
    private String cloudListOU = "A01,A03,B01,B03";
    /**
     * 非云OU列表
     */
    private String unCloldListOU = "A02,A04,B02,B04";


    @PostConstruct
    public void getSelectParameter() throws ExecutionException, InterruptedException {
        //缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
        systemParameterCaches
                //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
                = CacheBuilder.newBuilder()
                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(8)
                //设置写缓存后8秒钟过期
                .expireAfterWrite(8, TimeUnit.SECONDS)
                //设置缓存容器的初始容量为10
                .initialCapacity(10)
                //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(100)
                //设置要统计缓存的命中率
                .recordStats()
                //设置缓存的移除通知
                .removalListener(notification -> System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause()))
                //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(
                        new CacheLoader<String, ParameterDO>() {
                            @Override
                            public ParameterDO load(String key) throws Exception {
                                ParameterDO parameterDO = systemParamterDalService.getSelectParameter(key);
                                return Objects.nonNull(parameterDO) ? parameterDO : new ParameterDO();
                            }
                        }
                );
    }


    public boolean getSelectParamter(String key) {
        System.out.println("cache stats:");
        //最后打印缓存的命中率等 情况
        System.out.println(systemParameterCaches.stats().toString());
        try {
            ParameterDO parameterDO = systemParameterCaches.get(key);
            return !parameterDO.valuesIsNull() && Boolean.parseBoolean(parameterDO.getResultValue());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getOldListOU(int index) {
        return getOne(oldListOU, index);
    }

    /**
     * 修改老OU配置
     *
     * @param oldListOU
     */
    public void setOldListOU(String oldListOU) {
        this.oldListOU = oldListOU;
    }

    public String getCloudListOU(int index) {
        return getOne(cloudListOU, index);
    }

    /**
     * 修改云OU配置
     *
     * @param cloudListOU
     */
    public void setCloudListOU(String cloudListOU) {
        this.cloudListOU = cloudListOU;
    }

    public String getUnCloldListOU(int index) {
        return getOne(unCloldListOU, index);
    }

    /**
     * 修改非云OU配置
     *
     * @param unCloldListOU
     */
    public void setUnCloldListOU(String unCloldListOU) {
        this.unCloldListOU = unCloldListOU;
    }

    public String getOne(String ou, int index) {
        String[] split = ou.split(",");
        if (index > split.length - 1) {
            return "error";
        }
        return split[index];
    }
}
