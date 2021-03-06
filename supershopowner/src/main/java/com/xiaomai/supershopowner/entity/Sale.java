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
    
    private Double salesWeekToday;
    
    private Double salesWeekNow;
    
    private Integer custWeekToday;
    
    private Integer custWeekNow;

    private Double lossAmount;
    
    private Integer lossNumber;

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
    
    private Integer salesTotalNumber;
    
    private String todaySalesUpdown;
    
    private String todayCustUpdown;
    
    private String yesSalesUpdown;
    
    private String yesCustUpdown;
    
    private String averagePriceUpdown;
    
    private String profitUpdown;
    
    private String lossUpdown;
	
	public String getTodaySalesUpdown() {
		return todaySalesUpdown;
	}

	public void setTodaySalesUpdown(String todaySalesUpdown) {
		this.todaySalesUpdown = todaySalesUpdown;
	}

	public String getTodayCustUpdown() {
		return todayCustUpdown;
	}

	public void setTodayCustUpdown(String todayCustUpdown) {
		this.todayCustUpdown = todayCustUpdown;
	}

	public String getYesSalesUpdown() {
		return yesSalesUpdown;
	}

	public void setYesSalesUpdown(String yesSalesUpdown) {
		this.yesSalesUpdown = yesSalesUpdown;
	}

	public String getYesCustUpdown() {
		return yesCustUpdown;
	}

	public void setYesCustUpdown(String yesCustUpdown) {
		this.yesCustUpdown = yesCustUpdown;
	}

	public String getAveragePriceUpdown() {
		return averagePriceUpdown;
	}

	public void setAveragePriceUpdown(String averagePriceUpdown) {
		this.averagePriceUpdown = averagePriceUpdown;
	}

	public String getProfitUpdown() {
		return profitUpdown;
	}

	public void setProfitUpdown(String profitUpdown) {
		this.profitUpdown = profitUpdown;
	}

	public String getLossUpdown() {
		return lossUpdown;
	}

	public void setLossUpdown(String lossUpdown) {
		this.lossUpdown = lossUpdown;
	}


	public Integer getSalesTotalNumber() {
		return salesTotalNumber;
	}

	public void setSalesTotalNumber(Integer salesTotalNumber) {
		this.salesTotalNumber = salesTotalNumber;
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


	public Double getLossAmount() {
		return lossAmount;
	}

	public void setLossAmount(Double lossAmount) {
		this.lossAmount = lossAmount;
	}

	public Integer getLossNumber() {
		return lossNumber;
	}

	public void setLossNumber(Integer lossNumber) {
		this.lossNumber = lossNumber;
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

	public Double getSalesWeekToday() {
		return salesWeekToday;
	}

	public void setSalesWeekToday(Double salesWeekToday) {
		this.salesWeekToday = salesWeekToday;
	}

	public Double getSalesWeekNow() {
		return salesWeekNow;
	}

	public void setSalesWeekNow(Double salesWeekNow) {
		this.salesWeekNow = salesWeekNow;
	}

	public Integer getCustWeekToday() {
		return custWeekToday;
	}

	public void setCustWeekToday(Integer custWeekToday) {
		this.custWeekToday = custWeekToday;
	}

	public Integer getCustWeekNow() {
		return custWeekNow;
	}

	public void setCustWeekNow(Integer custWeekNow) {
		this.custWeekNow = custWeekNow;
	}

}