package com.xiaomai.supershopowner.common;

import com.xiaomai.supershopowner.entity.UserTransfer;



public class TokenUtils
{

    public static String createToken(UserTransfer user)
    {
        long expires = System.currentTimeMillis() + 1000L * 60 * 60;

        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(user.getUserAccount());
        tokenBuilder.append(":");
        tokenBuilder.append(user.getStoreCode());
        tokenBuilder.append(":");
        tokenBuilder.append(expires);

        String token = Md5.encode(tokenBuilder.toString());
        
        return token;
    }
}