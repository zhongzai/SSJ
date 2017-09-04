package com.xiaomai.supershopowner.entity;

import java.util.List;

public class GoodQR{
	private String goodsCode;  //商品图片
	private String goodsName;    //商品名称
	private String price;    //零售价
	private String coefficien;    //请配系数
	private String shelfLife;  //保质期
	private Integer inventory; //库存
	private String imagesUrl;  //商品图片
	private Integer weekSales;   //周销
	private Integer dayDistribution;//是否日配
	private Integer logisticsType;//配送类型
	
	private List<WeekSales> ws;   //七天实体
	
	public List<WeekSales> getWs() {
		return ws;
	}

	public String getImagesUrl() {
		return imagesUrl;
	}

	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}

	public Integer getInventory() {
		return inventory;
	}

	public Integer getWeekSales() {
		return weekSales;
	}

	public void setWeekSales(Integer weekSales) {
		this.weekSales = weekSales;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public void setWs(List<WeekSales> ws) {
		this.ws = ws;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCoefficien() {
		return coefficien;
	}

	public void setCoefficien(String coefficien) {
		this.coefficien = coefficien;
	}

	public String getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public Integer getDayDistribution() {
		return dayDistribution;
	}

	public void setDayDistribution(Integer dayDistribution) {
		this.dayDistribution = dayDistribution;
	}

	public Integer getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(Integer logisticsType) {
		this.logisticsType = logisticsType;
	}

}
