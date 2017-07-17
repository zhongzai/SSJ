package com.xiaomai.supershopowner.dao;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Cust;

@Repository
public class CustDao extends BaseDaoImpl<Cust, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.CustMapper";
	}
}
