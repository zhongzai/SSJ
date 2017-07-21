package com.xiaomai.supershopowner.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.common.WebPage;
import com.xiaomai.supershopowner.dao.GoodsDao;
import com.xiaomai.supershopowner.entity.Goods;

@Service
public class GoodsService implements BaseService<Goods, Integer>{
	@Resource
	public GoodsDao goodsDao;
	
	public List<Goods> findGoods(Map<String, Object> map){
		
		List<Goods> list = null;
		try {
			 list= goodsDao.findListAllWithMap(map);
		} catch (Exception e) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return list;
	}
	
    public List<Goods> findSoldOutList(String storeCode){
		
		List<Goods> list = null;
		try {
			 list= goodsDao.findSoldOutList(storeCode);
		} catch (Exception e) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return list;
	}
	
	@Override
	public Integer insert(Goods t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer update(Goods t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Goods findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Goods> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public WebPage<Goods> findPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Goods> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}
}
