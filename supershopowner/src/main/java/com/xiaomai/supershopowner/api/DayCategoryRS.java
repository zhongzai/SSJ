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
import com.xiaomai.supershopowner.entity.DayCategory;

import com.xiaomai.supershopowner.service.DayCategoryService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/DayCategory")
public class DayCategoryRS extends BaseRS{

	@Autowired
	public DayCategoryService dayCategoryService;
	
	@RequestMapping(value="/findDayCategory" , method = RequestMethod.POST)
	public @ResponseBody String getDayCategory(@RequestBody DayCategory dayCategory){
		RSResult result = new RSResult();
		HashMap<String,Object> map = super.getQueryMap();
		
		SimpleDateFormat formatt = new SimpleDateFormat("YYYY-MM-dd");
		map.put("storeCode", dayCategory.getStoreCode());
		map.put("salesDate", formatt.format(dayCategory.getStoreCode()));
		try {
			List<DayCategory> dayCategoryList = dayCategoryService.getDayCategory(map);
			for(DayCategory dayCategorys : dayCategoryList){
				
				dayCategorys.setSalesTime(formatt.format(dayCategorys.getSalesDate()));
				
			}
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
