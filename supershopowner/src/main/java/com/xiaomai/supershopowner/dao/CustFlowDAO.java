package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.xiaomai.supershopowner.entity.CustFlow;

@Repository
public class CustFlowDAO extends BaseDaoImpl<CustFlow, Integer>{

	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "mappers.CustFlowMapper";
		
	}
	public List<CustFlow> findByCustFlow(Map<String, Object> map)throws SQLException{
		return selectList(getNameSpace()+".findByCustFlow",map);
	}

}
