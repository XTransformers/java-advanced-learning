package com.xtransformers.designpattern.testability.exercise;

import java.util.Date;

/**
 * @author daniel
 * @date 2021-05-20
 */
public class Demo {
    // 未决行为 封装解决
    public long caculateDelayDays(Date dueTime) {
        long currentTimestamp = System.currentTimeMillis();
        if (isAfterNow(dueTime, currentTimestamp)) {
            return 0;
        }
        long delayTime = currentTimestamp - dueTime.getTime();
        long delayDays = delayTime / 86400;
        return delayDays;
    }

    protected boolean isAfterNow(Date dueTime, long currentTimestamp) {
        return dueTime.getTime() >= currentTimestamp;
    }
}
