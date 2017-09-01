package com.xiaomai.supershopowner.entity;

import java.util.List;

public class CustTransfer {
    private Integer custNumber;
    
    private List<Cust> custList;

	public Integer getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(Integer custNumber) {
		this.custNumber = custNumber;
	}

	public List<Cust> getCustList() {
		return custList;
	}
	public void setCustList(List<Cust> custList) {
		this.custList = custList;
	}
   
   
}