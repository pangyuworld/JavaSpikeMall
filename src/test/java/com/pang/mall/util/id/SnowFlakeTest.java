package com.pang.mall.util.id;

import com.pang.mall.utils.id.SnowFlake;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author pang
 * @version V1.0
 * @ClassName: SnowFlakeTest
 * @Package com.pang.mall.util
 * @description:
 * @date 2019/11/11 23:50
 */
public class SnowFlakeTest {
    @Test
    public void test() {
        Set<Long> idSet = new HashSet<>();
        while (true) {
            Long id=SnowFlake.nextId();
            System.out.println(id);
            if (!idSet.add(id)) {
                break;
            }
        }
    }
}
