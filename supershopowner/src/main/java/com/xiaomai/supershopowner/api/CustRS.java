package com.xiaomai.supershopowner.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imxiaomai.member.service.OpenService;
import com.imxiaomai.shop.web.superStoreDubbo.SuperStoreService;
import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Cust;
import com.xiaomai.supershopowner.entity.CustTransfer;
import com.xiaomai.supershopowner.entity.TagTransfer;
import com.xiaomai.supershopowner.service.CustService;

import net.sf.json.JSONObject;
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
	@Autowired
	public SuperStoreService superStoreService;
	@Resource
	public OpenService openService;
	
	@RequestMapping(value="/findCust" , method = RequestMethod.POST)	
	public @ResponseBody String getfindCustComingTime(HttpServletRequest request,@RequestBody Cust cust){
		
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		
		map.put("storeCode",cust.getStoreCode());
		map.put("comingTime", formatter.format(cust.getComingTime()));
		map.put("type", cust.getType());
		CustTransfer custTransfer = new CustTransfer();
		try {
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
			custTransfer = custService.getfindComingTime(map);
			result.setCode("200");
			result.setMsg("Suscces");
			result.setResult(custTransfer);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
				result.setResult(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result,JSONObjectConfig.getTime()).toString();
	}
	/**
	 * 编辑顾客标签信息
	 * @return 
	 */
	@RequestMapping(value="/addOrUpdate" , method = RequestMethod.POST)	
	public @ResponseBody String addOrUpdate(HttpServletRequest request,@RequestBody TagTransfer tagTransfer){
		
		RSResult result = new RSResult();
		
		try {
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
				boolean tag= openService.saveOrUpdateMemberTag(tagTransfer.getTagList(), tagTransfer.getCustId());
			result.setCode("200");
			result.setMsg("Suscces");
			result.setResult(tag);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
				result.setResult(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/**
	 * 查询顾客信息
	 * @param request 客户信息
	 * @param storeCode 门店唯一标识
	 * @param curentPage 页数
	 * @param pageSize 显示数据条数
	 * @return 
	 */
	@RequestMapping(value="/findMemberDto" , method = RequestMethod.POST)	
	public @ResponseBody String getfindCustList(HttpServletRequest request,
			@RequestParam(value = "storeCode", required = false) String storeCode,
			@RequestParam(value = "findType", required = false) String findType,
			@RequestParam(value = "dayType", required = false) String dayType){
		RSResult result = new RSResult();
		
		CustTransfer custTransfer = new CustTransfer();
		try {
			
			HashMap<String, Object> map =super.getQueryMap();
			
			map.put("storeCode",storeCode);
			map.put("findType",findType);
			
	        if("30".equals(dayType)){
	        	Calendar   cal   =   Calendar.getInstance();
				cal.add(Calendar.DATE,   -30);
	        	map.put("startDate",new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime()));
	        	map.put("endDate",new SimpleDateFormat( "yyyy-MM-dd ").format(new Date()));
	        }
	        
	        if("7".equals(dayType)){
	        	Calendar   cal   =   Calendar.getInstance();
				cal.add(Calendar.DATE,   -7);
	        	map.put("startDate",new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime()));
	        	map.put("endDate",new SimpleDateFormat( "yyyy-MM-dd ").format(new Date()));
	        }
	        if("1".equals(dayType)){
	        	map.put("day",new SimpleDateFormat( "yyyy-MM-dd ").format(new Date()));
	        }
			
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
				custTransfer=custService.findAllList(map);
			result.setCode("200");
			result.setMsg("Suscces");
			result.setResult(custTransfer);
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
