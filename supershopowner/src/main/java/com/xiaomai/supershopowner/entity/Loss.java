package com.xiaomai.supershopowner.entity;

import java.util.Date;
import java.util.List;

public class Loss {
    private Integer id;

    private String storeCode;

    private Double lossValue;

    private Integer lossNumber;
    
    private String lossCode;

    private Date lossTime;
    
    private Date updateTime;
    
    private List<Goods> goods;

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

    public String getLossCode() {
        return lossCode;
    }

    public void setLossCode(String lossCode) {
        this.lossCode = lossCode == null ? null : lossCode.trim();
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

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}
}