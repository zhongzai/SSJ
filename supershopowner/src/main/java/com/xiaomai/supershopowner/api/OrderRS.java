package com.xiaomai.supershopowner.api;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Goods;
import com.xiaomai.supershopowner.service.OrderService;

@RestController
@RequestMapping(value="order")
public class OrderRS extends BaseRS{
	private org.slf4j.Logger log = LoggerFactory.getLogger(OrderRS.class);
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	protected CheckToken checkToken;
	
	@RequestMapping(value="addorders", method = RequestMethod.POST)
	public String inserOrder(HttpServletRequest request,@RequestBody List<Goods> goods ) throws SQLException{
		
	
		RSResult rr = new RSResult();
		Boolean res=checkToken.check(request.getHeader("token"));
		
		try{
			if(res==true){
				log.debug("call the orderRS");
				orderService.addOrders(goods);
				rr.setCode("200");
				rr.setMsg("添加订单成功");
				rr.setResult(null);
			}else{
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
			
		}catch(Exception e){
			
			log.error("called orderRS failure",e);
			rr.setCode("400");
			rr.setMsg("添加订单失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr).toString();
	}
	
	
}
