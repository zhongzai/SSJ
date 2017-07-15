package com.xiaomai.supershopowner.dao;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Sale;
@Repository
public class SalesDao extends BaseDaoImpl<Sale, Integer> {

	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "mappers.SaleMapper";
	}
}
