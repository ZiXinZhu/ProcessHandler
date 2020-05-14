package com.zzx.transactions.common;

import com.zzx.transactions.exceptions.CommonException;

public class ProcessHandler {

    /**
     * 业务处理公共模板
     *
     * @param result 返回值
     * @param paramterHandler 公共模板
     */
    public static void handler(BaseResult result, ParamterHandler paramterHandler) {
        try {
            paramterHandler.check();
            paramterHandler.process();
            result.setSuccess(true);
            result.setCode(200);
            result.setDescribe("success");
        } catch (CommonException e) {
            result.setSuccess(false);
            result.setCode(e.getCode());
            result.setDescribe(e.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setCode(500);
            result.setDescribe(e.getMessage());
        }
    }
}
