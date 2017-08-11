package com.xiaomai.supershopowner.dao;

import com.xiaomai.supershopowner.entity.WeekSales;

public class WeekSalesDao extends BaseDaoImpl<WeekSales, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.WeekSales";
	}

}
