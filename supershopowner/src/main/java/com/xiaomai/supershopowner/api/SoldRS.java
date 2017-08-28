package com.xiaomai.supershopowner.api;
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

import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Goods;
import com.xiaomai.supershopowner.entity.UnSoldTranfer;
import com.xiaomai.supershopowner.service.GoodsService;

@Controller
@RequestMapping(value="/sold")
public class SoldRS extends BaseRS{
	@Autowired
	public GoodsService goodsService;
	@Autowired
	protected CheckToken checkToken;
	/**
     * 滞销商品接口
     * 
     */
	@RequestMapping(value="/unSoldList",method = RequestMethod.POST)
	public @ResponseBody String list(HttpServletRequest request,@RequestBody UnSoldTranfer unSoldTranfer){
		
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		map.put("storeCode", unSoldTranfer.getStoreCode());
		map.put("days", unSoldTranfer.getDays());
		
		
		try{ 
			Boolean res=checkToken.check(request.getHeader("token"));
			
			if(res==true){
			List<Goods> goodsList = goodsService.findGoods(map);
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(goodsList);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
				result.setResult(null);
			}
		}catch(Exception ex){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
     * 售罄商品接口接口
     * 
     */
	@RequestMapping(value="/soldOutList",method = RequestMethod.POST)
	public @ResponseBody String soldOut(HttpServletRequest request,@RequestBody UnSoldTranfer unSoldTranfer){
		
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		map.put("storeCode", unSoldTranfer.getStoreCode());
		
		try{ 
			Boolean res=checkToken.check(request.getHeader("token"));
			if(res==true){
				
			List<Goods> list = goodsService.findSoldOutList(map);
				
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(list);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
				result.setResult(null);
			}
		}catch(Exception ex){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result).toString();
	}
}
