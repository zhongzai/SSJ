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

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.CustFlow;
import com.xiaomai.supershopowner.service.CustFlowService;

@Controller
@RequestMapping(value="/CustFlow")
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
		SimpleDateFormat formatt = new SimpleDateFormat();
		HashMap<String,Object> map = super.getQueryMap();
		map.put("storeCode", custFlow.getStoreCode());
		map.put("flowTime",formatt.format(custFlow.getFlowTime()));
		try {
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
			List<CustFlow> articleList = custFlowService.getCustFlow(map);
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(articleList);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
				result.setResult(null);
			}
		} catch (Exception e) {
			if(BizErr.EX_UPDATE_FAIL.equals(e.getMessage())){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
			}
		}
		return JSONObject.fromObject(result,JSONObjectConfig.getTime()).toString();
	}
}
