package com.pang.mall.services;

import com.pang.mall.common.exception.UserActionException;
import com.pang.mall.common.restful.ResponseEnum;
import com.pang.mall.config.TokenConfig;
import com.pang.mall.entity.Buyer;
import com.pang.mall.mapper.BuyerMapper;
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
 * @ClassName: BuyerService
 * @Package com.pang.mall.services
 * @description: 买家服务
 * @date 2019/11/11 20:19
 */
@Service
public class BuyerService {
    @Autowired
    private BuyerMapper buyerMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private final String SUBJECT = "buyer";

    /**
     * 用户注册
     *
     * @param buyer 要注册的用户信息
     */
    public Boolean register(Buyer buyer) {
        // 检查是否存在用户
        if (buyerMapper.selectBuyerByLoginName(buyer.getUserName()) != null) {
            throw new UserActionException("用户已存在", ResponseEnum.REPEAT_REGISTER);
        }
        // 校验登录名
        if (!ParameterTool.checkUserName(buyer.getUserName())) {
            throw new UserActionException("用户名不符合要求", ResponseEnum.BAD_REQUEST);
        }
        // 校验密码
        if (!ParameterTool.checkPassword(buyer.getPassword())) {
            throw new UserActionException("密码不符合要求", ResponseEnum.BAD_REQUEST);
        }
        // 校验用户名
        if (buyer.getBuyerName().isEmpty()) {
            throw new UserActionException("用户昵称不能为空", ResponseEnum.BAD_REQUEST);
        }
        // 校验通过，对密码进行加密
        String password = buyer.getPassword();
        password = passwordEncoder.encode(password);
        buyer.setPassword(password);
        // 开始添加到数据库
        if (buyerMapper.addBuyer(buyer) > 0) {
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
        Buyer buyer = buyerMapper.selectBuyerByLoginName(userName);
        if (!(buyer != null && passwordEncoder.matches(password, buyer.getPassword()))) {
            // 如果没有找到用户或者用户账号密码不匹配
            throw new UserActionException("账号密码不匹配", ResponseEnum.NOT_MATCH);
        }
        // 清空密码信息
        buyer.setPassword("");
        // 生成token
        String token = TokenUtil.createJWT(buyer.getBuyerId(), TokenConfig.TOKEN_TTL, SUBJECT);
        // 组件返回map
        Map<String, Object> result = new HashMap<>();
        result.put("buyerInfo", buyer);
        result.put("token", token);

        return result;
    }
}
