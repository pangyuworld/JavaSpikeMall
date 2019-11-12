package com.pang.mall.common.exception;

/**
 * @author pang
 * @version V1.0
 * @ClassName: GenerateIDException
 * @Package com.pang.mall.common.exception
 * @description: 生成ID异常
 * @date 2019/11/11 23:34
 */
public class GenerateIDException extends RuntimeException {
    public GenerateIDException() {
        this("生成ID时发生未知异常");
    }

    public GenerateIDException(String message) {
        super(message);
    }
}
