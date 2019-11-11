package com.pang.mall.utils.token;

import java.lang.annotation.*;

/**
 * token注解，用在拦截器处进行认证拦截
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Token {
}
