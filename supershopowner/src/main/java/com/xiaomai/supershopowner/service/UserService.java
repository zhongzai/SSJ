package com.xiaomai.supershopowner.service;

import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.common.TokenUtils;
import com.xiaomai.supershopowner.dao.UserLoginDao;
import com.xiaomai.supershopowner.entity.UserLogin;
import com.xiaomai.supershopowner.entity.UserTransfer;

@Service
public class UserService{
	@Resource
	private UserLoginDao  userLoginDao ; 
    
    public UserTransfer login(UserTransfer user){
    	
    	UserTransfer resposeUser= new UserTransfer();
		try {			
			//根据用户名查询用户登录
			UserLogin userLogin = userLoginDao.findByStoreCode(user.getUserAccount());
			
			String newToken = TokenUtils.createToken(user);
			if (userLogin != null){
				userLogin.setToken(newToken);
				userLogin.setLastLogin(new Date());
				//token保鲜
				userLoginDao.update(userLogin);
			}else{
				userLogin = new UserLogin();
				userLogin.setToken(newToken);
				userLogin.setUserAccount(user.getUserAccount());
				userLogin.setLastLogin(new Date());
				
				userLoginDao.insert(userLogin);
			}
			
			resposeUser.setToken(newToken);
			
		} catch (SQLException ex) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return resposeUser;
    }

}
