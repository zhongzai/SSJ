package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Flow;
@Repository
public class FlowDao extends BaseDaoImpl<Flow, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.FlowMapper";
	}
	
	public List<Flow> findByStoreCode(String  storeCode)throws SQLException{
		return selectList(getNameSpace()+".findByStoreCode",storeCode);
	}
	
}
