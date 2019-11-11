package com.pang.mall.services;

import com.pang.mall.common.exception.UserActionException;
import com.pang.mall.common.restful.ResponseEnum;
import com.pang.mall.config.TokenConfig;
import com.pang.mall.entity.Seller;
import com.pang.mall.mapper.SellerMapper;
import com.pang.mall.utils.check.ParameterTool;
import com.pang.mall.utils.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private final String SUBJECT = "seller";

    /**
     * 用户注册
     *
     * @param seller 要注册的用户信息
     */
    public boolean register(Seller seller) {
        // 检查是否存在用户
        if (sellerMapper.selectSellerByLoginName(seller.getUserName()) != null) {
            throw new UserActionException("用户已存在", ResponseEnum.REPEAT_REGISTER);
        }
        // 校验登录名
        if (!ParameterTool.checkUserName(seller.getUserName())) {
            throw new UserActionException("用户名不符合要求", ResponseEnum.BAD_REQUEST);
        }
        // 校验密码
        if (!ParameterTool.checkPassword(seller.getPassword())) {
            throw new UserActionException("密码不符合要求", ResponseEnum.BAD_REQUEST);
        }
        // 校验用户名
        if (seller.getSellerName().isEmpty()) {
            throw new UserActionException("用户昵称不能为空", ResponseEnum.BAD_REQUEST);
        }
        // 校验通过，对密码进行加密
        String password = seller.getPassword();
        password = passwordEncoder.encode(password);
        seller.setPassword(password);
        // 开始添加到数据库
        if (sellerMapper.addSeller(seller) > 0) {
            // 注册成功
            return true;
        } else {
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
            throw new UserActionException("用户名不符合要求", ResponseEnum.BAD_REQUEST);
        }
        // 校验密码
        if (!ParameterTool.checkPassword(password)) {
            throw new UserActionException("密码不符合要求", ResponseEnum.BAD_REQUEST);
        }
        // 登录名、密码校验成功，可以验证账号密码是否匹配了
        Seller seller = sellerMapper.selectSellerByLoginName(userName);
        if (!(seller != null && passwordEncoder.matches(password, seller.getPassword()))) {
            // 如果没有找到用户或者用户账号密码不匹配
            throw new UserActionException("账号密码不匹配", ResponseEnum.NOT_MATCH);
        }
        // 清空密码信息
        seller.setPassword("");
        // 生成token
        String token = TokenUtil.createJWT(seller.getSellerId(), TokenConfig.TOKEN_TTL, SUBJECT);
        // 组件返回map
        Map<String, Object> result = new HashMap<>();
        result.put("sellerInfo", seller);
        result.put("token", token);

        return result;
    }
}
