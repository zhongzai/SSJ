package com.xiaomai.supershopowner.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sun.webkit.WebPage;
import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.dao.CustFlowDAO;
import com.xiaomai.supershopowner.entity.CustFlow;

@Service
public class CustFlowService implements BaseService<CustFlow, Integer>{
	
	@Resource
	public CustFlowDAO custFlowDao;
	
	public List<CustFlow> getCustFlow(HashMap<String,Object> map){
		List<CustFlow> list =new ArrayList<>();
		try {
			if(list.size()!=0){
				list = custFlowDao.findList(map);
			}
		} catch (Exception e) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return list;
	}

	@Override
	public Integer insert(CustFlow t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(CustFlow t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustFlow findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustFlow> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustFlow> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
