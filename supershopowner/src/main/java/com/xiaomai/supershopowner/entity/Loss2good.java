package com.xiaomai.supershopowner.entity;

import java.util.Date;

public class Loss2good extends Goods{
    private Integer id;

    private String goodsCode;

    private String lossCode;
    
    private Integer lossTotal;
    

    private Double lossValue;

    private Integer lossNumber;
    
    private Date lossTime;
    
    private Date updateTime;
    

	public Double getLossValue() {
		return lossValue;
	}

	public void setLossValue(Double lossValue) {
		this.lossValue = lossValue;
	}

	public Integer getLossNumber() {
		return lossNumber;
	}

	public void setLossNumber(Integer lossNumber) {
		this.lossNumber = lossNumber;
	}

	public Date getLossTime() {
		return lossTime;
	}

	public void setLossTime(Date lossTime) {
		this.lossTime = lossTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getLossCode() {
        return lossCode;
    }

    public void setLossCode(String lossCode) {
        this.lossCode = lossCode == null ? null : lossCode.trim();
    }

	public Integer getLossTotal() {
		return lossTotal;
	}

	public void setLossTotal(Integer lossTotal) {
		this.lossTotal = lossTotal;
	}
}