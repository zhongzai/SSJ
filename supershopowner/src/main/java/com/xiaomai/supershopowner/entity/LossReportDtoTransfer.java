package com.xiaomai.supershopowner.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.imxiaomai.shop.web.superStoreDubbo.domain.LossReportDto;

public class LossReportDtoTransfer{
	
	private String lossCode;
	private Date lossTime;
	private Double lossTotalAmount;
	private Integer lossToatalNuber;
	
	public Double getLossTotalAmount() {
		return lossTotalAmount;
	}
	public void setLossTotalAmount(Double lossTotalAmount) {
		this.lossTotalAmount = lossTotalAmount;
	}
	public Integer getLossToatalNuber() {
		return lossToatalNuber;
	}
	public void setLossToatalNuber(Integer lossToatalNuber) {
		this.lossToatalNuber = lossToatalNuber;
	}
	public String getLossCode() {
		return lossCode;
	}
	public void setLossCode(String lossCode) {
		this.lossCode = lossCode;
	}
	public Date getLossTime() {
		return lossTime;
	}
	public void setLossTime(Date lossTime) {
		this.lossTime = lossTime;
	}
}
