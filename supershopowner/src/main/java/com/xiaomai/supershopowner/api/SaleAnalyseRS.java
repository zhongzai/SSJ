package com.xiaomai.supershopowner.api;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.SaleAnalyse;
import com.xiaomai.supershopowner.service.SaleAnalyseService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/user")
public class SaleAnalyseRS extends BaseRS{
	@Autowired
	public SaleAnalyseService saleAnalyseService;
	
	@RequestMapping(value="/findSaleAnalyse",method = RequestMethod.POST)
	public @ResponseBody String getSaleAnalyseByStoreCode(@RequestBody SaleAnalyse saleAnalyse){
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		
		map.put("storeCode", saleAnalyse.getStoreCode());
		map.put("salesDate", saleAnalyse.getSalesTime());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			
			List<SaleAnalyse> saleAnalyseList = saleAnalyseService.findByStoreCode(map);
			for(SaleAnalyse saleAnalyses : saleAnalyseList){
				
				saleAnalyses.setSalesTime(formatter.format(saleAnalyses.getSalesDate()));
				
			}
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(saleAnalyseList);
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
