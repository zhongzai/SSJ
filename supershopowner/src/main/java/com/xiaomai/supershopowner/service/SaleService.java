package com.xiaomai.supershopowner.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.common.WebPage;
import com.xiaomai.supershopowner.dao.DayCategoryDao;
import com.xiaomai.supershopowner.dao.OneCategoryAnalyseDao;
import com.xiaomai.supershopowner.dao.SalesDao;
import com.xiaomai.supershopowner.entity.DayCategory;
import com.xiaomai.supershopowner.entity.OneCategoryAnalyse;
import com.xiaomai.supershopowner.entity.Sale;
import com.xiaomai.supershopowner.entity.SalesTranfer;

@Service
public class SaleService implements BaseService<Sale, Integer>{
	@Resource
	public SalesDao saleDao;
	
	@Resource
	public DayCategoryDao dayCategoryDao;
	
	@Resource
	public OneCategoryAnalyseDao oneCategoryAnalyseDao;
	private org.slf4j.Logger log = LoggerFactory.getLogger(SaleService.class);
	public SalesTranfer findSales(Map<String, Object> map){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		SalesTranfer salesTranfer = new SalesTranfer();
		try {
			List<Sale> list= saleDao.findListAllWithMap(map);
			
			for(Sale sales : list){
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("salesDate", formatter.format(sales.getSalesDate()));
				map1.put("storeCode", sales.getStoreCode());
				
				//根据时间和门店Code查询当天最佳品类信息
				DayCategory dayCategory = dayCategoryDao.findBestGcate(map1);
				
				//根据时间和门店Code查询当天最佳单品信息
				OneCategoryAnalyse oneCategoryAnalyse = oneCategoryAnalyseDao.findBestGcate(map1);
				if(dayCategory!=null){
					sales.setBestCategoryName(dayCategory.getCategoryName());
				}
				if(oneCategoryAnalyse!=null){
					sales.setBestGoodsName(oneCategoryAnalyse.getCategoryName());
				}	
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
