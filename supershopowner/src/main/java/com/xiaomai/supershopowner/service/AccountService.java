package com.xiaomai.supershopowner.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.dao.AccountDao;
import com.xiaomai.supershopowner.entity.Account;

@Service
public class AccountService{
	@Resource
	private AccountDao  accountDao ; 
    
    public Account findByUserAccount(String userAccount){
    	
    	Account account= new Account();
		try {			
			account = accountDao.findByUserAccount(userAccount);
						
		} catch (SQLException ex) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return account;
    }

}
