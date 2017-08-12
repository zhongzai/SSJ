package com.xiaomai.supershopowner.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.dao.WeekSalesDao;
import com.xiaomai.supershopowner.entity.WeekSales;



@Service
public class WeekSalesService {
	private org.slf4j.Logger log = LoggerFactory.getLogger(WeekSalesService.class);
	@Autowired
	WeekSalesDao weekSalesDao;
	
	
	public List<WeekSales> findWeekSales(Map<String,Object> map){
		log.debug(" findWeekSales service starting...");
		List<WeekSales> wSales=null;
		
		try {
			wSales = weekSalesDao.findWeekSales(map);
		} catch (SQLException ex) {
			log.error("Exception: ",ex);
			throw new RuntimeException(ex);
		}
		log.debug(" findWeekSales service end");
		return wSales;
		
	}

}
