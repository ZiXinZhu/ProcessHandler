package com.zzx.transactions.common;

public class ProcessHandler {

    public static void handler(BaseResult result, ParamterHandler paramterHandler) {
        try {
            paramterHandler.check();
            paramterHandler.process();
            result.setSuccess(true);
            result.setCode(200);
            result.setDescribe("success");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setCode(500);
            result.setDescribe(e.getMessage());
        }
    }
}
