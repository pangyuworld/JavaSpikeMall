package com.pang.mall.utils.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author pang
 * @version V1.0
 * @ClassName: PasswordEncoder
 * @Package com.pang.mall.utils.password
 * @description: 密码验证工具
 * @date 2019/11/11 21:14
 */
@Component
public class PasswordEncoder extends BCryptPasswordEncoder {
}
