package com.xiaomai.supershopowner.entity;

import java.util.List;

import com.imxiaomai.shop.web.superStoreDubbo.domain.SuperPurchaseOrderItemsRsp;

public class ReceiveGoods {
	
	private String purcorderCode;   //订单号
	private String userId;         //创建人id
	private String userName;		//创建人名字
	private String shopCode;		//门店编号
	private String shopName;		//门店名字
	private Integer purchaseType;	//供货方类型
	private String supplyCommodityId;   //供货方id
	private String supplyCommodityName; //供货方名字
	private String outBizCode; //动作序号，用于去重

	private List<SuperPurchaseOrderItemsRsp> superPurchaseOrderItemsRspL;//商品列表

	public String getPurcorderCode() {
		return purcorderCode;
	}

	public void setPurcorderCode(String purcorderCode) {
		this.purcorderCode = purcorderCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getSupplyCommodityId() {
		return supplyCommodityId;
	}

	public void setSupplyCommodityId(String supplyCommodityId) {
		this.supplyCommodityId = supplyCommodityId;
	}

	public String getSupplyCommodityName() {
		return supplyCommodityName;
	}

	public void setSupplyCommodityName(String supplyCommodityName) {
		this.supplyCommodityName = supplyCommodityName;
	}

	public String getOutBizCode() {
		return outBizCode;
	}

	public void setOutBizCode(String outBizCode) {
		this.outBizCode = outBizCode;
	}

	public List<SuperPurchaseOrderItemsRsp> getSuperPurchaseOrderItemsRspL() {
		return superPurchaseOrderItemsRspL;
	}

	public void setSuperPurchaseOrderItemsRspL(
			List<SuperPurchaseOrderItemsRsp> superPurchaseOrderItemsRspL) {
		this.superPurchaseOrderItemsRspL = superPurchaseOrderItemsRspL;
	}
	
}
