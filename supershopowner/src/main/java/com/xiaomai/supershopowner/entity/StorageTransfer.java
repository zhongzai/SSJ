package com.xiaomai.supershopowner.entity;

public class StorageTransfer {	
    private String storeCode;

    private Integer categoryNumber;

    private Integer goodsNumber;

    private Double totalValue;
    
    private Double  totalLoss;

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

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public Double getTotalLoss() {
		return totalLoss;
	}

	public void setTotalLoss(Double totalLoss) {
		this.totalLoss = totalLoss;
	}



}