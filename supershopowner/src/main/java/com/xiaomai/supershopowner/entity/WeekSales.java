package com.xiaomai.supershopowner.entity;

import java.util.Date;

public class WeekSales {
    private Integer id;

    private String goodsCode;

    private Date salesDate;

    private String temperature;

    private Integer salesNumber;
    
    private String storeCode;
    

    public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

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
		this.goodsCode = goodsCode;
	}

	public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public Integer getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(Integer salesNumber) {
        this.salesNumber = salesNumber;
    }
}