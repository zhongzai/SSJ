package com.xiaomai.supershopowner.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import org.apache.http.HttpHeaders;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Cust;
import com.xiaomai.supershopowner.service.CustService;
/**
 * 
 * @author 叩学聪
 * @version 日来客分析表
 * @return 返回日来客信息表所有数据
 * 根据购买日期(cust.getComingTime())
 * 和门店唯一标识查询(cust.getStoreCode())
 */
@Controller
@RequestMapping(value="/cust")
public class CustRS extends BaseRS{
	@Autowired
	public CustService custService;
	@Autowired
	protected CheckToken checkToken;
	
	@RequestMapping(value="/findCust" , method = RequestMethod.POST)	
	public @ResponseBody String getfindCustComingTime(HttpServletRequest request,@RequestBody Cust cust){
		String token = request.getHeader("token");
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		
		map.put("storeCode",cust.getStoreCode());
		map.put("comingTime", formatter.format(cust.getComingTime()));
		try {
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
			List<Cust> custList = custService.getfindComingTime(map);
			result.setCode("200");
			result.setMsg("Suscces");
			result.setResult(custList);
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
		return JSONObject.fromObject(result).toString();
	}
}
