package com.xiaomai.supershopowner.service;

import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.dao.StorageDao;
import com.xiaomai.supershopowner.entity.Storage;
import com.xiaomai.supershopowner.entity.StorageTransfer;


@Service
public class StorageService {

	private org.slf4j.Logger log = LoggerFactory.getLogger(StorageService.class);
	
	@Autowired
	StorageDao storageDao;
	
	public StorageTransfer findStorageByCode(String storeCode){
		log.debug("find storage by code starting...");
		StorageTransfer storageTransfer= new StorageTransfer();
		try {
			Storage storage = storageDao.getStorageByCode(storeCode);
			
			storageTransfer.setCategoryNumber(storage==null?null:storage.getCategoryNumber());
			storageTransfer.setGoodsNumber(storage==null?null:storage.getGoodsNumber());
			storageTransfer.setTotalLoss(storage==null?null:storage.getTotalLoss());
			storageTransfer.setTotalValue(storage==null?null:storage.getTotalValue());
			storageTransfer.setStoreCode(storeCode);
			
		} catch (SQLException ex) {
			log.error("exception:", ex);
			throw new RuntimeException(ex); 
		}
		log.debug("find storage by code end...");
		return storageTransfer;
	}
	
	
	
	
}
