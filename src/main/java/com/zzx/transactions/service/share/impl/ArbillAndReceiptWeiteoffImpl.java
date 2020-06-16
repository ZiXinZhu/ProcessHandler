package com.zzx.transactions.service.share.impl;

import com.zzx.transactions.entity.vo.ArBillAndReceiptResultVO;
import com.zzx.transactions.entity.vo.ArBillAndReceiptVO;
import com.zzx.transactions.service.share.ArbillAndReceiptWeiteoff;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ArbillAndReceiptWeiteoffImpl implements ArbillAndReceiptWeiteoff {
    @Override
    public List<ArBillAndReceiptResultVO> explainFile(List<ArBillAndReceiptVO> vos) {
        List<ArBillAndReceiptResultVO> result=new LinkedList<>();
        //TODO 分组
         Map<String,List<ArBillAndReceiptVO> >map= groupMap(vos);
        //TODO 校验
        for (Map.Entry<String, List<ArBillAndReceiptVO>> stringListEntry : map.entrySet()) {
            //①
            List<String> arbill = new ArrayList<>();
            List<String> receipt = new ArrayList<>();
            List<ArBillAndReceiptVO> file = stringListEntry.getValue();
            for (ArBillAndReceiptVO aFile : file) {
                arbill.add(aFile.getBillNo());
                receipt.add(aFile.getReceiptNo());
            }
            checkFile( arbill, receipt, result,"测试");
        }
        //③排序返回

        return result;
    }

    private void checkFile(List<String> arbill,List<String> receipt,List<ArBillAndReceiptResultVO> result,String message){
        for (String anArbill : arbill) {
            ArBillAndReceiptResultVO arBillAndReceiptResultVO = new ArBillAndReceiptResultVO();
            arBillAndReceiptResultVO.setBillNo(anArbill);
            arBillAndReceiptResultVO.setCkeckResult(message);
            result.add(arBillAndReceiptResultVO);
        }
    }

    private Map<String,List<ArBillAndReceiptVO> > groupMap(List<ArBillAndReceiptVO> vos){
        //②
        Map<String,List<ArBillAndReceiptVO> > map=new HashMap<>();
        for (ArBillAndReceiptVO vo:vos
             ) {
            if(map.containsKey(vo.getBillGroupNo())){
                List<ArBillAndReceiptVO> voList= map.get(vo.getBillGroupNo());
                voList.add(vo);
               map.replace(vo.getBillGroupNo(),voList);
            }else {
                List<ArBillAndReceiptVO> voList=new ArrayList<>();
                voList.add(vo);
                map.put(vo.getBillGroupNo(),voList);
            }
        }
        return map;
    }
}
