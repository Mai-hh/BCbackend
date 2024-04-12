package com.huaihao.bookcrosser.backend.common;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtil {
    // 秘钥,用于签名和解签 JWT
    private static final String SECRET_KEY = "your-secret-key";
    // JWT 的过期时间,单位为毫秒
    private static final long EXPIRATION_TIME = 86400000; // 1 天

    /**
     * 生成 JWT
     * @param userId 用户 ID
     * @return JWT 字符串
     */
    public static String generateToken(Long userId) {
        Date expiresAt = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        return JWT.create()
                .withSubject(userId.toString())
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    /**
     * 解析 JWT
     * @param token JWT 字符串
     * @return 用户 ID
     */
    public static Long parseToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token);
        return Long.parseLong(decodedJWT.getSubject());
    }

    /**
     * 验证 JWT 是否有效
     * @param token JWT 字符串
     * @return 是否有效
     */
    public static boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
