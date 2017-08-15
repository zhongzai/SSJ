package com.xiaomai.supershopowner.api;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.WeekSales;
import com.xiaomai.supershopowner.service.WeekSalesService;

@RestController
@RequestMapping(value = "/WeekSales")
public class WeekSalesRS extends BaseRS{

	
	private org.slf4j.Logger log = LoggerFactory.getLogger(WeekSalesRS.class);
	@Autowired
	WeekSalesService weekSalesService;
	
	@Autowired
	protected CheckToken checkToken;

	// 查询7天销售
	@RequestMapping(value = "findWeekSales", method = RequestMethod.POST)
	public String findWeekSales(
			@RequestParam(value = "nowDate", required = false) String nowDate,
			@RequestParam(value = "storeCode", required = false) String storeCode) {
		RSResult rr = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		map.put("nowDate", nowDate);
		map.put("storeCode", storeCode);
		List<WeekSales> ws = null;
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the findWeekSales");
				ws = weekSalesService.findWeekSales(map);
				rr.setCode("200");
				rr.setMsg("查询查询7天销售成功");
				rr.setResult(ws);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		} catch (Exception e) {
			log.error("called findWeekSales", e);
			rr.setCode("400");
			rr.setMsg("查询查询7天销售失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr, JSONObjectConfig.getInstance())
				.toString();
	}

}
