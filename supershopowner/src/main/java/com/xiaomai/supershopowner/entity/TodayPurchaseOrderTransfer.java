package com.xiaomai.supershopowner.entity;

import com.imxiaomai.shop.web.superStoreDubbo.domain.TodayPurchaseOrder;

public class TodayPurchaseOrderTransfer extends TodayPurchaseOrder{

	private String inventory;
	private String weekSales;
	private String logisticsType;//配送类型
	private String dayDistribution;//是否日配
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	
	public String getLogisticsType() {
		return logisticsType;
	}
	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
	}
	public String getDayDistribution() {
		return dayDistribution;
	}
	public void setDayDistribution(String dayDistribution) {
		this.dayDistribution = dayDistribution;
	}
	public String getWeekSales() {
		return weekSales;
	}
	public void setWeekSales(String weekSales) {
		this.weekSales = weekSales;
	}
	
}
