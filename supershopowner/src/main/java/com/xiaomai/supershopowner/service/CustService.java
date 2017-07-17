package com.xiaomai.supershopowner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;


import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.common.WebPage;
import com.xiaomai.supershopowner.dao.CustDao;
import com.xiaomai.supershopowner.entity.Cust;

@Service
public class CustService implements BaseService<Cust, Integer>{
	@Resource
	public CustDao custDao;
	private org.slf4j.Logger log = LoggerFactory.getLogger(Cust.class);
	public List<Cust> getfindComingTime(Map<String,Object> map){
		List<Cust> list = new ArrayList<>();
		try {
			list = custDao.findListAllWithMap(map);
		} catch (Exception e) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return list;
	}
	@Override
	public Integer insert(Cust t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Cust t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cust findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cust> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebPage<Cust> findPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cust> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
