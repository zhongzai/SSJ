package com.xiaomai.supershopowner.entity;

import java.util.List;

import com.imxiaomai.shop.web.superStoreDubbo.domain.LossReportDetailDto;

public class LossTransfer {
	
	private List<LossReportDetailDto> lossReportDetailDto;

	public List<LossReportDetailDto> getLossReportDetailDto() {
		return lossReportDetailDto;
	}

	public void setLossReportDetailDto(List<LossReportDetailDto> lossReportDetailDto) {
		this.lossReportDetailDto = lossReportDetailDto;
	}
	
}
