package com.xiaomai.supershopowner.api;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Sale;
import com.xiaomai.supershopowner.entity.SalesTranfer;
import com.xiaomai.supershopowner.service.SaleService;

@Controller
@RequestMapping(value="/sales")
public class SaleRS extends BaseRS{
	@Autowired
	public SaleService saleService;
	
	@RequestMapping(value="/findSales",method = RequestMethod.POST)
	public @ResponseBody String getSaleAnalyseByStoreCode(@RequestBody Sale sale){
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
			if(BizErr.EX_UPDATE_FAIL.equals(ex.getMessage())){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
			}
		}
		return JSONObject.fromObject(result).toString();
	}
}
