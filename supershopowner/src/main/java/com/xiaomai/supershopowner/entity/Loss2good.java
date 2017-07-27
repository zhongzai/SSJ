package com.xiaomai.supershopowner.entity;

public class Loss2good extends Goods{
    private Integer id;

    private String goodsCode;

    private String lossCode;
    
    private Integer lossTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getLossCode() {
        return lossCode;
    }

    public void setLossCode(String lossCode) {
        this.lossCode = lossCode == null ? null : lossCode.trim();
    }

	public Integer getLossTotal() {
		return lossTotal;
	}

	public void setLossTotal(Integer lossTotal) {
		this.lossTotal = lossTotal;
	}
}