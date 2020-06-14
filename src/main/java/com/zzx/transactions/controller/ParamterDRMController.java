package com.zzx.transactions.controller;

import com.zzx.transactions.common.CommonResult;
import com.zzx.transactions.common.ParamterHandler;
import com.zzx.transactions.common.ProcessHandler;
import com.zzx.transactions.config.ParamterDRMConfig;
import com.zzx.transactions.exceptions.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/drm")
public class ParamterDRMController {


    @Autowired
    ParamterDRMConfig paramterDRMConfig;

    @GetMapping("/old/list")
    public CommonResult<String> setOldListOU(String list) {
        CommonResult<String> result = new CommonResult<>();
        ProcessHandler.handler(result, new ParamterHandler() {
            @Override
            public void check() {
                if (Objects.isNull(list)) {
                    throw new CommonException("DRM老OU配置不能为空！");
                }
            }

            @Override
            public void process() {
                paramterDRMConfig.setOldListOU(list);
                result.setResult("成功");
                result.setDescribe("老OU列表更新内容为：" + list);
            }
        });
        return result;
    }
}
