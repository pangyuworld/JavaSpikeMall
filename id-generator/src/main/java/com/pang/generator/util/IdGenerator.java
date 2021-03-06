package com.pang.generator.util;


import com.pang.exception.GenerateIDException;

/**
 * @author pang
 * @version V1.0
 * @ClassName: IdGenerator
 * @Package com.pang.generator.util
 * @description: 分布式id生成器
 * @date 2019/11/22 20:57
 */
public class IdGenerator {
    /** 机器ID */
    private static long workedId = 1L;
    /** 机器ID长度 */
    private static long WORKED_ID_BIT;
    /** 机房ID */
    private static long dataCenterId = 2L;
    /** 机房ID长度 */
    private static final long DATA_CENTER_ID_BIT = 5;
    /** 序列号 */
    private static long sequenceId;
    /** 序列号长度 */
    private static final long SEQUENCE_ID_BIT = 12;
    /** 最大机器ID */
    private static final long MAX_WORKED_ID = ~(-1L << WORKED_ID_BIT);
    /** 最大机房ID */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BIT);
    /** 一秒内的最大序列号 */
    private static final long MAX_SEQUENCE_ID = ~(-1L << SEQUENCE_ID_BIT);
    /** 机器ID部分左移位数 */
    private static final long WORKED_ID_LEFT = SEQUENCE_ID_BIT;
    /** 机房ID部分左移位数 */
    private static final long DATA_CENTER_ID_LEFT = SEQUENCE_ID_BIT + WORKED_ID_LEFT;
    /** 时间戳左移位数 */
    private static final long TIME_LEFT = DATA_CENTER_ID_LEFT + DATA_CENTER_ID_BIT;
    /** 上一次的时间戳 */
    private static long lastTime = -1L;
    /** 开始的时间戳 */
    private static long startTime = 1573487594576L;

    /**
     * 下一个ID
     *
     * @param workedId 机房ID，使用端口号的低两位表示
     */
    public static synchronized long nextId(long workedId) {
        WORKED_ID_BIT = workedId;
        // 获取当前时间
        long nowTime = System.currentTimeMillis();
        if (nowTime < lastTime) {
            // 如果当前时间小于上次生成序列号的时间，则直接抛出异常，事实上不允许有这种情况发生的
            throw new GenerateIDException();
        } else if (nowTime == lastTime) {
            // 如果当前时间等于上次生成序列号的时间，即相同时间内，那就生成ID
            sequenceId = (sequenceId + 1) & MAX_SEQUENCE_ID;
            if (sequenceId == 0L) {
                // 这里是因为当序列号超出最大值的时候，执行与操作会让序列号为0
                // 如果出现了这种情况，示例里是等待下次执行
                while (nowTime <= System.currentTimeMillis()) ;
                return nextId(workedId);
            }
        } else {
            // 如果当前时间大于上次生成序列号的时间，则将序列号清零
            sequenceId = 0L;
        }
        // 上一次更新序列号的时间戳进行更新
        lastTime = nowTime;
        // 生成最终的ID
        long result = (lastTime - startTime) << TIME_LEFT
                | (dataCenterId << DATA_CENTER_ID_LEFT)
                | (workedId << WORKED_ID_LEFT)
                | sequenceId;
        return result;
    }
}
