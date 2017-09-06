package com.xiaomai.supershopowner.entity;

import java.util.List;

public class TagTransfer {
    private Integer custId;

    private List<String> tagList;

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	
}