package com.xiaomai.supershopowner.api;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.SaleAnalyse;
import com.xiaomai.supershopowner.service.SaleAnalyseService;

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
	@Autowired
	protected CheckToken checkToken;
	
	/**
	 * 查询日销售交易流水所有数据
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/findSaleAnalyse",method = RequestMethod.POST)
	public @ResponseBody String getSaleAnalyseByStoreCode(HttpServletRequest request,@RequestBody SaleAnalyse saleAnalyse){
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		map.put("storeCode", saleAnalyse.getStoreCode());
		
		map.put("salesDate", formatter.format(saleAnalyse.getSalesDate()));
		
		try{
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
			List<SaleAnalyse> saleAnalyseList = saleAnalyseService.findByStoreCode(map);
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(saleAnalyseList);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
				result.setResult(null);
			}
		}catch(Exception ex){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result,JSONObjectConfig.getTime()).toString();
	}
	
	/**
	 * 查询日销售交易流水的占比 单独实现(柱状图)
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getSaleAnalyseByProportion",method = RequestMethod.POST)
	public @ResponseBody String getSaleAnalyseByProportion( @RequestBody SaleAnalyse saleAnalyse){
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		map.put("storeCode", saleAnalyse.getStoreCode());
		
		map.put("salesDate", formatter.format(saleAnalyse.getSalesDate()));
		try{
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
			List<SaleAnalyse> saleAnalyseList =  saleAnalyseService.findByStorePeriod(map);
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(saleAnalyseList);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
				result.setResult(null);
			}
		}catch(Exception ex){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return 	JSONObject.fromObject(result).toString();
	}
}
