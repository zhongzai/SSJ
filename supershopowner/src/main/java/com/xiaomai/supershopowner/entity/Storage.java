package com.xiaomai.supershopowner.entity;

public class Storage {
    private Integer id;

    private String storeCode;

    private Integer categoryNumber;

    private Integer goodsNumber;

    private Long totalValue;

    private Long totalLoss;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public Integer getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(Integer categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Long getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Long totalValue) {
        this.totalValue = totalValue;
    }

    public Long getTotalLoss() {
        return totalLoss;
    }

    public void setTotalLoss(Long totalLoss) {
        this.totalLoss = totalLoss;
    }
}