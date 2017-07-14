package com.xiaomai.supershopowner.entity;

import java.util.Date;

public class SaleAnalyse {
    private Integer id;

    private String storeCode;

    private Date salesDate;

    private String salesPeriod;

    private Double salesAmount;

    private Double proportion;
    
    private String salesTime;
    

    public Integer getId() {
        return id;
    }


	public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public String getSalesTime() {
		return salesTime;
	}


	public void setSalesTime(String salesTime) {
		this.salesTime = salesTime;
	}


	public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public Date getSalesDate() {
		return salesDate;
	}


	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}


	public String getSalesPeriod() {
        return salesPeriod;
    }

    public void setSalesPeriod(String salesPeriod) {
        this.salesPeriod = salesPeriod == null ? null : salesPeriod.trim();
    }


	public Double getSalesAmount() {
		return salesAmount;
	}


	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}


	public Double getProportion() {
		return proportion;
	}


	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

}