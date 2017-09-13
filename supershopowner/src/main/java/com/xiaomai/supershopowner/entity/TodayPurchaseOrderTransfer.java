package com.xiaomai.supershopowner.entity;

import com.imxiaomai.shop.web.superStoreDubbo.domain.TodayPurchaseOrder;

public class TodayPurchaseOrderTransfer extends TodayPurchaseOrder{

	private String inventory;
	private String weekSales;
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public String getWeekSales() {
		return weekSales;
	}
	public void setWeekSales(String weekSales) {
		this.weekSales = weekSales;
	}
}
