package com.xiaomai.supershopowner.api;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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
import com.xiaomai.supershopowner.entity.Sale;
import com.xiaomai.supershopowner.entity.YesterdaySales;
import com.xiaomai.supershopowner.service.YesterdaySalesService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/YesterdaySales")
public class YesterdaySaleRS extends BaseRS{
	@Autowired
	public YesterdaySalesService yesterdaySalesService;
	@Autowired
	protected CheckToken checkToken;
	
	/**
	 * 查询昨日数据 
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getYesterdaySales",method = RequestMethod.POST)
	public @ResponseBody String getYesterdaySales(HttpServletRequest request,@RequestBody Sale sale){
		SimpleDateFormat formatt = new SimpleDateFormat("YYYY-MM-dd");
		RSResult result = new RSResult();
		HashMap<String , Object> map = super.getQueryMap();
		YesterdaySales yds = new YesterdaySales();
		map.put("storeCode",sale.getStoreCode());

		map.put("salesDate",formatt.format(sale.getSalesDate()));
	
		try {
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
			yds= yesterdaySalesService.getYesterdaySales(map);
			result.setCode("200");
			result.setMsg("Suscces");
			result.setResult(yds);
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
