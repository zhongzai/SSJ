package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.SalesTotalTranfer;
@Repository
public class SalesTotalDao extends BaseDaoImpl<SalesTotalTranfer, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.SaleTotalMapper";
	}
	
	public SalesTotalTranfer findSaleTotal(String storeCode)throws SQLException{
		return selectOne(getNameSpace()+".findSaleTotal",storeCode);
	}
}
