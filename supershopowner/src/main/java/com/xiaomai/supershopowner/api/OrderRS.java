package com.xiaomai.supershopowner.api;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imxiaomai.shop.web.superStoreDubbo.SuperStoreService;
import com.imxiaomai.shop.web.superStoreDubbo.domain.GoodsCategory;
import com.imxiaomai.shop.web.superStoreDubbo.domain.GoodsInfoDto;
import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.GoodQR;
import com.xiaomai.supershopowner.entity.Goods;
import com.xiaomai.supershopowner.entity.Order;
import com.xiaomai.supershopowner.entity.Order2good;
import com.xiaomai.supershopowner.entity.WeekSales;
import com.xiaomai.supershopowner.service.GoodsService;
import com.xiaomai.supershopowner.service.OrderService;
import com.xiaomai.supershopowner.service.WeekSalesService;

@RestController
@RequestMapping(value = "order")
public class OrderRS extends BaseRS {
	private org.slf4j.Logger log = LoggerFactory.getLogger(OrderRS.class);

	@Autowired
	OrderService orderService;

	@Autowired
	protected CheckToken checkToken;

	@Autowired
	SuperStoreService superStoreService;

	@Autowired
	GoodsService goodsService;

	@Autowired
	WeekSalesService weekSalesService;

	// 添加订单
	@RequestMapping(value = "addorders", method = RequestMethod.POST)
	public String inserOrder(HttpServletRequest request,
			@RequestBody Order order) throws SQLException {

		RSResult rr = new RSResult();
		Boolean res = checkToken.check(request.getHeader("token"));

		try {
			if (res == true) {
				log.debug("call the orderRS");
				orderService.addOrders(order);// TODO
				rr.setCode("200");
				rr.setMsg("添加订单成功");
				rr.setResult(null);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}

		} catch (Exception e) {

			log.error("called orderRS failure", e);
			rr.setCode("400");
			rr.setMsg("添加订单失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr).toString();
	}

	// 查询所有的orders
	@RequestMapping(value = "findAllOrders", method = RequestMethod.POST)
	public String findAllOrders(
			@RequestParam(value = "storeCode", required = false) String storeCode) {
		RSResult rr = new RSResult();
		List<Order> os = null;
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the findAllOrdersRS");
				os = orderService.findAllOrders(storeCode);
				rr.setCode("200");
				rr.setMsg("查询订单成功");
				rr.setResult(os);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		} catch (SQLException e) {
			log.error("called orderRS failure", e);
			rr.setCode("400");
			rr.setMsg("查询订单失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr, JSONObjectConfig.getInstance())
				.toString();
	}

	// 查询订单详情
	@RequestMapping(value = "findOrderGoods", method = RequestMethod.POST)
	public String findOrderGoods(
			@RequestParam(value = "orderCode", required = false) String orderCode) {
		RSResult rr = new RSResult();
		List<Order2good> o2g = null;
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the findOrderGoods");
				o2g = orderService.findOrderGoods(orderCode);
				rr.setCode("200");
				rr.setMsg("查询订单成功");
				rr.setResult(o2g);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		} catch (SQLException e) {
			log.error("called orderRS failure", e);
			rr.setCode("400");
			rr.setMsg("查询订单失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr, JSONObjectConfig.getInstance())
				.toString();
	}

	// 确认订单
	@RequestMapping(value = "updateOrder", method = RequestMethod.POST)
	public String updateOrder(@RequestBody Order o2g) {
		RSResult rr = new RSResult();
		int updateId = 0;
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the update orderRs starting...");
				updateId = orderService.updateAndAffirmOrder(o2g);
				rr.setCode("200");
				rr.setMsg("更新订单成功");
				rr.setResult(updateId);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		} catch (SQLException e) {
			log.error("call update OrderRS failure", e);
			rr.setCode("400");
			rr.setMsg("更新订单失败");
			rr.setResult(null);
		}

		return JSONObject.fromObject(rr).toString();

	}

	// 给一个订单添加商品,需要调用pc端7天销售接口,需要关联损耗单
	// TODO

	// 扫描二维码获取商品信息

	@RequestMapping(value = "findGoodByQR", method = RequestMethod.POST)
	public String findGoodByQR(
			@RequestParam(value = "sku", required = true) String sku,
			@RequestParam(value = "storeCode", required = true) String storeCode) {
		RSResult rr = new RSResult();
		HashMap<String, Object> map = super.getQueryMap();
		map.put("storeCode", storeCode);

		GoodQR gor = new GoodQR();
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the findGoodByQR starting...");
				GoodsInfoDto gid = superStoreService.getGoodsInfoBySku(sku);
				map.put("goodsCode", gid.getGoodsCode());
				gor.setGoodsInforDto(gid);
				List<Goods> gs = goodsService.findGoods(map);
				gor.setGs(gs);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				map.put("nowDate", sdf.format(new Date()));
				List<WeekSales> ws = weekSalesService.findWeekSales(map);
				gor.setWs(ws);
				rr.setCode("200");
				rr.setMsg("扫描二维码获取商品信息成功");
				rr.setResult(gor);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(gor);
			}
		} catch (Exception e) {
			log.error("called findGoodByQR failure", e);
			rr.setCode("400");
			rr.setMsg("扫描二维码获取商品信息失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr, JSONObjectConfig.getInstance())
				.toString();
	}
	
	@RequestMapping(value="findAllOneItems")
	public String findAllOneItems(@RequestParam(value="levelNumber",required=false) String levelNumber){
		RSResult rr = new RSResult();
		GoodsCategory gcy = new GoodsCategory();
		gcy.setGcateLevelnumber(Integer.valueOf(levelNumber));
		
		List<GoodsCategory> gcs = superStoreService.getGoodscategory(gcy);
		for(GoodsCategory gcay:gcs){
			System.out.println(gcay.getGcateCode()+":"+gcay.getGcateName());
		}
		return JSONObject.fromObject(rr, JSONObjectConfig.getInstance())
				.toString();
	}
	
	@RequestMapping(value="findAllTwoItems")
	public String findAllTwoItems(@RequestParam(value="gcateCode",required=false) String gcateCode){
		RSResult rr = new RSResult();
		GoodsCategory gcy = new GoodsCategory();
		gcy.setGcateCode(gcateCode);
		
		List<GoodsCategory> gcs = superStoreService.getGoodscategory(gcy);
		for(GoodsCategory gcay:gcs){
			System.out.println(gcay.getSonList());
		}
		return JSONObject.fromObject(rr, JSONObjectConfig.getInstance())
				.toString();
	}

}
