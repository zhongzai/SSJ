package com.xiaomai.supershopowner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.dao.SaleAnalyseDao;
import com.xiaomai.supershopowner.entity.SaleAnalyse;

@Service
public class SaleAnalyseService implements BaseService<SaleAnalyse, Integer>{
	@Resource
	public SaleAnalyseDao saleAnalyseDao;
	private org.slf4j.Logger log = LoggerFactory.getLogger(SaleAnalyseService.class);
	public List<SaleAnalyse> findByStoreCode(Map<String, Object> map){
		List<SaleAnalyse> list =new ArrayList<>();
		try {
			 list= saleAnalyseDao.findByStoreCode(map);
		} catch (Exception e) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return list;
	}
	/**
	 * 查询交易流水的占比 单独实现
	 * @param map
	 * @return
	 */
	public List<SaleAnalyse> findByStorePeriod(Map<String, Object> map){
		List<SaleAnalyse> listSale =new ArrayList<>();
		try {
			listSale= saleAnalyseDao.findByStorPeriod(map);
		} catch (Exception e) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return listSale;
	}
	
	@Override
	public Integer insert(SaleAnalyse t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer update(SaleAnalyse t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SaleAnalyse findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SaleAnalyse> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<SaleAnalyse> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}
}
