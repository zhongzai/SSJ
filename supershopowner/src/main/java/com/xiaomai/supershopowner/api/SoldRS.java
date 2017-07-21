package com.xiaomai.supershopowner.api;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Context;

import net.sf.json.JSONObject;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Goods;
import com.xiaomai.supershopowner.entity.UnSoldTranfer;
import com.xiaomai.supershopowner.service.GoodsService;

@Controller
@RequestMapping(value="/sold")
public class SoldRS extends BaseRS{
	@Autowired
	public GoodsService goodsService;
	
	@RequestMapping(value="/unSoldList",method = RequestMethod.POST)
	public @ResponseBody String list(@Context HttpHeaders headers,@RequestBody UnSoldTranfer unSoldTranfer){
		
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		map.put("storeCode", unSoldTranfer.getStoreCode());
		map.put("turnoverDays", unSoldTranfer.getDays());
		
		try{ 
			List<Goods> goodsList = goodsService.findGoods(map);
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(goodsList);
		}catch(Exception ex){
			if(BizErr.EX_UPDATE_FAIL.equals(ex.getMessage())){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
			}
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/soldOutList",method = RequestMethod.POST)
	public @ResponseBody String soldOut(@Context HttpHeaders headers,@RequestBody UnSoldTranfer unSoldTranfer){
		
		RSResult result = new RSResult();
		HashMap<String, Object> map =super.getQueryMap();
		map.put("storeCode", unSoldTranfer.getStoreCode());
		
		try{ 
			List<Goods> goodsList = goodsService.findSoldOutList(unSoldTranfer.getStoreCode());
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(goodsList);
		}catch(Exception ex){
			if(BizErr.EX_UPDATE_FAIL.equals(ex.getMessage())){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
			}
		}
		return JSONObject.fromObject(result).toString();
	}
}
