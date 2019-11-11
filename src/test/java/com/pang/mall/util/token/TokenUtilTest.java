package com.pang.mall.util.token;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.pang.mall.utils.token.TokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author pang
 * @version V1.0
 * @ClassName: TokenUtilTest
 * @Package com.pang.mall.util.token
 * @description: token工具测试
 * @date 2019/11/11 11:38
 */
public class TokenUtilTest {
    private static TokenUtil tokenUtil;

    @Test
    public void createTokenTest(){
        String token=tokenUtil.createJWT(1,1000*60,"seller");
        System.out.println(token);
    }

    @Test
    public void parseTokenTest()  {
        String token=tokenUtil.createJWT(1,1000,"seller");
        DecodedJWT decodedJWT = tokenUtil.parseJWT(token);
        System.out.println(decodedJWT.getIssuedAt().getTime());
        System.out.println(decodedJWT.getExpiresAt().getTime());
    }
}
