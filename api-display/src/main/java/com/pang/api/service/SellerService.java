package com.pang.api.service;

import com.pang.api.config.TokenConfig;
import com.pang.api.mapper.SellerMapper;
import com.pang.entity.Seller;
import com.pang.exception.UserActionException;
import com.pang.redis.RedisTool;
import com.pang.restful.ResponseEnum;
import com.pang.utils.check.ParameterTool;
import com.pang.utils.password.PasswordEncoder;
import com.pang.utils.token.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: SellerService
 * @Package com.pang.mall.services
 * @description: 卖家服务
 * @date 2019/11/11 21:20
 */
@Service
public class SellerService {
    public static final String SUBJECT = "seller";
    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private RedisTool redis;
    private static final Logger LOGGER = LoggerFactory.getLogger(SellerService.class);

    /**
     * 用户注册
     *
     * @param seller 要注册的用户信息
     */
    public boolean register(Seller seller) {
        // 检查是否存在用户
        if (sellerMapper.selectSellerByLoginName(seller.getUserName()) != null) {
            LOGGER.debug("重复注册,seller={}", seller);
            throw new UserActionException("用户已存在", ResponseEnum.REPEAT_REGISTER);
        }
        // 校验登录名
        if (!ParameterTool.checkUserName(seller.getUserName())) {
            LOGGER.debug("用户名不符合规则,param={}", seller);
            throw new UserActionException("用户名不符合要求", ResponseEnum.BAD_REQUEST);
        }
        // 校验密码
        if (!ParameterTool.checkPassword(seller.getPassword())) {
            LOGGER.debug("密码不符合规则,seller={}", seller);
            throw new UserActionException("密码不符合要求", ResponseEnum.BAD_REQUEST);
        }
        // 校验用户名
        if (seller.getSellerName().isEmpty()) {
            LOGGER.debug("用户昵称不能为空,seller={}", seller);
            throw new UserActionException("用户昵称不能为空", ResponseEnum.BAD_REQUEST);
        }
        LOGGER.debug("参数校验通过,seller={}", seller);
        // 校验通过，对密码进行加密
        String password = seller.getPassword();
        password = PasswordEncoder.encode(password);
        LOGGER.debug("对密码进行加密，加密后的密码为:{}", password);
        seller.setPassword(password);
        // 开始添加到数据库
        if (sellerMapper.addSeller(seller) > 0) {
            LOGGER.debug("商家注册成功,seller={}", seller);
            // 注册成功
            return true;
        } else {
            LOGGER.info("商家注册失败，失败原因未知，seller={}", seller);
            throw new UserActionException("注册失败，失败原因未知", ResponseEnum.SYSTEM_ERROR);
        }
    }

    /**
     * 用户登录
     *
     * @param userName 账号
     * @param password 密码
     * @return 包含用户信息和token认证的map
     */
    public Map<String, Object> login(String userName, String password) {
        // 校验登录名
        if (!ParameterTool.checkUserName(userName)) {
            LOGGER.debug("用户名不符合规则,username={},password={}", userName, password);
            throw new UserActionException("用户名不符合要求", ResponseEnum.BAD_REQUEST);
        }
        // 校验密码
        if (!ParameterTool.checkPassword(password)) {
            LOGGER.debug("密码不符合规则,username={},password={}", userName, password);
            throw new UserActionException("密码不符合要求", ResponseEnum.BAD_REQUEST);
        }
        // 登录名、密码校验成功，可以验证账号密码是否匹配了
        Seller seller = sellerMapper.selectSellerByLoginName(userName);
        if (!(seller != null && PasswordEncoder.matches(password, seller.getPassword()))) {
            LOGGER.debug("输入了不匹配的密码,username={},password={}", userName, password);
            // 如果没有找到用户或者用户账号密码不匹配
            throw new UserActionException("账号密码不匹配", ResponseEnum.NOT_MATCH);
        }
        // 清空密码信息
        seller.setPassword("");
        // 生成token
        String token = TokenUtil.createJWT(seller.getSellerId(), TokenConfig.TOKEN_TTL, SUBJECT);
        // 以token为键值，将用户信息保存到redis中，过期时间和token一致
        redis.setHash(token, "value", seller.getSellerId(), TokenConfig.TOKEN_TTL);
        redis.setHash(token, "authorization", SUBJECT, TokenConfig.TOKEN_TTL);
        redis.expire(token, TokenConfig.TOKEN_TTL);
        LOGGER.debug("将token和用户信息保存到redis中,key={},ttl={}", token, TokenConfig.TOKEN_TTL);
        // 组件返回map
        Map<String, Object> result = new HashMap<>();
        result.put("sellerInfo", seller);
        result.put("token", token);
        LOGGER.debug("返回登录信息,result={}", result);
        return result;
    }
}
