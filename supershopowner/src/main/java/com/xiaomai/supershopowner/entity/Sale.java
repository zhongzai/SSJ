package com.xiaomai.supershopowner.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xiaomai.supershopowner.common.JsonDateTimeSerializer;

public class Sale {
    private Integer id;

    private String userAccount;

    private String storeCode;

    private Integer custNumber;

    private Double profit;

    private Double loss;

    private Double averagePrice;

    private String bestCategoryCode;

    private String bestCategoryName;

    private String bestGoodsCode;

    private String bestGoodsName;

    private Integer consumptionNumber;

    private Integer newCust;

    private Double afterBuyingRate;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private Date salesDate;

    private Double salesTotal;
    
    private Integer bestSalesNumber;
    
    private Integer upDown;

    public Integer getUpDown() {
		return upDown;
	}

	public void setUpDown(Integer upDown) {
		this.upDown = upDown;
	}

	public Integer getBestSalesNumber() {
		return bestSalesNumber;
	}

	public void setBestSalesNumber(Integer bestSalesNumber) {
		this.bestSalesNumber = bestSalesNumber;
	}

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


    public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getLoss() {
		return loss;
	}

	public void setLoss(Double loss) {
		this.loss = loss;
	}

	public Double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public Double getAfterBuyingRate() {
		return afterBuyingRate;
	}

	public void setAfterBuyingRate(Double afterBuyingRate) {
		this.afterBuyingRate = afterBuyingRate;
	}

	public Double getSalesTotal() {
		return salesTotal;
	}

	public void setSalesTotal(Double salesTotal) {
		this.salesTotal = salesTotal;
	}

	public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

}