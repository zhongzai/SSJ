package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Loss;
import com.xiaomai.supershopowner.entity.YesterdaySales;

@Repository
public class LossDao extends BaseDaoImpl<Loss,Integer> {

	@Override
	public String getNameSpace() {
		
		return "mappers.LossMapper";
	}
	public Loss findWithMap(Map<String ,Object> map) throws SQLException{
		return selectOne(getNameSpace()+".findWithMap",map);
	}
}
