package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.User;


@Repository
public class UserDao extends BaseDaoImpl<User, Integer> {

	@Override
	public String getNameSpace() {
		return "sqlmap.User";
	}
	
	public User findUserByName(String userName) throws SQLException{
    	return selectOne(getNameSpace() + ".findUserByName",userName);
    }

}
