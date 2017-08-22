package com.xiaomai.supershopowner.dao;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Goods;
@Repository
public class GoodsDao extends BaseDaoImpl<Goods, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.GoodsMapper";
	}
	
}
