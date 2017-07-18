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
import com.xiaomai.supershopowner.entity.DayCategory;

import com.xiaomai.supershopowner.service.DayCategoryService;

import net.sf.json.JSONObject;
/**
 * 
 * @author 叩学聪
 * @version 日品分析表
 * @return 返回日品分析信息表数据
 * 根据销售时间查询 (dayCategory.getSalesDate())
 * 和门店唯一标识查询(dayCategory.getStoreCode())
 */
@Controller
@RequestMapping(value="/dayCategory")
public class DayCategoryRS extends BaseRS{

	@Autowired
	public DayCategoryService dayCategoryService;
	
	@RequestMapping(value="/findDayCategory" , method = RequestMethod.POST)
	public @ResponseBody String getDayCategory(@Context HttpHeaders headers,@RequestBody DayCategory dayCategory){
		RSResult result = new RSResult();
		HashMap<String,Object> map = super.getQueryMap();
		
		SimpleDateFormat formatt = new SimpleDateFormat("YYYY-MM-dd");
		
		map.put("storeCode", dayCategory.getStoreCode());
		
		map.put("salesDate", formatt.format(dayCategory.getSalesDate()));
		
		try {
			List<DayCategory> dayCategoryList = dayCategoryService.getDayCategory(map);
	
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(dayCategoryList);
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
