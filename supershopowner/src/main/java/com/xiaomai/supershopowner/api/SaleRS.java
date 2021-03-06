package com.xiaomai.supershopowner.api;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import net.sf.json.JSONObject;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Sale;
import com.xiaomai.supershopowner.entity.SalesTotalTranfer;
import com.xiaomai.supershopowner.entity.SalesTranfer;
import com.xiaomai.supershopowner.service.SaleService;

@Controller
@RequestMapping(value="/sales")
public class SaleRS extends BaseRS{
	@Autowired
	public SaleService saleService;
	/**
     * 门店销售数据接口
     * 
     */
	@RequestMapping(value="/findSales",method = RequestMethod.POST)
	public @ResponseBody String getSaleAnalyseByStoreCode(@Context HttpHeaders headers ,@RequestBody Sale sale){
		RSResult result = new RSResult();
		
		HashMap<String, Object> map =super.getQueryMap();
		
		Calendar   cal   =   Calendar.getInstance();
		cal.add(Calendar.DATE,   -1);
		
		String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
		  
		map.put("storeCode", sale.getStoreCode());
		map.put("startDate", yesterday);
		map.put("endDate", new SimpleDateFormat( "yyyy-MM-dd ").format(new Date()));
		try{ 
			SalesTranfer sales = saleService.findSales(map);
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(sales);
		}catch(Exception ex){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result,JSONObjectConfig.getInstance()).toString();
	}
	
	/**
     * 门店销售统计接口
     * 
     */
	@RequestMapping(value="/salesTotal",method = RequestMethod.POST)
	public @ResponseBody String salesTotal(HttpServletRequest request ,@RequestParam(value="storeCode",required=false) String storeCode){
		RSResult result = new RSResult();
		
		try{ 
			SalesTotalTranfer sales = saleService.findTotalSales(storeCode);
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(sales);
		}catch(Exception ex){
			ex.printStackTrace();
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result,JSONObjectConfig.getInstance()).toString();
	}
}
