package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Loss;


@Repository
public class LossDao extends BaseDaoImpl<Loss,Integer>{

	@Override
	public String getNameSpace() {
		return "mappers.LossMapper";
	}
	
	public List<Loss> findLossByStoreCode(String storeCode) throws SQLException{
		return selectList(getNameSpace()+".findByStoreCode",storeCode);
	}
	
	public List<Loss> findLossByGoodCode(String goodCode) throws SQLException{
		return selectList(getNameSpace()+".findByGoodCode",goodCode);
	}
}
