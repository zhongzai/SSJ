package com.xiaomai.supershopowner.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.common.WebPage;
import com.xiaomai.supershopowner.dao.UserDao;
import com.xiaomai.supershopowner.entity.User;

@Service
public class UserService implements BaseService<User, Integer>{
	@Resource
	private UserDao  userDao ; 
	private org.slf4j.Logger log = LoggerFactory.getLogger(UserService.class);
    
    public User findUserByName(String userName){
    	log.debug("get user starting...");
		User user = null;
		try {
			user = userDao.findUserByName(userName);
			if (user != null)
				user.setPassword(null);
		} catch (SQLException ex) {
			log.error("exception:", ex);
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		log.debug("get user end");
		return user;
    }

	@Override
	public Integer insert(User t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer update(User t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public WebPage<User> findPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}
}
