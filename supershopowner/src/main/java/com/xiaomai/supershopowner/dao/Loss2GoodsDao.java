package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Loss2good;

@Repository
public class Loss2GoodsDao extends BaseDaoImpl<Loss2good,Integer>{

	@Override
	public String getNameSpace() {
		return "mappers.Loss2goodMapper";
	}
	
	public List<Loss2good> findLossGoods(String lossCode) throws SQLException{
		return selectList(getNameSpace()+".findLossGoods",lossCode);
	}

}
