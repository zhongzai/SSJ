package com.xiaomai.supershopowner.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Storage;


@Repository
public class StorageDao extends BaseDaoImpl<Storage,Integer>{

	@Override
	public String getNameSpace() {
		return "mappers.StorageMapper";
	}
	
	public Storage getStorageByCode(String storeCode) throws SQLException{
		return selectOne(getNameSpace()+".findByStoreCode",storeCode);
	}

}
