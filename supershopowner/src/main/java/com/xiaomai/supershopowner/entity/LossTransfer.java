package com.xiaomai.supershopowner.entity;

import java.util.List;

import com.imxiaomai.shop.web.superStoreDubbo.domain.LossReportDetailDto;
import com.imxiaomai.shop.web.superStoreDubbo.domain.LossReportDto;

public class LossTransfer {
	
	private List<LossReportDetailDto> lossReportDetailDto;
	private String lossTotalAmount;
	
	private List<LossReportDto> lossReportDto;

	public String getLossTotalAmount() {
		return lossTotalAmount;
	}

	public void setLossTotalAmount(String lossTotalAmount) {
		this.lossTotalAmount = lossTotalAmount;
	}

	public List<LossReportDetailDto> getLossReportDetailDto() {
		return lossReportDetailDto;
	}

	public void setLossReportDetailDto(List<LossReportDetailDto> lossReportDetailDto) {
		this.lossReportDetailDto = lossReportDetailDto;
	}

	public List<LossReportDto> getLossReportDto() {
		return lossReportDto;
	}

	public void setLossReportDto(List<LossReportDto> lossReportDto) {
		this.lossReportDto = lossReportDto;
	}
}
