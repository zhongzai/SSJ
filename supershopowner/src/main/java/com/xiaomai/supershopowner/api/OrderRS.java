package com.xiaomai.supershopowner.api;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.imxiaomai.shop.web.superStoreDubbo.domain.BaseDto;
import com.imxiaomai.shop.web.superStoreDubbo.domain.GoodsCategory;
import com.imxiaomai.shop.web.superStoreDubbo.domain.GoodsInfoDto;
import com.imxiaomai.shop.web.superStoreDubbo.domain.Pager;
import com.imxiaomai.shop.web.superStoreDubbo.domain.SuperPurchaseOrder;
import com.imxiaomai.shop.web.superStoreDubbo.domain.SuperPurchaseOrderItemsRsp;
import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.GoodQR;
import com.xiaomai.supershopowner.entity.Goods;
import com.xiaomai.supershopowner.entity.GoodsInfoDtoTransfer;
import com.xiaomai.supershopowner.entity.Order;
import com.xiaomai.supershopowner.entity.OrderTransfer;
import com.xiaomai.supershopowner.entity.SuperPurchaseOrderItemsRspTransfer;
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
		BaseDto updateBaseDto = null;

		try {
			if (res == true) {
				log.debug("call the orderRS");
				updateBaseDto = superStoreService.saveStorePurchaseInfo(order.getStorePurchaseGoodItems());
				rr.setCode("200");
				rr.setMsg("添加订单成功");
				rr.setResult(updateBaseDto);
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

	// 查询所有的订单
	@RequestMapping(value = "findAllOrders", method = RequestMethod.POST)
	public String findAllOrders(
			@RequestParam(value = "storeCode", required = false) String storeCode) {
		RSResult rr = new RSResult();
		DecimalFormat df = new DecimalFormat("0.00");
		Pager<SuperPurchaseOrder> spo = null;
		List<SuperPurchaseOrder> spsList = new ArrayList<SuperPurchaseOrder>();
		int totalOrderNumber=0;
		int totalActualNumber=0;
		Double totalOrderValue=0.0;
		Double totalActualVal=0.0;
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				
				log.debug("call the findAllOrdersRS");
				spo = superStoreService.selectPurchaseOrder(storeCode, null==request.getHeader("pageNum")?1:Integer.valueOf(request.getHeader("pageNum")), null==request.getHeader("pageSize")?10:Integer.valueOf(request.getHeader("pageSize")));
				
				for(SuperPurchaseOrder sp:spo.getResult()){
					List<SuperPurchaseOrderItemsRsp> spoirs = superStoreService.getPurchaseOrderItemRspList(sp.getOrderCode());
					
					for(SuperPurchaseOrderItemsRsp spoir:spoirs){
						totalOrderNumber+=(null==spoir.getOrderNumber()?0:spoir.getOrderNumber());
						totalActualNumber+=(null==spoir.getReceiveNumber()?0:spoir.getReceiveNumber());
						
						totalOrderValue += spoir.getPurcorderGoodsPrice().
								multiply(new BigDecimal(Double.toString(Double.parseDouble(String.valueOf((null==spoir.getOrderNumber()?0:spoir.getOrderNumber())))))).doubleValue();
						totalActualVal += spoir.getPurcorderGoodsPrice().
								multiply(new BigDecimal(Double.toString(Double.parseDouble(String.valueOf((null==spoir.getReceiveNumber()?0:spoir.getReceiveNumber())))))).doubleValue();
					}
					sp.setActualNumber(String.valueOf(totalActualNumber));
					sp.setOrderNumber(String.valueOf(totalOrderNumber));
					sp.setReceiveTotalAmount(String.valueOf(df.format(totalActualVal)));
					sp.setOrderTotalAmount(String.valueOf(df.format(totalOrderValue)));
					spsList.add(sp);
				}
				spo.setResult(spsList);
				rr.setCode("200");
				rr.setMsg("查询订单成功");
				rr.setResult(spo);
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
			@RequestParam(value = "orderCode", required = true) String orderCode,
			@RequestParam(value = "storeCode", required = true) String storeCode) {
		DecimalFormat df = new DecimalFormat("0.00");
		RSResult rr = new RSResult();
		Double totalOrderValue=0.0;
		OrderTransfer otf = new OrderTransfer();
		List<SuperPurchaseOrderItemsRspTransfer> sL = new ArrayList<SuperPurchaseOrderItemsRspTransfer>();
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the findOrderGoods");
				List<SuperPurchaseOrderItemsRsp> spoirs = superStoreService.getPurchaseOrderItemRspList(orderCode);
				
				for(SuperPurchaseOrderItemsRsp spoir:spoirs){
					totalOrderValue += spoir.getPurcorderGoodsPrice().
							multiply(new BigDecimal(Double.toString(Double.parseDouble(String.valueOf((null==spoir.getOrderNumber()?0:spoir.getOrderNumber())))))).doubleValue();
					
					SuperPurchaseOrderItemsRspTransfer spf = new SuperPurchaseOrderItemsRspTransfer();
					spf.setGoodsCode(spoir.getGoodsCode());
					spf.setGoodsName(spoir.getGoodsName());
					spf.setOrderNumber(spoir.getOrderNumber());
					spf.setPurcorderGoodsPrice(spoir.getPurcorderGoodsPrice());
					spf.setReceiveNumber(spoir.getReceiveNumber());
					spf.setUnit(spoir.getUnit());
					
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("storeCode", storeCode);
					map.put("goodsCode", spoir.getGoodsCode());
					Goods g = goodsService.findLatestGoods(map);
					if(null!=g){
						spf.setWeekSales(String.valueOf(g.getWeekSales()));
					}else{
						spf.setWeekSales("0");
					}
					sL.add(spf);
				}
				otf.setSpoirt(sL);
				otf.setTotalOrderValue(String.valueOf(df.format(totalOrderValue)));
				rr.setCode("200");
				rr.setMsg("查询订单成功");
				rr.setResult(otf);
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

	//完成收货
	@RequestMapping(value = "updateOrder", method = RequestMethod.POST)
	public String updateOrder(@RequestBody Order o2g) {
		RSResult rr = new RSResult();
		BaseDto updateBaseDto = null;
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the update orderRs starting...");
				
				rr.setCode("200");
				rr.setMsg("收货成功");
				rr.setResult(updateBaseDto);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		} catch (Exception e) {
			log.error("call update OrderRS failure", e);
			rr.setCode("400");
			rr.setMsg("收货失败");
			rr.setResult(null);
		}

		return JSONObject.fromObject(rr).toString();

	}


	// 扫描二维码获取商品信息

	@RequestMapping(value = "findGoodByQR", method = RequestMethod.POST)
	public String findGoodByQR(
			@RequestParam(value = "sku", required = false) String sku,
			@RequestParam(value = "storeCode", required = true) String storeCode,
			@RequestParam(value = "goodsCode", required = false) String goodsCode) {
		RSResult rr = new RSResult();
		HashMap<String, Object> map = super.getQueryMap();
		map.put("storeCode", storeCode);

		GoodQR gor = new GoodQR();
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the findGoodByQR starting...");
				if(null!=sku){
					GoodsInfoDto gid = superStoreService.getGoodsInfoBySku(sku);
					map.put("goodsCode", gid.getGoodsCode());
					gor.setGoodsInforDto(gid);
					List<Goods> gs = goodsService.findGoods(map);
					gor.setGs(gs);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					map.put("nowDate", sdf.format(new Date()));
					List<WeekSales> ws = weekSalesService.findWeekSales(map);
					gor.setWs(ws);
				}else{
					map.put("goodsCode", goodsCode);
					List<Goods> gs = goodsService.findGoods(map);
					gor.setGs(gs);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					map.put("nowDate", sdf.format(new Date()));
					List<WeekSales> ws = weekSalesService.findWeekSales(map);
					gor.setWs(ws);
				}
				
				
				rr.setCode("200");
				rr.setMsg("扫描二维码获取商品信息成功");
				rr.setResult(gor);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
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
	
	//获取所有的一级类目
	@RequestMapping(value="findAllOneItems")
	public String findAllOneItems(@RequestParam(value="levelNumber",required=false) String levelNumber){
		RSResult rr = new RSResult();
		Map<String,String> map = new HashMap<String,String>();
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the findGoodByQR starting...");
				GoodsCategory gcy = new GoodsCategory();
				gcy.setGcateLevelnumber(Integer.valueOf(levelNumber));
				
				List<GoodsCategory> gcs = superStoreService.getGoodscategory(gcy);
				for(GoodsCategory gcay:gcs){
					map.put(gcay.getGcateCode(), gcay.getGcateName());
				}
				rr.setCode("200");
				rr.setMsg("获取一级目录成功");
				rr.setResult(gcs);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		} catch (Exception e) {
			log.error("called findGoodByQR failure", e);
			rr.setCode("400");
			rr.setMsg("获取一级目录失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr, JSONObjectConfig.getInstance())
				.toString();
	}
	
	//获取所有一级类目下的所有的二级类目
	@RequestMapping(value="findAllTwoItems")
	public String findAllTwoItems(@RequestParam(value="gcateParentCode",required=false) String gcateParentCode){
		RSResult rr = new RSResult();
		
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the findGoodByQR starting...");
				GoodsCategory gcy = new GoodsCategory();
				gcy.setGcateParentCode(gcateParentCode);
				
				List<GoodsCategory> gcs = superStoreService.getGoodscategory(gcy);
				rr.setCode("200");
				rr.setMsg("获取特定的一级下的所有二级目录成功");
				rr.setResult(gcs);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		} catch (Exception e) {
			log.error("called findGoodByQR failure", e);
			rr.setCode("400");
			rr.setMsg("获取特定的一级下的所有二级目录失败");
			rr.setResult(null);
		}
		
		return JSONObject.fromObject(rr, JSONObjectConfig.getInstance())
				.toString();
	}
	//根据类目查询所有的商品
	@RequestMapping(value="findAllGoods")
	public String findAllGoods(@RequestParam(value="shopCode",required=false) String shopCode,@RequestParam(value="typeCode",required=false) String typeCode){
		RSResult rr = new RSResult();
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("storeCode", shopCode);
		List<GoodsInfoDtoTransfer> gidts= new ArrayList<GoodsInfoDtoTransfer>();
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the findAllGoods starting...");
				
				
				
				List<GoodsInfoDto> gids = superStoreService.getGoodsInfoByType(shopCode, typeCode);
				
				for(GoodsInfoDto gid:gids){
					map.put("goodsCode", gid.getGoodsCode());
					
					
					List<Goods> gs = goodsService.findGoods(map);
					GoodsInfoDtoTransfer gidt = new GoodsInfoDtoTransfer();
					gs.forEach((v)->{
						gidt.setGoods(v);
					});
					gidt.setShelfLife(gid.getShelfLife());
					gidt.setCoefficien(gid.getCoefficien());
					gidt.setGoodsCode(gid.getGoodsCode());
					gidt.setGoodsName(gid.getGoodsName());
					gidt.setPrice(gid.getPrice());
					
					gidts.add(gidt);
				}
				rr.setCode("200");
				rr.setMsg("获取类目下所有的商品成功");
				rr.setResult(gidts);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		} catch (Exception e) {
			log.error("called findAllGoods failure", e);
			rr.setCode("400");
			rr.setMsg("获取类目下所有的商品失败");
			rr.setResult(null);
		}
		
		return JSONObject.fromObject(rr, JSONObjectConfig.getInstance())
				.toString();
	}
	

}
