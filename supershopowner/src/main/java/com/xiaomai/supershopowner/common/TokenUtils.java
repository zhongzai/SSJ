package com.xiaomai.supershopowner.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.xiaomai.supershopowner.entity.UserTransfer;


public class TokenUtils
{

    public static final String MAGIC_KEY = "obfuscate";


    public static String createToken(UserTransfer user)
    {
		/* Expires in one hour */
        long expires = System.currentTimeMillis() + 1000L * 60 * 60;

        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(user.getUserAccount());
        tokenBuilder.append(":");
        tokenBuilder.append(user.getStoreCode());
        tokenBuilder.append(":");
        tokenBuilder.append(expires);
        tokenBuilder.append(":");
        tokenBuilder.append(TokenUtils.computeSignature(user, expires));

        return tokenBuilder.toString();
    }


    public static String computeSignature(UserTransfer user, long expires)
    {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(user.getUserAccount());
        signatureBuilder.append(":");
        signatureBuilder.append(user.getStoreCode());
        signatureBuilder.append(":");
        signatureBuilder.append(expires);
        signatureBuilder.append(":");
        signatureBuilder.append(TokenUtils.MAGIC_KEY);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(digest.digest(signatureBuilder.toString().getBytes()));
    }

}