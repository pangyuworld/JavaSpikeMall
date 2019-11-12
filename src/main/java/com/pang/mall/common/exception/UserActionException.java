package com.pang.mall.common.exception;

import com.pang.mall.common.restful.ResponseEnum;

/**
 * @author pang
 * @version V1.0
 * @ClassName: UserActionException
 * @Package com.pang.mall.common.exception
 * @description: 用户操作异常
 * @date 2019/11/11 20:25
 */
public class UserActionException extends RuntimeException {

    private ResponseEnum exceptionType;

    /**
     * 默认构造，系统异常
     */
    public UserActionException() {
        this("用户操作异常。", ResponseEnum.SYSTEM_ERROR);
    }

    /**
     * 带参数构造
     *
     * @param message       异常信息
     * @param exceptionType 异常类型
     */
    public UserActionException(String message, ResponseEnum exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public ResponseEnum getExceptionType() {
        return exceptionType;
    }
}
