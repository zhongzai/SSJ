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
	public void addOrders(List<Goods> gds){
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
		
		try{
			orderDao.insert(order);
			
			
			for(Goods good:gds){
				Order2good og = new Order2good();
				og.setGoodsCode(good.getGoodsCode());
				og.setGoodsTotal(good.getGoodTotal());
				og.setOrderCode(orderCode);
				order2GoodDao.insert(og);
				
				orderNumber += good.getGoodTotal();
				totalValue = new BigDecimal(Double.toString(good.getPrice())).
						multiply(new BigDecimal(Double.toString(Double.parseDouble(String.valueOf(good.getGoodTotal()))))).doubleValue();
			}
			Order o = new Order();
			o.setId(order.getId());
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
	public List<Order> findAllOrders(){
		log.debug("find all orders");
		List<Order> orders=new ArrayList<Order>();
		try {
			orders = orderDao.findListAll();
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
	

}
