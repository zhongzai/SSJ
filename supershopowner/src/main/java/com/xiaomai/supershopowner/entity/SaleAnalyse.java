package com.xiaomai.supershopowner.entity;

public class SaleAnalyse {
    private Integer id;

    private String storeCode;

    private Integer salesDate;

    private String salesPeriod;

    private Long salesAmount;

    private Long proportion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public Integer getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Integer salesDate) {
        this.salesDate = salesDate;
    }

    public String getSalesPeriod() {
        return salesPeriod;
    }

    public void setSalesPeriod(String salesPeriod) {
        this.salesPeriod = salesPeriod == null ? null : salesPeriod.trim();
    }

    public Long getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Long salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Long getProportion() {
        return proportion;
    }

    public void setProportion(Long proportion) {
        this.proportion = proportion;
    }
}