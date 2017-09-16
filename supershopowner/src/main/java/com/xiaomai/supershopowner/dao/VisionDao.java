package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Vision;
@Repository
public class VisionDao extends BaseDaoImpl<Vision, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.VisionMapper";
	}
	
	public Vision findNewVision(String visonCode)throws SQLException{
		return selectOne(getNameSpace()+".findNewVision",visonCode);
	}
}
