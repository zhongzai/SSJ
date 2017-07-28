package com.xiaomai.supershopowner.service;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.dao.Order2GoodDao;
import com.xiaomai.supershopowner.dao.OrderDao;
import com.xiaomai.supershopowner.entity.Goods;
import com.xiaomai.supershopowner.entity.Order;
import com.xiaomai.supershopowner.entity.Order2good;


@Service
public class OrderService {
	
	private org.slf4j.Logger log = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	Order2GoodDao order2GoodDao;
	
	//添加订单
	public void addOrders(Order or){
		log.debug("save orders starting...");
		
		Order order = new Order();
		order.setOrderTime(new Date());
		String orderCode = "order"+System.currentTimeMillis();
		order.setOrderCode(orderCode);
		int orderNumber = 0;
		order.setOrderNumber(orderNumber);
		order.setStatus(0);
		Double totalValue = 0.0;
		order.setTotalValue(totalValue);
		order.setStoreCode(or.getStoreCode());
		
		List<Goods> gds = or.getGoods();
		
		try{
			orderDao.insert(order);
			
			for(Goods good:gds){
				Order2good og = new Order2good();
				og.setGoodsCode(good.getGoodsCode());
				og.setGoodsTotal(good.getGoodTotal());
				og.setOrderCode(orderCode);
				order2GoodDao.insert(og);
				
				orderNumber += good.getGoodTotal();
				totalValue += new BigDecimal(Double.toString(good.getPrice())).
						multiply(new BigDecimal(Double.toString(Double.parseDouble(String.valueOf(good.getGoodTotal()))))).doubleValue();
			}
			Order o = new Order();
			o.setOrderCode(orderCode);
			o.setOrderNumber(orderNumber);
			o.setTotalValue(totalValue);
			
			orderDao.update(o);
		}catch(SQLException ex){
			log.error("exception:", ex);
			throw new RuntimeException(ex); 
		}
		log.debug("save orders end...");
	}
	//查询所有的订单
	public List<Order> findAllOrders(String storeCode){
		log.debug("find all orders");
		List<Order> orders=new ArrayList<Order>();
		try {
			orders = orderDao.selectList(storeCode);
		} catch (SQLException ex) {
			log.error("exception:", ex);
			throw new RuntimeException(ex); 
		}
		log.debug("find all orders end...");
		return orders;
	}
	
	//查询一个订单详情
	public List<Order2good> findOrderGoods(String orderCode){
		log.debug("find goods by order id starting...");
		List<Order2good> og = null;
		try {
			og = order2GoodDao.findGoodsByOrderCode(orderCode);
		} catch (SQLException ex) {
			log.error("exception:", ex);
			throw new RuntimeException(ex); 
		}
		log.debug("find goods by order id end...");
		return og;
	}
	
	//更改订单
	public int updateOrder(Order order){
		log.debug("update order starting...");
		int updateId = 0;
		try {
			order.setUpdateTime(new Date());
			updateId = orderDao.update(order);
		} catch (SQLException ex) {
			log.error("exception:", ex);
			throw new RuntimeException(ex); 
		}
		log.debug("update order ending...");
		return updateId;
	}
	
	//确认收货，更改订单中商品的实际收货量
	public int updateAndAffirmOrder(Order order){
		log.debug("affrim order starting...");
		List<Order2good> order2Goods = order.getOrder2good();
		int realReceiveTotal = 0;
		int updateId;
		Double totalValue = 0.0;
		String orderCode = null;
		try{
			for(Order2good order2good : order2Goods){
				order2GoodDao.update(order2good);
				realReceiveTotal+=order2good.getRealTotal();
				totalValue += new BigDecimal(Double.toString(order2good.getPrice())).
						multiply(new BigDecimal(Double.toString(Double.parseDouble(String.valueOf(order2good.getRealTotal()))))).doubleValue();
				if(orderCode==null){
					orderCode = order2good.getOrderCode();
				}
			}
			Order o = new Order();
			o.setStatus(1);
			o.setOrderCode(orderCode);
			o.setActualNumber(realReceiveTotal);
			o.setActualValue(totalValue);
			updateId = this.updateOrder(o);
		}catch(SQLException ex){
			log.error("exception:", ex);
			throw new RuntimeException(ex);
		}
		log.debug("affrim order end...");
		return updateId;
	}
}
