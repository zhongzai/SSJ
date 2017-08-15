package com.xiaomai.supershopowner.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.dao.FlowDao;
import com.xiaomai.supershopowner.entity.Flow;

@Service
public class FlowService{
	@Resource
	private FlowDao  flowDao ; 
    
    public List<Flow> findByStoreCode(String storeCode){
    	
    	List<Flow> flowList = new ArrayList<>();
		try {			
			flowList = flowDao.findByStoreCode(storeCode);
						
		} catch (SQLException ex) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return flowList;
    }

}
