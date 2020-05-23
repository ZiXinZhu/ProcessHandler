package com.zzx.transactions.entity;

public class ParameterDO {
    private Integer id;

    private String keyWord;

    private String resultValue;

    public boolean valuesIsNull() {
        return this.resultValue == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }
}