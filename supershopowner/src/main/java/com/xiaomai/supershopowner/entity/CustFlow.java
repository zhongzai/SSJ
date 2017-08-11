package com.xiaomai.supershopowner.entity;

import java.util.Date;

public class CustFlow {
	private Integer id;
	
	private String storeCode;
	
	private String FlowDate;
	
	private String custId;
	
	private String flowCode;
	
	private Integer payType; 
	
	private Double amount;
	
	private Date flowTime;
	
	private	Integer status;
	
	private String goodCode;
	
	private String goodName;
	
	private Integer goodNumber;
	
	private String custName;
	
	private String custHeadUrl;
	
	private	Integer custLevel;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public String getFlowDate() {
		return FlowDate;
	}
	public void setFlowDate(String flowDate) {
		FlowDate = flowDate;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getFlowCode() {
		return flowCode;
	}
	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getFlowTime() {
		return flowTime;
	}
	public void setFlowTime(Date flowTime) {
		this.flowTime = flowTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getGoodCode() {
		return goodCode;
	}
	public void setGoodCode(String goodCode) {
		this.goodCode = goodCode;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public Integer getGoodNumber() {
		return goodNumber;
	}
	public void setGoodNumber(Integer goodNumber) {
		this.goodNumber = goodNumber;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustHeadUrl() {
		return custHeadUrl;
	}
	public void setCustHeadUrl(String custHeadUrl) {
		this.custHeadUrl = custHeadUrl;
	}
	public Integer getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(Integer custLevel) {
		this.custLevel = custLevel;
	}
}
