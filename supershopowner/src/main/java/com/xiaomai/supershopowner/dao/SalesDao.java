package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Sale;
@Repository
public class SalesDao extends BaseDaoImpl<Sale, Integer> {

	@Override
	public String getNameSpace() {
		// TODO Auto-generated method stub
		return "mappers.SaleMapper";
	}
}
