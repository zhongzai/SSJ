package com.xiaomai.supershopowner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.common.WebPage;
import com.xiaomai.supershopowner.dao.OneCategoryAnalyseDao;
import com.xiaomai.supershopowner.entity.Cust;
import com.xiaomai.supershopowner.entity.OneCategoryAnalyse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OneCategoryAnalyseService implements BaseService<OneCategoryAnalyse, Integer>{
	@Resource
	public OneCategoryAnalyseDao OneCategoryAnalyseDao;
	
	private org.slf4j.Logger log = LoggerFactory.getLogger(Cust.class);
	
	public List<OneCategoryAnalyse> getOneCategoryAnalyse(Map<String,Object> map){
		SimpleDateFormat formatt = new SimpleDateFormat("YYYY-MM-dd");
		List<OneCategoryAnalyse> list = new ArrayList<>();
		try {
			list = OneCategoryAnalyseDao.findListAllWithMap(map);
			for(OneCategoryAnalyse oneCategoryAnalyses : list){
				oneCategoryAnalyses.setSalesTime(formatt.format(oneCategoryAnalyses.getSalesDate()));
			}
		} catch (Exception e) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return list;
	}

	@Override
	public Integer insert(OneCategoryAnalyse t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(OneCategoryAnalyse t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OneCategoryAnalyse findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OneCategoryAnalyse> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebPage<OneCategoryAnalyse> findPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OneCategoryAnalyse> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}
}
