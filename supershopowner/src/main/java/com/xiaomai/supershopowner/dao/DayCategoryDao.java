package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.DayCategory;

@Repository
public class DayCategoryDao extends BaseDaoImpl<DayCategory, Integer> {

	@Override
	public String getNameSpace() {
	
		return "mappers.DayCategoryMapper";
	}
	
	public DayCategory findBestGcate(Map<String, Object> map)throws SQLException{
		return selectOne(getNameSpace()+".findBestGcate",map);
	}

}
