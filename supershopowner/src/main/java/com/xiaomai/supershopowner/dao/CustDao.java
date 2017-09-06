package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Cust;

@Repository
public class CustDao extends BaseDaoImpl<Cust, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.CustMapper";
	}
	
	public Integer findAllListCount(HashMap<String, Object> map) throws SQLException {
		return ((Number) selectOne(getNameSpace()+".findAllListCount",map)).intValue();
	}
	
	public List<Cust> findAllList(HashMap<String, Object> map)throws SQLException{
		return selectList(getNameSpace()+".findAllList",map);
	}
}
