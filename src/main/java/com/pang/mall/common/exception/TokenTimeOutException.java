package com.pang.mall.common.exception;

/**
 * @author pang
 * @version V1.0
 * @ClassName: TokenTimeOutException
 * @Package com.pang.mall.utils.token
 * @description: token过期异常
 * @date 2019/11/11 11:46
 */
public class TokenTimeOutException extends RuntimeException {
    public TokenTimeOutException() {
        this("token过期，请重新登录");
    }

    public TokenTimeOutException(String message) {
        super(message);
    }
}
