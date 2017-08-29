package com.xiaomai.supershopowner.entity;

import com.imxiaomai.shop.web.superStoreDubbo.domain.SuperPurchaseOrderItemsRsp;

public class SuperPurchaseOrderItemsRspTransfer extends SuperPurchaseOrderItemsRsp{



	private String weekSales;
	
	private String monthSales;
	
	private String inventory;
	
	private String imageUrl;

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

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	
	
	
	
}
