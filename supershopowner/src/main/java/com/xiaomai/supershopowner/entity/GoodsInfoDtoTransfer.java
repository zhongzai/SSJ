package com.xiaomai.supershopowner.entity;

import java.io.Serializable;

import com.imxiaomai.shop.web.superStoreDubbo.domain.GoodsInfoDto;

public class GoodsInfoDtoTransfer extends GoodsInfoDto implements Serializable{
	
	private static final long serialVersionUID = 2L;
	private String weekSales;
	private String monthSales;
	private String monthProvide;
	public String getWeekSales() {
		return weekSales;
	}
	public void setWeekSales(String weekSales) {
		this.weekSales = weekSales;
	}
	public String getMonthSales() {
		return monthSales;
	}
	public void setMonthSales(String monthSales) {
		this.monthSales = monthSales;
	}
	public String getMonthProvide() {
		return monthProvide;
	}
	public void setMonthProvide(String monthProvide) {
		this.monthProvide = monthProvide;
	}
	
}
