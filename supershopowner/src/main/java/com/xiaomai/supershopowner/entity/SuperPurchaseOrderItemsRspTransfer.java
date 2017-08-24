package com.xiaomai.supershopowner.entity;

import com.imxiaomai.shop.web.superStoreDubbo.domain.SuperPurchaseOrderItemsRsp;

public class SuperPurchaseOrderItemsRspTransfer{

	private Goods goods;
	
	private SuperPurchaseOrderItemsRsp superPurchaseOrderItemsRsp;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public SuperPurchaseOrderItemsRsp getSuperPurchaseOrderItemsRsp() {
		return superPurchaseOrderItemsRsp;
	}

	public void setSuperPurchaseOrderItemsRsp(
			SuperPurchaseOrderItemsRsp superPurchaseOrderItemsRsp) {
		this.superPurchaseOrderItemsRsp = superPurchaseOrderItemsRsp;
	}
}
