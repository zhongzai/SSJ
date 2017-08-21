package com.xiaomai.supershopowner.entity;

import java.math.BigDecimal;

public class RechargeTransfer {
	private String memberMobile;//会员手机号
    private BigDecimal rechargeAmount;//充值金额
    private Integer payType;//支付方式(0:现金 1:微信 2:支付宝 3:校园卡 4:余额)
    private String storeCode;//门店唯一标识
	public String getMemberMobile() {
		return memberMobile;
	}
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

    
}