package com.xiaomai.supershopowner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sun.webkit.WebPage;
import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.dao.DayCategoryDao;
import com.xiaomai.supershopowner.entity.DayCategory;

@Service
public class DayCategoryService implements BaseService<DayCategory, Integer>{
	@Resource
	public DayCategoryDao dayCategoryDao;
	private org.slf4j.Logger log = LoggerFactory.getLogger(DayCategory.class);
	
	SimpleDateFormat formatt = new SimpleDateFormat("YYYY-MM-dd");
	
	public List<DayCategory> getDayCategory(Map<String , Object> map){
		List<DayCategory> list = new ArrayList<>();
		try {
			list = dayCategoryDao.findListAllWithMap(map);
		} catch (Exception e) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return list;
	}

	@Override
	public Integer insert(DayCategory t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(DayCategory t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DayCategory findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DayCategory> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DayCategory> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}
}
