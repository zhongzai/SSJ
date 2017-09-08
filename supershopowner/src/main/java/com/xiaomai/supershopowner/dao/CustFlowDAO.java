package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.CustFlow;

@Repository
public class CustFlowDAO extends BaseDaoImpl<CustFlow, Integer>{

	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "mappers.CustFlowMapper";
		
	}
	public List<CustFlow> findGoodsList(HashMap<String, Object> map)throws SQLException{
		return selectList(getNameSpace()+".findGoodsList",map);
	}
}
