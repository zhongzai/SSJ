package com.xiaomai.supershopowner.entity;


public class SalesTranfer {
    private Sale todaySales;

    private Sale yestodaySales;

	public Sale getTodaySales() {
		return todaySales;
	}

	public void setTodaySales(Sale todaySales) {
		this.todaySales = todaySales;
	}

	public Sale getYestodaySales() {
		return yestodaySales;
	}

	public void setYestodaySales(Sale yestodaySales) {
		this.yestodaySales = yestodaySales;
	}

}