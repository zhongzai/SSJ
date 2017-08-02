package com.xiaomai.supershopowner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.common.WebPage;
import com.xiaomai.supershopowner.dao.LossDao;
import com.xiaomai.supershopowner.dao.SalesDao;
import com.xiaomai.supershopowner.entity.Loss;
import com.xiaomai.supershopowner.entity.Sale;
import com.xiaomai.supershopowner.entity.YesterdaySales;

@Service
public class YesterdaySalesService implements BaseService<YesterdaySales, Integer>{
	@Resource
	public SalesDao salesDao;
	@Resource
	public LossDao lossdao;
	
	
	
	public YesterdaySales getYesterdaySales(Map<String , Object> map){
		SimpleDateFormat formatt = new SimpleDateFormat("YYYY-MM-dd");
		List<Loss> lossList = new ArrayList<>();
		Sale sale = new Sale();
		YesterdaySales yds = new YesterdaySales();
		try {
			lossList  = lossdao.findListAllWithMap(map);
			sale = salesDao.findWithMap(map);
			yds.setSale(sale);
		} catch (Exception e) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return yds;
	}
	@Override
	public Integer insert(YesterdaySales t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(YesterdaySales t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public YesterdaySales findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YesterdaySales> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebPage<YesterdaySales> findPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<YesterdaySales> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}
}
