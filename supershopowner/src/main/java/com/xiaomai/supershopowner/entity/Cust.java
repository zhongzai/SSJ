package com.xiaomai.supershopowner.entity;

import java.util.Date;

public class Cust {
    private Integer id;
    
    private Integer count;
   
    private String storeCode;

	private String custId;

    private String custAccount;

    private Integer repeatBuy;

    private Integer isNew;

    private Date comingTime;

    private Integer isBuy;

    private Integer status;

    private String headUrl;

    private Double consumptionAmount;

    private String categoryCode;
    
    private Integer type;
    
	private String custSex;
	
	private String custPhone;
	
    private boolean storedValue;
	
	private String custName; 
	
	public String getCustSex() {
		return custSex;
	}

	public void setCustSex(String custSex) {
		this.custSex = custSex;
	}
    public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public boolean isStoredValue() {
		return storedValue;
	}

	public void setStoredValue(boolean storedValue) {
		this.storedValue = storedValue;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	
    
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public String getCustAccount() {
        return custAccount;
    }

    public void setCustAccount(String custAccount) {
        this.custAccount = custAccount == null ? null : custAccount.trim();
    }

    public Integer getRepeatBuy() {
        return repeatBuy;
    }

    public void setRepeatBuy(Integer repeatBuy) {
        this.repeatBuy = repeatBuy;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Date getComingTime() {
        return comingTime;
    }

    public void setComingTime(Date comingTime) {
        this.comingTime = comingTime;
    }

    public Integer getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(Integer isBuy) {
        this.isBuy = isBuy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    public Double getConsumptionAmount() {
		return consumptionAmount;
	}

	public void setConsumptionAmount(Double consumptionAmount) {
		this.consumptionAmount = consumptionAmount;
	}

	public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }
}