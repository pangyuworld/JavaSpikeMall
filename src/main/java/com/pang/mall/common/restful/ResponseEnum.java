package com.pang.mall.common.restful;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ResponseEnum
 * @Package com.pang.mall.common.restful
 * @description: 统一返回包装的枚举对象
 * @date 2019/11/11 21:28
 */
public enum ResponseEnum {
    SUCCESS_OPTION(200, "操作成功", true),
    LOGIN_SUCCESS(200, "登录成功", true),
    REGISTER_SUCCESS(200, "注册成功", true),
    SYSTEM_ERROR(500, "系统异常", false),
    REPEAT_REGISTER(400, "重复注册", false),
    BAD_REQUEST(400, "参数错误", false),
    NOT_MATCH(400, "账号密码不匹配符", false),
    NOT_LOGIN(401, "没有认证信息", false);


    private int code;

    private String message;

    private boolean success;

    ResponseEnum(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
