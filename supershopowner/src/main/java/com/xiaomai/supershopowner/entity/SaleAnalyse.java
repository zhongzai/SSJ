package com.xiaomai.supershopowner.entity;

import java.util.Date;

public class SaleAnalyse {
    private Integer id;

    private String storeCode;

    private Date salesDate;

    private String salesPeriod;

    private Double salesAmount;

    private Double proportion;
    
    private Integer custNumber;
    
    private String two;
    
    private String four;

    private String six;
    
    private String eight;
    
    private String ten;
    
    private String twelve;
    
    private String fourteen;
    
    private String sixteen;
    
    private String eighteen;
    
    private String twenty;
    
    private String twentyTwo;
    
    private String twentyFour;
    

    public String getTwo() {
		return two;
	}


	public void setTwo(String two) {
		this.two = two;
	}


	public Integer getCustNumber() {
		return custNumber;
	}


	public void setCustNumber(Integer custNumber) {
		this.custNumber = custNumber;
	}


	public String getFour() {
		return four;
	}


	public void setFour(String four) {
		this.four = four;
	}


	public String getSix() {
		return six;
	}


	public void setSix(String six) {
		this.six = six;
	}


	public String getEight() {
		return eight;
	}


	public void setEight(String eight) {
		this.eight = eight;
	}


	public String getTen() {
		return ten;
	}


	public void setTen(String ten) {
		this.ten = ten;
	}


	public String getTwelve() {
		return twelve;
	}


	public void setTwelve(String twelve) {
		this.twelve = twelve;
	}


	public String getFourteen() {
		return fourteen;
	}


	public void setFourteen(String fourteen) {
		this.fourteen = fourteen;
	}


	public String getSixteen() {
		return sixteen;
	}


	public void setSixteen(String sixteen) {
		this.sixteen = sixteen;
	}


	public String getEighteen() {
		return eighteen;
	}


	public void setEighteen(String eighteen) {
		this.eighteen = eighteen;
	}


	public String getTwenty() {
		return twenty;
	}


	public void setTwenty(String twenty) {
		this.twenty = twenty;
	}


	public String getTwentyTwo() {
		return twentyTwo;
	}


	public void setTwentyTwo(String twentyTwo) {
		this.twentyTwo = twentyTwo;
	}


	public String getTwentyFour() {
		return twentyFour;
	}


	public void setTwentyFour(String twentyFour) {
		this.twentyFour = twentyFour;
	}


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

    public Date getSalesDate() {
		return salesDate;
	}


	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}


	public String getSalesPeriod() {
        return salesPeriod;
    }

    public void setSalesPeriod(String salesPeriod) {
        this.salesPeriod = salesPeriod == null ? null : salesPeriod.trim();
    }


	public Double getSalesAmount() {
		return salesAmount;
	}


	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}


	public Double getProportion() {
		return proportion;
	}


	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

}