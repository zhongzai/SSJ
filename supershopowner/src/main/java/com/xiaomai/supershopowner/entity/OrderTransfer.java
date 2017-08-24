package com.xiaomai.supershopowner.entity;

import java.util.List;

public class OrderTransfer {

	private List<SuperPurchaseOrderItemsRspTransfer> spoirt;
	
	private String orderTotalAmount;//订单总金额
	private String orderNumber;   //应收总数量
	private String actualNumber;   //实收总数量
	private String receiveTotalAmount;  //收货总金额

	public List<SuperPurchaseOrderItemsRspTransfer> getSpoirt() {
		return spoirt;
	}

	public void setSpoirt(List<SuperPurchaseOrderItemsRspTransfer> spoirt) {
		this.spoirt = spoirt;
	}

	public String getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(String orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getActualNumber() {
		return actualNumber;
	}

	public void setActualNumber(String actualNumber) {
		this.actualNumber = actualNumber;
	}

	public String getReceiveTotalAmount() {
		return receiveTotalAmount;
	}

	public void setReceiveTotalAmount(String receiveTotalAmount) {
		this.receiveTotalAmount = receiveTotalAmount;
	}
	
}
