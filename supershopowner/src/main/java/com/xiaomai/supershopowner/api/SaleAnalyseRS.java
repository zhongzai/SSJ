package com.xiaomai.supershopowner.api;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

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

/**
 * 
 * @author 叩学聪
 * @version 日销售额分析表
 * @return 返回日销售额信息表数据
 * 根据销售时间查询 (saleAnalyse.getSalesDate())
 * 和门店唯一标识查询(saleAnalyse.getStoreCode())
 */
@Controller
@RequestMapping(value="/saleAnalyse")
public class SaleAnalyseRS extends BaseRS{
	@Autowired
	public SaleAnalyseService saleAnalyseService;
	
	@RequestMapping(value="/findSaleAnalyse",method = RequestMethod.POST)
	public @ResponseBody String getSaleAnalyseByStoreCode(@Context HttpHeaders headers,@RequestBody SaleAnalyse saleAnalyse){
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		map.put("storeCode", saleAnalyse.getStoreCode());
		
		map.put("salesDate", formatter.format(saleAnalyse.getSalesDate()));
		
		try{
			List<SaleAnalyse> saleAnalyseList = saleAnalyseService.findByStoreCode(map);
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
