package com.xiaomai.supershopowner.entity;

import java.io.Serializable;

import com.imxiaomai.shop.web.superStoreDubbo.domain.GoodsInfoDto;

public class GoodsInfoDtoTransfer extends GoodsInfoDto implements Serializable{
	
	private static final long serialVersionUID = 2L;
	private Goods goods;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
}
