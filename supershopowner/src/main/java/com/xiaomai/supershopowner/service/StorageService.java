package com.xiaomai.supershopowner.service;

import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.dao.StorageDao;
import com.xiaomai.supershopowner.entity.Storage;


@Service
public class StorageService {

	private org.slf4j.Logger log = LoggerFactory.getLogger(StorageService.class);
	
	@Autowired
	StorageDao storageDao;
	
	public Storage findStorageByCode(String storeCode){
		log.debug("find storage by code starting...");
		Storage storage=null;
		try {
			storage = storageDao.getStorageByCode(storeCode);
		} catch (SQLException ex) {
			log.error("exception:", ex);
			throw new RuntimeException(ex); 
		}
		log.debug("find storage by code end...");
		return storage;
	}
	
	
	
	
}
