package com.xiaomai.supershopowner.entity;

import java.util.List;

public class OrderTransfer {

	private List<SuperPurchaseOrderItemsRspTransfer> spoirt;
	
	private String totalOrderValue;

	public List<SuperPurchaseOrderItemsRspTransfer> getSpoirt() {
		return spoirt;
	}

	public void setSpoirt(List<SuperPurchaseOrderItemsRspTransfer> spoirt) {
		this.spoirt = spoirt;
	}

	public String getTotalOrderValue() {
		return totalOrderValue;
	}

	public void setTotalOrderValue(String totalOrderValue) {
		this.totalOrderValue = totalOrderValue;
	}
	
}
