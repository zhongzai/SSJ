package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.WeekSales;

@Repository
public class WeekSalesDao extends BaseDaoImpl<WeekSales, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.WeekSales";
	}
	
	public List<WeekSales> findWeekSales(Map<String, Object> map) throws SQLException{
		return selectList(getNameSpace()+".findWeekSales",map);
	}

}
