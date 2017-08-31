package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.List;
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
	
	public List<Goods> findSoldOutList(Map<String,Object> map) throws SQLException{
		
		return selectList(getNameSpace()+".findSoldOutList",map);
	} 
	public Goods findGoodLast(Map<String,Object> map) throws SQLException{
			
			return selectOne(getNameSpace()+".findGoodLast",map);
		} 
}
