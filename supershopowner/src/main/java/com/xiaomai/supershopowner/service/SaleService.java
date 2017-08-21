package com.xiaomai.supershopowner.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.xiaomai.supershopowner.dao.SaleAnalyseDao;
import com.xiaomai.supershopowner.dao.SalesDao;
import com.xiaomai.supershopowner.entity.DayCategory;
import com.xiaomai.supershopowner.entity.OneCategoryAnalyse;
import com.xiaomai.supershopowner.entity.Sale;
import com.xiaomai.supershopowner.entity.SaleAnalyse;
import com.xiaomai.supershopowner.entity.SalesTranfer;

@Service
public class SaleService implements BaseService<Sale, Integer>{
	@Resource
	public SalesDao saleDao;
	
	@Resource
	public DayCategoryDao dayCategoryDao;
	
	@Resource
	public SaleAnalyseDao saleAnalyseDao;
	
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
					sales.setBestGoodsName(oneCategoryAnalyse.getGoodName());
				}	
				if(formatter.format(new Date()).equals(formatter.format(sales.getSalesDate()))){
					Calendar   cal   =   Calendar.getInstance();
					cal.add(Calendar.DATE,   -7);
					
					String lastWeek = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
					
/*					String lastWeekNow = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss").format(cal.getTime());
*/					
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("salesDate", lastWeek);
					map2.put("storeCode", sales.getStoreCode());
					
					//根据日期查询上周今日的数据
					Sale sale = saleDao.findSaleWithMap(map2);
					
					Map<String, Object> map3 = new HashMap<String, Object>();
					map3.put("startTime", new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime()));
					map3.put("endTime",  new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
					map3.put("storeCode", sales.getStoreCode());
					//根据日期查询上周此时的数据
					List<SaleAnalyse> saleAnalyseList = saleAnalyseDao.findNowSales(map3);
					Double salesTotal =(double) 0;
					Integer custNumber =0;
					if(saleAnalyseList.size()!=0){
						for(SaleAnalyse saleAnalyse : saleAnalyseList){
							salesTotal+=(saleAnalyse.getSalesAmount()==null)?0:(saleAnalyse.getSalesAmount());
							custNumber+=(saleAnalyse.getCustNumber()==null)?0:saleAnalyse.getCustNumber();
						}
					}
					//升降标识
					//根据日期查询昨天的销售信息
					Calendar   cal1   =   Calendar.getInstance();
					cal1.add(Calendar.DATE,   -1);
					String yes = new SimpleDateFormat( "yyyy-MM-dd ").format(cal1.getTime());
					Map<String, Object> map4 = new HashMap<String, Object>();
					map4.put("salesDate", yes);
					map4.put("storeCode", sales.getStoreCode());
					
					Sale yesSale = saleDao.findSaleWithMap(map4);
					
					sales.setTodayCustUpdown((sales.getCustNumber()>(yesSale==null?0:yesSale.getCustNumber())?"up":"down"));//0降，1升
					sales.setTodaySalesUpdown((sales.getSalesTotal()>(yesSale==null?0:yesSale.getSalesTotal())?"up":"down"));
					sales.setProfitUpdown((sales.getProfit()>(yesSale==null?0:yesSale.getProfit())?"up":"down"));
					sales.setLossUpdown((sales.getLoss()>(yesSale==null?0:yesSale.getLoss())?"up":"down"));
					sales.setAveragePriceUpdown((sales.getAveragePrice()>(yesSale==null?0:yesSale.getAveragePrice())?"up":"down"));
					sales.setCustWeekNow(custNumber);
					sales.setSalesWeekNow(salesTotal);
					sales.setCustWeekToday((sale==null)?0:sale.getCustNumber());
					sales.setSalesWeekToday((sale==null)?0:sale.getSalesTotal());
					salesTranfer.setTodaySales(sales);
				}else{
					//根据日期查询前天销售信息
					Calendar   cal2   =   Calendar.getInstance();
					cal2.add(Calendar.DATE,   -2);
					String last = new SimpleDateFormat( "yyyy-MM-dd ").format(cal2.getTime());
					
					Map<String, Object> map5 = new HashMap<String, Object>();
					map5.put("salesDate", last);
					map5.put("storeCode", sales.getStoreCode());
					
					Sale lastSale = saleDao.findSaleWithMap(map5);
					
					sales.setYesCustUpdown(sales.getCustNumber()>(lastSale==null?0:lastSale.getCustNumber())?"up":"down");
					sales.setYesSalesUpdown(sales.getSalesTotal()>(lastSale==null?0:lastSale.getSalesTotal())?"up":"down");
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
