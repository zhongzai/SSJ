package com.xiaomai.supershopowner.entity;

import java.util.List;

import com.imxiaomai.shop.web.superStoreDubbo.domain.GoodsInfoDto;

public class GoodQR {
	
	private List<Goods> gs;
	
	private List<WeekSales> ws;
	
	private GoodsInfoDto goodsInforDto;

	
	public List<Goods> getGs() {
		return gs;
	}

	public void setGs(List<Goods> gs) {
		this.gs = gs;
	}

	public List<WeekSales> getWs() {
		return ws;
	}

	public void setWs(List<WeekSales> ws) {
		this.ws = ws;
	}

	public GoodsInfoDto getGoodsInforDto() {
		return goodsInforDto;
	}

	public void setGoodsInforDto(GoodsInfoDto goodsInforDto) {
		this.goodsInforDto = goodsInforDto;
	}
}
