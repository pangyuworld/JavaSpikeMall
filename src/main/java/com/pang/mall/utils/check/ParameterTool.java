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
     * 检验URL
     * @param url url
     * @return 是否符合标准
     */
    public static boolean checkURL(String url){
        return checkStrByPattern(url,"(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
    }

    /**
     * 检验库存
     * @param count 库存
     * @return 是否大于0
     */
    public static boolean checkCount(int count){
        return count>=0;
    }

    /**
     * 检验单价
     * @param price 单价
     * @return 单价是否大于0
     */
    public static boolean checkPrice(double price){
        return price>=0;
    }

    /**
     * 校验字段是否为中英数字下划线
     * @param itemName
     * @return
     */
    public static boolean checkText(String itemName){
        return checkStrByPattern(itemName,"^([\\u4E00-\\uFA29]|[\\uE7C7-\\uE7F3]|[a-zA-Z0-9_-]|[\"',，.。/、\\]\\[【】\\\\n\\s！!?？——_<>%;‘’；)《（）》(&+=`“”·*#@@]){0,}$");
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
