package com.pang.mall.common.handler;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.pang.mall.common.exception.TokenTimeOutException;
import com.pang.mall.common.restful.ResponseEnum;
import com.pang.mall.common.restful.ResponseJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ControllerExceptionHandler
 * @Package com.pang.mall.common.handler
 * @description: 异常处理
 * @date 2019/11/14 12:35
 */
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("exception-handler");

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = TokenTimeOutException.class)
    public ResponseJSON tokenTimeOutHandler(TokenTimeOutException e) {
        LOGGER.info("token过期，请重新登录,param={}", e.getToken(),e);
        return new ResponseJSON(ResponseEnum.NOT_LOGIN);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = JWTDecodeException.class)
    public ResponseJSON tokenDecodeHandler(JWTDecodeException e){
        LOGGER.info("token解析出错",e);
        return new ResponseJSON(ResponseEnum.NOT_LOGIN);
    }
}
