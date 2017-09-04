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
import com.imxiaomai.shop.web.superStoreDubbo.domain.PurchaseOrderInstockPDAReq;
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
import com.xiaomai.supershopowner.entity.ReceiveGoods;
import com.xiaomai.supershopowner.entity.SuperPurchaseOrderItemsRspTransfer;
import com.xiaomai.supershopowner.entity.WeekSales;
import com.xiaomai.supershopowner.service.GoodsService;
import com.xiaomai.supershopowner.service.WeekSalesService;

@RestController
@RequestMapping(value = "order")
public class OrderRS extends BaseRS {
	private org.slf4j.Logger log = LoggerFactory.getLogger(OrderRS.class);

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
				
				for(SuperPurchaseOrder sp:spo.getResult()){//查询所有的订单
					List<SuperPurchaseOrderItemsRsp> spoirs = superStoreService.getPurchaseOrderItemRspList(sp.getOrderCode());
					
					for(SuperPurchaseOrderItemsRsp spoir:spoirs){//查询一个订单中多个商品,为了获取订单中商品的下单数量，实际收货数量
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
		return JSONObject.fromObject(rr, JSONObjectConfig.getTime())
				.toString();
	}

	// 查询订单详情
	@RequestMapping(value = "findOrderGoods", method = RequestMethod.POST)
	public String findOrderGoods(
			@RequestParam(value = "orderCode", required = true) String orderCode,
			@RequestParam(value = "storeCode", required = true) String storeCode) {
		DecimalFormat df = new DecimalFormat("0.00");
		RSResult rr = new RSResult();
		int totalOrderNumber=0;
		int totalActualNumber=0;
		Double totalOrderValue=0.0;
		Double totalActualVal=0.0;
		OrderTransfer otf = new OrderTransfer();
		List<SuperPurchaseOrderItemsRspTransfer> sL = new ArrayList<SuperPurchaseOrderItemsRspTransfer>();
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the findOrderGoods");
				List<SuperPurchaseOrderItemsRsp> spoirs = superStoreService.getPurchaseOrderItemRspList(orderCode);//根据订单号查询所有商品
				
				for(SuperPurchaseOrderItemsRsp spoir:spoirs){//为了获取订单数，实际收货数，订单总价，时间收货总价，周销，月销等
					totalOrderNumber+=(null==spoir.getOrderNumber()?0:spoir.getOrderNumber());
					totalActualNumber+=(null==spoir.getReceiveNumber()?0:spoir.getReceiveNumber());
					
					totalOrderValue += spoir.getPurcorderGoodsPrice().
							multiply(new BigDecimal(Double.toString(Double.parseDouble(String.valueOf((null==spoir.getOrderNumber()?0:spoir.getOrderNumber())))))).doubleValue();
					totalActualVal += spoir.getPurcorderGoodsPrice().
							multiply(new BigDecimal(Double.toString(Double.parseDouble(String.valueOf((null==spoir.getReceiveNumber()?0:spoir.getReceiveNumber())))))).doubleValue();
					
					SuperPurchaseOrderItemsRspTransfer spf = new SuperPurchaseOrderItemsRspTransfer();//返回数据给前端，包含了订单的基本详情，还包含本地库中的weekSales,MonSales,Inventory库存
					spf.setGoodsCode(spoir.getGoodsCode());
					spf.setGoodsName(spoir.getGoodsName());
					spf.setOrderNumber(spoir.getOrderNumber());
					spf.setPurcorderGoodsPrice(spoir.getPurcorderGoodsPrice());
					spf.setReceiveNumber(spoir.getReceiveNumber());
					spf.setUnit(spoir.getUnit());
					spf.setShelfLife(spoir.getShelfLife());//shelfLife     保质期
					spf.setSpecification(spoir.getSpecification());//specification  商品规格
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("storeCode", storeCode);
					map.put("goodsCode", spoir.getGoodsCode());
					Goods g = goodsService.findLatestGoods(map);//查询本地数据库中的Good 得到weekSale 等
					if(null!=g){
						spf.setWeekSales(String.valueOf(g.getWeekSales()));
						spf.setMonthSales(String.valueOf(g.getMonthSales()));
						spf.setInventory(String.valueOf(g.getInventory()));
						spf.setImageUrl(g.getImagesUrl());
					}
					sL.add(spf);
				}
				otf.setSpoirt(sL);
				otf.setActualNumber(String.valueOf(totalActualNumber));
				otf.setOrderNumber(String.valueOf(totalOrderNumber));
				otf.setReceiveTotalAmount(String.valueOf(df.format(totalActualNumber)));
				otf.setOrderTotalAmount(String.valueOf(df.format(totalOrderValue)));
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
	public String updateOrder(@RequestBody  ReceiveGoods rgs) {
		RSResult rr = new RSResult();
		BaseDto updateBaseDto = null;
		List<PurchaseOrderInstockPDAReq> poipdars = new ArrayList<PurchaseOrderInstockPDAReq>();
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the update orderRs starting...");
				for(SuperPurchaseOrderItemsRsp sr:rgs.getSuperPurchaseOrderItemsRspL()){
					PurchaseOrderInstockPDAReq poipda = new PurchaseOrderInstockPDAReq();
					poipda.setPurcorderCode(rgs.getPurcorderCode());
					poipda.setUserId(rgs.getUserId());
					poipda.setUserName(rgs.getUserName());
					poipda.setShopCode(rgs.getShopCode());
					poipda.setShopName(rgs.getShopName());
					poipda.setPurchaseType(rgs.getPurchaseType());
					poipda.setSupplyCommodityId(rgs.getSupplyCommodityId());
					poipda.setSupplyCommodityName(rgs.getSupplyCommodityName());
					poipda.setOutBizCode(rgs.getOutBizCode());
					
					//设置每个商品的信息
					poipda.setGoodsCode(sr.getGoodsCode());
					poipda.setGoodsName(sr.getGoodsName());
					poipda.setPurcorderGoodsPrice(sr.getPurcorderGoodsPrice());
					poipda.setPurcorderGoodsCount(sr.getOrderNumber());
					poipda.setInstockCount(sr.getReceiveNumber());
					poipdars.add(poipda);
				}
				updateBaseDto = superStoreService.saveSuperPurchaseOrder(poipdars);
				log.debug("call the update orderRs end");
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
					GoodsInfoDto gid = superStoreService.getGoodsInfoByCgbk(sku);
					
					map.put("goodsCode", gid==null?null:gid.getGoodsCode());
					if(gid.getGoodsCode()!=null){
						Goods gs = goodsService.findGoodLast(map);
						gor.setImagesUrl(null==gs?null:gs.getImagesUrl());
						gor.setShelfLife(null==gs?null:gs.getShelfLife());
						gor.setWeekSales(null==gs?null:gs.getWeekSales());
						gor.setInventory(null==gs?null:gs.getInventory());
						
						gor.setCoefficien(gid==null?null:gid.getCoefficien());
						gor.setGoodsName(gid==null?null:gid.getGoodsName());
						gor.setPrice(gid==null?null:gid.getPrice());
						gor.setGoodsCode(gid==null?null:gid.getGoodsCode());
						gor.setDayDistribution(gid.getDayDistribution());
						gor.setLogisticsType(gid.getLogisticsType());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						map.put("nowDate", sdf.format(new Date()));
						List<WeekSales> ws = weekSalesService.findWeekSales(map);
						gor.setWs(ws);
						
						rr.setCode("200");
						rr.setMsg("扫描二维码获取商品信息成功");
						rr.setResult(gor);
					}else{
						rr.setCode("200");
						rr.setMsg("无该商品信息");
						rr.setResult(null);
					}
				}else{
					map.put("goodsCode", goodsCode);
					Goods gs = goodsService.findGoodLast(map);
					
					gor.setGoodsCode(goodsCode);
					gor.setGoodsName(null==gs?null:gs.getGoodsName());
					gor.setImagesUrl(null==gs?null:gs.getImagesUrl());
					gor.setShelfLife(null==gs?null:gs.getShelfLife());
					gor.setWeekSales(null==gs?null:gs.getWeekSales());
					gor.setInventory(null==gs?null:gs.getInventory());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					map.put("nowDate", sdf.format(new Date()));
					List<WeekSales> ws = weekSalesService.findWeekSales(map);
					gor.setWs(ws);
					
					rr.setCode("200");
					rr.setMsg("查询商品信息成功");
					rr.setResult(gor);
				}
				
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
	public String findAllGoods(@RequestParam(value="shopCode",required=false) String shopCode,
			@RequestParam(value="typeCode",required=false) String typeCode){
		RSResult rr = new RSResult();
		HashMap<String, Object> map = new HashMap<String,Object>();
		Integer pageNum = null==request.getHeader("pageNum")?0:Integer.valueOf(request.getHeader("pageNum"));
		Integer pageSize = null==request.getHeader("pageSize")?0:Integer.valueOf(request.getHeader("pageSize"));
		Pager<GoodsInfoDtoTransfer> gidP = new Pager<GoodsInfoDtoTransfer>();
		map.put("storeCode", shopCode);
		List<GoodsInfoDtoTransfer> gidts= new ArrayList<GoodsInfoDtoTransfer>();//存储回传数据
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("call the findAllGoods starting...");
				List<GoodsInfoDto> gids = superStoreService.getGoodsInfoByType(shopCode, typeCode);
				int currIdx = (pageNum > 1 ? (pageNum -1) * pageSize : 0);
				if(gids.size()!=0){
					for (int i = 0; i < pageSize && i < gids.size() - currIdx; i++) {
						GoodsInfoDto goodInfo = gids.get(currIdx + i);
						map.put("goodsCode", goodInfo.getGoodsCode());
						Goods gs = goodsService.findLatestGoods(map);//查询本地数据库中最新的统计数据获取周销，月销等
						
						GoodsInfoDtoTransfer gidt = new GoodsInfoDtoTransfer();
						if(null!=gs){
							gidt.setWeekSales(String.valueOf(gs.getWeekSales()));
							gidt.setMonthProvide(String.valueOf(gs.getMonthProvide()));
							gidt.setMonthSales(String.valueOf(gs.getMonthSales()));
							gidt.setImageUrl(gs.getImagesUrl());
						}
						gidt.setShelfLife(goodInfo.getShelfLife());
						gidt.setCoefficien(goodInfo.getCoefficien());
						gidt.setGoodsCode(goodInfo.getGoodsCode());
						gidt.setGoodsName(goodInfo.getGoodsName());
						gidt.setPrice(goodInfo.getPrice());
						
						gidts.add(gidt);
			            
			        }
				}
				gidP.setCurrentPage(pageNum);
				gidP.setPageSize(pageSize);
				gidP.setResult(gidts);
				rr.setCode("200");
				rr.setMsg("获取类目下所有的商品成功");
				rr.setResult(gidP);
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
