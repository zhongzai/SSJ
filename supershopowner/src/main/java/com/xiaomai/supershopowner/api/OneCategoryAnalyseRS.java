package com.xiaomai.supershopowner.api;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

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
import com.xiaomai.supershopowner.entity.OneCategoryAnalyse;
import com.xiaomai.supershopowner.service.OneCategoryAnalyseService;

import net.sf.json.JSONObject;

/**
 * 
 * @author 叩学聪
 * @version 单品分析表
 * @return 返回单品分析信息表数据
 * 根据销售时间查询 (oneCategoryAnalyse.getSalesDate())
 * 和门店唯一标识查询(oneCategoryAnalyse.getStoreCode())
 */
@Controller
@RequestMapping(value="/oneCategoryAnalyse")
public class OneCategoryAnalyseRS extends BaseRS{
	@Autowired
	public OneCategoryAnalyseService oneCategoryAnalyseService;
	@Autowired
	protected CheckToken checkToken;
	/**
     * 获取单品分析接口
     * 
     */
	@RequestMapping(value="/getOneCategoryAnalyse" ,method = RequestMethod.POST)
	public @ResponseBody String getOneCategoryAnalyse(HttpServletRequest request,@RequestBody OneCategoryAnalyse oneCategoryAnalyse){
		RSResult result = new RSResult();
		HashMap<String,Object> map = super.getQueryMap();
		SimpleDateFormat formatt = new SimpleDateFormat("YYYY-MM-dd");
		map.put("storeCode", oneCategoryAnalyse.getStoreCode());
		map.put("salesDate", formatt.format(oneCategoryAnalyse.getSalesDate()));
		map.put("type", oneCategoryAnalyse.getType());
		try {
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
			List<OneCategoryAnalyse> list = oneCategoryAnalyseService.getOneCategoryAnalyse(map);
			result.setCode("200");
			result.setMsg("Suscces");
			result.setResult(list);
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
