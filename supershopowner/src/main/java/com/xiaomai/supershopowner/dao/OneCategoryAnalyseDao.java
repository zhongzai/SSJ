package com.xiaomai.supershopowner.dao;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.OneCategoryAnalyse;

@Repository
public class OneCategoryAnalyseDao extends BaseDaoImpl<OneCategoryAnalyse, Integer>{

	@Override
	public String getNameSpace() {
	
		return "mappers.OneCategoryAnalyseMapper";
	}

}
