package com.pang.utils.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author pang
 * @version V1.0
 * @ClassName: PasswordEncoder
 * @Package com.pang.mall.utils.password
 * @description: 密码验证工具
 * @date 2019/11/11 21:14
 */
public class PasswordEncoder {
    public static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 加密
     *
     * @param source 要加密的密码
     * @return 加密后的密码
     */
    public static String encode(String source) {
        return encoder.encode(source);
    }

    /**
     * 对比密码是否相同
     *
     * @param source 原密码字串
     * @param target 加密后的字串
     * @return 相同返回true
     */
    public static boolean matches(String source, String target) {
        return encoder.matches(source, target);
    }
}
