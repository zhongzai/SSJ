package com.xiaomai.supershopowner.entity;

public class Loss2good {
    private Integer id;

    private String goodsCode;

    private String lossCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getLossCode() {
        return lossCode;
    }

    public void setLossCode(String lossCode) {
        this.lossCode = lossCode == null ? null : lossCode.trim();
    }
}