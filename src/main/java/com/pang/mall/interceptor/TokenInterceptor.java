package com.pang.mall.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.pang.mall.utils.redis.RedisTool;
import com.pang.mall.utils.token.Token;
import com.pang.mall.utils.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pang
 * @version V1.0
 * @ClassName: TokenInterceptor
 * @Package com.pang.mall.interceptor
 * @description: token拦截器
 * @date 2019/11/11 22:28
 */
@Component
@Order
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisTool redis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Token token = handlerMethod.getMethodAnnotation(Token.class);
        // 如果不存在token注解，则通过认证
        if (token == null) {
            return super.preHandle(request, response, handler);
        }
        // 如果存在token注解，则需要认证
        // TODO 如果redis里面存在该token，则直接通过认证，这个等做好redis客户端以后就可以实现了
        // TODO 如果redis未击中，则手动进行校验
        // 从认证中取出token字符串
        String tokenStr = request.getHeader("Authorization");
        // 开始认证，如果认证出错，则直接抛出异常
        DecodedJWT decodedJWT = TokenUtil.parseJWT(tokenStr);
        // 认证通过，将token内保存的信息添加到请求中
        request.setAttribute(decodedJWT.getSubject(), decodedJWT.getAudience());
        // 执行正常的操作
        return super.preHandle(request, response, handler);
    }
}
