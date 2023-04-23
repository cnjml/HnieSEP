package com.hniesep.framework.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 * @author 吉铭炼
 */
@Component
public class JwtUtil {
    /**
     * 有效期为
     * 60 * 60 * 1000  一个小时
     */
    public static final Long JWT_TTL = 24 * 60 * 60 * 1000L;
    /**
     * 设置秘钥明文
     */
    public static final String JWT_KEY = "hniesepSecret";

    /**
     * @return uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成jtw
     *
     * @param subject token中要存放的数据（json格式）
     * @return jtw
     */
    public static String createJwt(String subject) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    /**
     * 生成jtw
     *
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return jtw
     */
    public static String createJwt(String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                //唯一的ID
                .setId(uuid)
                // 主题  可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("sg")
                // 签发时间
                .setIssuedAt(now)
                //使用HS256对称加密算法签名, 第二个参数为秘钥
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate);
    }

    /**
     * 创建token
     *
     * @param id        id
     * @param subject   主题
     * @param ttlMillis 设置过期时间
     * @return jwt
     */
    public static String createJwt(String id, String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return secretKey
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JWT_KEY.getBytes());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析
     *
     * @param jwt
     * @return jwt
     */
    public static Claims parseJwt(String jwt) {
        SecretKey secretKey = generalKey();
        Claims claims;
        try {
            claims = Jwts.parser()
                    // 设置标识名
                    .setSigningKey(secretKey)
                    //解析 token
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }

}