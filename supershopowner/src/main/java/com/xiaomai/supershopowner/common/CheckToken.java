package com.xiaomai.supershopowner.common;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.dao.UserLoginDao;
import com.xiaomai.supershopowner.entity.UserLogin;

/**
 * 验证Token
 * User: CQL
 *
 */
@Repository
public class CheckToken {
    @Autowired
    UserLoginDao userLoginDao;
    public boolean  check(String token) throws SQLException{
        boolean res = false;
        //根据Token查询用户的登录状态
        UserLogin persistentLogin = userLoginDao.getLatestLoginedUserByToken(token);

        if(persistentLogin!=null){
            res=true;
        }else{
            res=false;
        }
        return res;
    }


}
