package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Order2good;

@Repository
public class Order2GoodDao extends BaseDaoImpl<Order2good,Integer>{

	@Override
	public String getNameSpace() {
		return "mappers.Order2goodMapper";
	}
	
	public List<Order2good> findGoodsByOrderCode(String orderCode) throws SQLException{
		return selectList(getNameSpace()+".findOrderGoods",orderCode);
	}
	

}
