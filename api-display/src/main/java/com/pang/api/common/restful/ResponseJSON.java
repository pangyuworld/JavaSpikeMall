package com.pang.api.common.restful;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ResponseJSON
 * @Package com.pang.mall.common.exception.restful
 * @description: 统一返回标识
 * @date 2019/11/11 21:25
 */
public class ResponseJSON<E> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 响应包消息
     */
    private String message;
    /**
     * 操作是否成功
     */
    private boolean success;
    /**
     * 数据内容
     */
    private E data;

    public ResponseJSON(ResponseEnum e) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.success = e.isSuccess();
    }

    public ResponseJSON(E data, ResponseEnum e) {
        this.data = data;
        this.code = e.getCode();
        this.message = e.getMessage();
        this.success = e.isSuccess();
    }

    public ResponseJSON(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public ResponseJSON(int code, String message, boolean success, E data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public ResponseJSON<E> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseJSON<E> setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public ResponseJSON<E> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public E getData() {
        return data;
    }

    public ResponseJSON<E> setData(E data) {
        this.data = data;
        return this;
    }
}
