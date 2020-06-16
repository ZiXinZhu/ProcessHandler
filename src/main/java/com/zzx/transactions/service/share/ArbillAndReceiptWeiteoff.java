package com.zzx.transactions.service.share;

import com.zzx.transactions.entity.vo.ArBillAndReceiptResultVO;
import com.zzx.transactions.entity.vo.ArBillAndReceiptVO;

import java.util.List;

public interface ArbillAndReceiptWeiteoff {

    public List<ArBillAndReceiptResultVO> explainFile(List<ArBillAndReceiptVO> vos);
}
