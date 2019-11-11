package com.pang.mall.utils.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ParameterTool
 * @Package com.pang.mall.utils.check
 * @description: 参数校验工具
 * @date 2019/11/11 20:34
 */
public class ParameterTool {

    /**
     * 校验登录用户名
     *
     * @param userName 登录用户名
     * @return 是否符合标准
     */
    public static boolean checkUserName(String userName) {
        return checkStrByPattern(userName, "^\\w{6,20}$");
    }

    /**
     * 校验登录密码
     *
     * @param password 登录密码
     * @return 是否符合标准
     */
    public static boolean checkPassword(String password) {
        return checkStrByPattern(password, "^\\w{6,20}$");
    }

    /**
     * 校验字符串是否符合正则表达式
     *
     * @param str     要校验的字符串
     * @param pattern 正则表达式
     * @return 是否符合正则表达式
     */
    public static boolean checkStrByPattern(String str, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(str);
        return matcher.matches();
    }
}
