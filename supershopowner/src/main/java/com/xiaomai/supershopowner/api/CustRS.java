package com.xiaomai.supershopowner.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.BizErr;
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
	
	@RequestMapping(value="/findCust" , method = RequestMethod.POST)	
	public @ResponseBody String getfindCustComingTime(@RequestBody Cust cust){
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		
		map.put("storeCode",cust.getStoreCode());
		map.put("comingTime", formatter.format(cust.getComingTime()));
		try {
			List<Cust> custList = custService.getfindComingTime(map);
			
			result.setCode("200");
			result.setMsg("Suscces");
			result.setResult(custList);
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
