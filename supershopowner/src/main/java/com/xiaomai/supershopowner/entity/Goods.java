package com.xiaomai.supershopowner.entity;

public class Goods {
    private Integer id;

    private String goodsCode;

    private Integer weekSales;

    private Integer monthSales;

    private Long price;

    private String goodsName;

    private String imagesUrl;

    private String shelfLife;

    private Integer turnoverDays;

    private Integer averageSales;

    private Integer maxOrder;

    private Integer minOrder;

    private String specifications;

    private String unit;

    private Long purchasePrice;

    private Integer inventory;

    private String storeCode;

    private Integer coefficien;

    private Integer monthProvide;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public Integer getWeekSales() {
        return weekSales;
    }

    public void setWeekSales(Integer weekSales) {
        this.weekSales = weekSales;
    }

    public Integer getMonthSales() {
        return monthSales;
    }

    public void setMonthSales(Integer monthSales) {
        this.monthSales = monthSales;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl == null ? null : imagesUrl.trim();
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife == null ? null : shelfLife.trim();
    }

    public Integer getTurnoverDays() {
        return turnoverDays;
    }

    public void setTurnoverDays(Integer turnoverDays) {
        this.turnoverDays = turnoverDays;
    }

    public Integer getAverageSales() {
        return averageSales;
    }

    public void setAverageSales(Integer averageSales) {
        this.averageSales = averageSales;
    }

    public Integer getMaxOrder() {
        return maxOrder;
    }

    public void setMaxOrder(Integer maxOrder) {
        this.maxOrder = maxOrder;
    }

    public Integer getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(Integer minOrder) {
        this.minOrder = minOrder;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Long getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public Integer getCoefficien() {
        return coefficien;
    }

    public void setCoefficien(Integer coefficien) {
        this.coefficien = coefficien;
    }

    public Integer getMonthProvide() {
        return monthProvide;
    }

    public void setMonthProvide(Integer monthProvide) {
        this.monthProvide = monthProvide;
    }
}