package com.xiaomai.supershopowner.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Flow {
    private String flowCode;
    
    private String phone;

    private Integer type;

    private BigDecimal amount;
    
    private Date flowTime;


    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode == null ? null : flowCode.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getFlowTime() {
        return flowTime;
    }

    public void setFlowTime(Date flowTime) {
        this.flowTime = flowTime;
    }
}