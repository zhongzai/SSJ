package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.SaleAnalyse;
@Repository
public class SaleAnalyseDao extends BaseDaoImpl<SaleAnalyse, Integer> {

	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "mappers.SaleAnalyseMapper";
	}
	public List<SaleAnalyse> findByStoreCode(Map<String, Object> map)throws SQLException{
		return selectList(getNameSpace()+".findByStoreCode",map);
	}
}