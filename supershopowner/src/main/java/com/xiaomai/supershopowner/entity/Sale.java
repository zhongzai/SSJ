package com.xiaomai.supershopowner.entity;

import java.util.Date;

public class Sale {
    private Integer id;

    private String userAccount;

    private String storeCode;

    private Integer custNumber;

    private Long profit;

    private Long loss;

    private Long averagePrice;

    private String bestCategoryCode;

    private String bestCategoryName;

    private String bestGoodsCode;

    private String bestGoodsName;

    private Integer consumptionNumber;

    private Integer newCust;

    private Long afterBuyingRate;

    private Date salesDate;

    private Date salesTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public Integer getCustNumber() {
        return custNumber;
    }

    public void setCustNumber(Integer custNumber) {
        this.custNumber = custNumber;
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }

    public Long getLoss() {
        return loss;
    }

    public void setLoss(Long loss) {
        this.loss = loss;
    }

    public Long getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Long averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getBestCategoryCode() {
        return bestCategoryCode;
    }

    public void setBestCategoryCode(String bestCategoryCode) {
        this.bestCategoryCode = bestCategoryCode == null ? null : bestCategoryCode.trim();
    }

    public String getBestCategoryName() {
        return bestCategoryName;
    }

    public void setBestCategoryName(String bestCategoryName) {
        this.bestCategoryName = bestCategoryName == null ? null : bestCategoryName.trim();
    }

    public String getBestGoodsCode() {
        return bestGoodsCode;
    }

    public void setBestGoodsCode(String bestGoodsCode) {
        this.bestGoodsCode = bestGoodsCode == null ? null : bestGoodsCode.trim();
    }

    public String getBestGoodsName() {
        return bestGoodsName;
    }

    public void setBestGoodsName(String bestGoodsName) {
        this.bestGoodsName = bestGoodsName == null ? null : bestGoodsName.trim();
    }

    public Integer getConsumptionNumber() {
        return consumptionNumber;
    }

    public void setConsumptionNumber(Integer consumptionNumber) {
        this.consumptionNumber = consumptionNumber;
    }

    public Integer getNewCust() {
        return newCust;
    }

    public void setNewCust(Integer newCust) {
        this.newCust = newCust;
    }

    public Long getAfterBuyingRate() {
        return afterBuyingRate;
    }

    public void setAfterBuyingRate(Long afterBuyingRate) {
        this.afterBuyingRate = afterBuyingRate;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public Date getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(Date salesTotal) {
        this.salesTotal = salesTotal;
    }
}