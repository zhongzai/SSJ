package com.xiaomai.supershopowner.api;

import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Goods;
import com.xiaomai.supershopowner.entity.Loss;
import com.xiaomai.supershopowner.entity.Loss2good;
import com.xiaomai.supershopowner.service.LossService;


@RestController
@RequestMapping(value="loss")
public class LossRS extends BaseRS {
	
	private org.slf4j.Logger log = LoggerFactory.getLogger(OrderRS.class);
	
	@Autowired
	LossService lossService;
	
	@Autowired
	protected CheckToken checkToken;
	
	//损耗单列表
	@RequestMapping(value="getLossByStoreCode",method=RequestMethod.POST)
	public String getLossByStoreCode(@RequestParam(value="storeCode",required=false) String storeCode){
		RSResult rr = new RSResult();
		List<Loss> loss = null;
		Boolean res;
		try{
			res = checkToken.check(request.getHeader("token"));
			if(res==true){
				log.debug("called getLossByStoreCode starting...");
				loss = lossService.findLossByStoreCode(storeCode);
				rr.setCode("200");
				rr.setMsg("查询损耗单列表成功");
				rr.setResult(loss);
			}else{
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		}catch(Exception e){
			log.error("called getLossByStoreCode failure", e);
			rr.setCode("400");
			rr.setMsg("查询损耗单列表失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr).toString();
	}
	
	//损耗单详情
	@RequestMapping(value="getLossGoods",method=RequestMethod.POST)
	public String getLossGoods(@RequestParam(value="lossCode",required=false) String lossCode){
		RSResult rr = new RSResult();
		List<Loss2good> loss2Good = null;
		Boolean res;
		try{
			res = checkToken.check(request.getHeader("token"));
			if(res == true){
				log.debug("called getLossGoods starting...");
				loss2Good = lossService.findLossGoods(lossCode);
				rr.setCode("200");
				rr.setMsg("查询损耗详情成功");
				rr.setResult(loss2Good);
			}else{
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		}catch(Exception e){
			log.error("called getLossGoods failure", e);
			rr.setCode("400");
			rr.setMsg("查询损耗详情失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr).toString();
	}
	
	
	//新建损耗单
	@RequestMapping(value="addloss",method=RequestMethod.POST)
	public String inserLoss(@RequestBody Loss loss){
		RSResult rr = new RSResult();
		Boolean res;
		try{
			res = checkToken.check(request.getHeader("token"));
			if(res == true){
				log.debug("called inser loss starting...");
				lossService.addLoss(loss);
				rr.setCode("200");
				rr.setMsg("插入损耗单成功");
				rr.setResult(1);
			}else{
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		}catch(Exception e){
			log.error("called inser loss failure",e);
			rr.setCode("400");
			rr.setMsg("增加损耗单失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr).toString();
	}
}
