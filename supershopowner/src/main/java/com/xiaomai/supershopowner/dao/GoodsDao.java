package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Goods;
@Repository
public class GoodsDao extends BaseDaoImpl<Goods, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.GoodsMapper";
	}
	
	public List<Goods> findSoldOutList(String  sotreCode)throws SQLException{
		return selectList(getNameSpace()+".findSoldOutList",sotreCode);
	}
}
