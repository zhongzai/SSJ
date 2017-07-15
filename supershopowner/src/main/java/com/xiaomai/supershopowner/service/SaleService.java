package com.xiaomai.supershopowner.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.common.WebPage;
import com.xiaomai.supershopowner.dao.SalesDao;
import com.xiaomai.supershopowner.entity.Sale;
import com.xiaomai.supershopowner.entity.SalesTranfer;

@Service
public class SaleService implements BaseService<Sale, Integer>{
	@Resource
	public SalesDao saleDao;
	private org.slf4j.Logger log = LoggerFactory.getLogger(SaleService.class);
	public SalesTranfer findSales(Map<String, Object> map){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		SalesTranfer salesTranfer = new SalesTranfer();
		try {
			List<Sale> list= saleDao.findListAllWithMap(map);
			
			for(Sale sales : list){
				if(formatter.format(new Date()).equals(formatter.format(sales.getSalesDate()))){
					salesTranfer.setTodaySales(sales);
				}else{
					salesTranfer.setYestodaySales(sales);
				}
			}
		} catch (Exception e) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return salesTranfer;
	}
	
	@Override
	public Integer insert(Sale t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer update(Sale t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Sale findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Sale> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public WebPage<Sale> findPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Sale> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}
}
