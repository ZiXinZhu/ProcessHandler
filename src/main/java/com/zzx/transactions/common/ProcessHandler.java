package com.zzx.transactions.common;

import com.zzx.transactions.exceptions.CommonException;

public class ProcessHandler {

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
        }catch (Exception e){
            result.setSuccess(false);
            result.setCode(500);
            result.setDescribe(e.getMessage());
        }
    }
}
