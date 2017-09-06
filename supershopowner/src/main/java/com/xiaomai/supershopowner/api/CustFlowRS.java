package com.xiaomai.supershopowner.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.CustFlow;
import com.xiaomai.supershopowner.service.CustFlowService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/custFlow")
public class CustFlowRS extends BaseRS{
	@Autowired
	public CustFlowService custFlowService;
	@Autowired
	protected CheckToken checkToken;
	/**
	 * 
	 * @param custFlow
	 * @return 根据日期查询到的销售数据
	 */
	@RequestMapping(value="/getCustFlow",method = RequestMethod.POST)
	public @ResponseBody String getCustFlow(HttpServletRequest request,@RequestBody CustFlow custFlow){
		RSResult result = new RSResult();
		SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd ");
		HashMap<String,Object> map = super.getQueryMap();
		map.put("storeCode", custFlow.getStoreCode());
		map.put("flowTime",formatt.format(custFlow.getFlowTime()));
		try {
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
			List<CustFlow> newList= new ArrayList<>();	
				
			List<CustFlow> custList = custFlowService.getCustFlow(map);
			Double totalAmount=0.00;
			if(custList.size()!=0){
				for(CustFlow flow : custList){
					totalAmount+=(flow.getAmount()==null?0.00:flow.getAmount());
					
					flow.setTotalAmount(totalAmount);
					newList.add(flow);
				}
			}
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(newList);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
				result.setResult(null);
			}
		} catch (Exception e) {
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result,JSONObjectConfig.getTime()).toString();
	}
}
