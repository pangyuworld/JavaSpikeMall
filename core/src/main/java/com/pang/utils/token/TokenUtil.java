package com.pang.utils.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pang.exception.TokenTimeOutException;
import com.pang.exception.UserActionException;
import com.pang.restful.ResponseEnum;

import java.util.Date;

/**
 * @author pang
 * @version V1.0
 * @ClassName: TokenUtil
 * @Package com.pang.utils.token
 * @description: token工具
 * @date 2019/11/11 11:18
 */
public class TokenUtil {

    /**
     * 创建一个token字符串
     *
     * @param userId    用户id
     * @param ttlMillis 超时时间
     * @param subject   主题，区分买家和买家
     * @return token字符串
     */
    public static String createJWT(long userId, long ttlMillis, String subject) {
        // 生成当前时间戳
        long nowMillis = System.currentTimeMillis();
        // 生成token的时间
        Date now = new Date(nowMillis);
        // 过期时间
        Date expiresDate = new Date(nowMillis + ttlMillis);
        // payload私有声明，这里省略

        // 生成token
        String token = JWT.create()
                // 设置主题
                .withSubject(subject)
                // 设置受众
                .withAudience("" + userId)
                // 设置颁发时间
                .withIssuedAt(now)
                // 设置签发人 TODO:这里以后要改成机器码
                .withIssuer("颁发人")
                // 设置过期时间
                .withExpiresAt(expiresDate)
                // 签发，参数为签发算法和秘钥
                .sign(Algorithm.HMAC256(subject));
        return token;
    }

    /**
     * 解析token
     *
     * @param token 要解析的token
     * @return 包含token认证信息的对象
     * @throws TokenTimeOutException 当token过期时候，抛出该异常
     */
    public static DecodedJWT parseJWT(String token) throws TokenTimeOutException {
        if (token == null) {
            throw new UserActionException("无认证信息", ResponseEnum.NOT_LOGIN);
        }
        DecodedJWT decode = null;
        try {
            decode = JWT.decode(token);
        } catch (JWTDecodeException e) {
            throw e;
        }
        long expiresDate = decode.getExpiresAt().getTime();
        if (expiresDate < System.currentTimeMillis()) {
            throw new TokenTimeOutException(token);
        }
        return decode;
    }
}
