package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Sale;
@Repository
public class SalesDao extends BaseDaoImpl<Sale, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.SaleMapper";
	}
	
	public Sale findSaleWithMap(Map<String, Object> map)throws SQLException{
		return selectOne(getNameSpace()+".findSaleWithMap",map);
	}
}
