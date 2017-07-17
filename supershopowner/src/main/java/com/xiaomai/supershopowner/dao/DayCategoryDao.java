package com.xiaomai.supershopowner.dao;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.DayCategory;

@Repository
public class DayCategoryDao extends BaseDaoImpl<DayCategory, Integer> {

	@Override
	public String getNameSpace() {
	
		return "mappers.DayCategoryMapper";
	}

}
