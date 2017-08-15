package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.OneCategoryAnalyse;

@Repository
public class OneCategoryAnalyseDao extends BaseDaoImpl<OneCategoryAnalyse, Integer>{

	@Override
	public String getNameSpace() {
	
		return "mappers.OneCategoryAnalyseMapper";
	}

	public OneCategoryAnalyse findBestGcate(Map<String, Object> map)throws SQLException{
		return selectOne(getNameSpace()+".findBestGcate",map);
	}
}
