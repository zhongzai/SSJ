package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Account;
@Repository
public class AccountDao extends BaseDaoImpl<Account, Integer> {

	@Override
	public String getNameSpace() {
		return "mappers.AccountMapper";
	}
	
	public Account findByUserAccount(String  userAccount)throws SQLException{
		return selectOne(getNameSpace()+".findByUserAccount",userAccount);
	}
	
}
