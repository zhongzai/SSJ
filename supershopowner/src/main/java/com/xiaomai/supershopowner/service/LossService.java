package com.xiaomai.supershopowner.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.dao.Loss2GoodsDao;
import com.xiaomai.supershopowner.dao.LossDao;
import com.xiaomai.supershopowner.entity.Loss;
import com.xiaomai.supershopowner.entity.Loss2good;

@Service
public class LossService{
	
	private org.slf4j.Logger log = LoggerFactory.getLogger(LossService.class);
	
	@Autowired
	LossDao lossDao;
	
	@Autowired
	Loss2GoodsDao loss2GoodsDao;
	
	
	//根据store code查询损耗列表
	public List<Loss> findLossByStoreCode(String storeCode){
		log.debug("find loss by store code starting...");
		List<Loss> loss=null;
		try {
			loss = lossDao.findLossByStoreCode(storeCode);
		} catch (SQLException ex) {
			log.error("Exception: ",ex);
			throw new RuntimeException(ex);
		}
		log.debug("find loss by store code end...");
		return loss;
	}
	
	//损耗单详情
	public List<Loss2good> findLossGoods(String lossCode){
		List<Loss2good> l2g = null;
		log.debug("find loss detail starting...");
		try{
			l2g = loss2GoodsDao.findLossGoods(lossCode);
		}catch(SQLException ex){
			log.error("Exception: ", ex);
			throw new RuntimeException(ex);
		}
		log.debug("find loss detail end...");
		return l2g;
	}
	
}
