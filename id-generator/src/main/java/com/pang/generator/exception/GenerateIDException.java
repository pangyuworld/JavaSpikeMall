package com.pang.generator.exception;

/**
 * @author pang
 * @version V1.0
 * @ClassName: GenerateIDException
 * @Package com.pang.generator.exception
 * @description: 生成Id时产生的异常
 * @date 2019/11/22 20:58
 */
public class GenerateIDException extends RuntimeException {
    public GenerateIDException() {
        this("生成ID时发生未知异常");
    }

    public GenerateIDException(String message) {
        super(message);
    }
}