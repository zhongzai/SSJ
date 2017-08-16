package com.xiaomai.supershopowner.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.dao.Loss2GoodsDao;
import com.xiaomai.supershopowner.dao.LossDao;
import com.xiaomai.supershopowner.entity.Goods;
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
		log.debug("find loss detail starting...");
		List<Loss2good> l2g = null;
		try{
			l2g = loss2GoodsDao.findLossGoods(lossCode);
		}catch(SQLException ex){
			log.error("Exception: ", ex);
			throw new RuntimeException(ex);
		}
		log.debug("find loss detail end...");
		return l2g;
	}
	
	//添加损耗单
	public void addLoss(Loss loss){
		log.debug("add the loss starting...");
		Integer lossNumber= 0;
		Loss lo = new Loss();
		String lossCode=loss.getStoreCode()+System.currentTimeMillis();
		lo.setLossCode(lossCode);
		lo.setStoreCode(loss.getStoreCode());
		lo.setLossTime(new Date());
		List<Goods> gds = loss.getGoods();
		Double totalValue = 0.0;
		try{
			lossDao.insert(lo);
			for(Goods g:gds){
				Loss2good l2g = new Loss2good();
				l2g.setLossCode(lossCode);
				l2g.setLossTotal(g.getLossTotal());
				l2g.setGoodsCode(g.getGoodsCode());
				loss2GoodsDao.insert(l2g);
				lossNumber+=g.getLossTotal();
				totalValue += new BigDecimal(Double.toString(g.getPrice())).
						multiply(new BigDecimal(Double.toString(Double.parseDouble(String.valueOf(g.getLossTotal()))))).doubleValue();
			}
			lo.setLossNumber(lossNumber);
			lo.setLossValue(totalValue);
			lossDao.update(lo);
			
		}catch(SQLException ex){
			log.error("Exception: ",ex);
			throw new RuntimeException(ex);
		}
		log.debug("add the loss end...");
	}
	
	//根据good code查询损耗列表
	public List<Loss> findLossByGoodCode(String goodCode){
		log.debug("find loss by store code starting...");
		List<Loss> loss=null;
		try {
			loss = lossDao.findLossByGoodCode(goodCode);
		} catch (SQLException ex) {
			log.error("Exception: ",ex);
			throw new RuntimeException(ex);
		}
		log.debug("find loss by store code end...");
		return loss;
	}
	
	//查询每个单品里的损耗
	public List<Loss2good> findLossByOneGood(Map<String,Object> map){
		log.debug("find loss by one good starting...");
		List<Loss2good> lgs = new ArrayList<Loss2good>();
		try {
			lgs = loss2GoodsDao.findLossByOneGood(map);
		} catch (SQLException ex) {
			log.error("Exception: ",ex);
			throw new RuntimeException(ex);
		}
		log.debug("find loss by one good end");
		return lgs;
	}
	
}
