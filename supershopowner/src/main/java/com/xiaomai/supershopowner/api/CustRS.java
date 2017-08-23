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

import com.imxiaomai.shop.web.superStoreDubbo.SuperStoreService;
import com.imxiaomai.shop.web.superStoreDubbo.domain.MemberDto;
import com.imxiaomai.shop.web.superStoreDubbo.domain.Pager;
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
	@Autowired
	public SuperStoreService superStoreService;
	
	@RequestMapping(value="/findCust" , method = RequestMethod.POST)	
	public @ResponseBody String getfindCustComingTime(HttpServletRequest request,@RequestBody Cust cust){
		
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		
		map.put("storeCode",cust.getStoreCode());
		map.put("comingTime", formatter.format(cust.getComingTime()));
		map.put("type", cust.getType());
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
	public @ResponseBody String getfindCustComingTime(HttpServletRequest request,@RequestBody String storeCode,int curentPage,int pageSize){
		RSResult result = new RSResult();
	
		try {
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
			Pager<MemberDto> pager = superStoreService.getMembersByStoreCode(storeCode, curentPage, pageSize);
			result.setCode("200");
			result.setMsg("Suscces");
			result.setResult(pager.getResult());
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
		return JSONObject.fromObject(result).toString();
	}

}
