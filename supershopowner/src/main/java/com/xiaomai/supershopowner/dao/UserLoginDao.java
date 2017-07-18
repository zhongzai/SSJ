package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.UserLogin;
@Repository
public class UserLoginDao extends BaseDaoImpl<UserLogin, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.UserLoginMapper";
	}
	public UserLogin findByStoreCode(String storeCode)throws SQLException{
		return selectOne(getNameSpace()+".findByStoreCode",storeCode);
	}
}
