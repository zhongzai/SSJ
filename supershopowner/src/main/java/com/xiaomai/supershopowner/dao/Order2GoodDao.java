package com.xiaomai.supershopowner.dao;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Order;

@Repository
public class Order2GoodDao extends BaseDaoImpl<Order,Integer>{

	@Override
	public String getNameSpace() {
		return "mappers.Order2goodMapper";
	}

}
