package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Goods;
@Repository
public class GoodsDao extends BaseDaoImpl<Goods, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.GoodsMapper";
	}
	
	public Goods findLatestGoods(Map<String,Object> map) throws SQLException{
		
		return selectOne(getNameSpace()+".findLatestGoods",map);
	} 
	
}
