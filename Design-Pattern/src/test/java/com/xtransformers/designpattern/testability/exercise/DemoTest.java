package com.xtransformers.designpattern.testability.exercise;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DemoTest {

    @Test
    public void caculateDelayDays() {
        Demo demo = new Demo() {
            @Override
            protected boolean isAfterNow(Date dueTime, long currentTimestamp) {
                return true;
            }
        };
        Date date = new Date(System.currentTimeMillis() - 86400 * 3);
        long delayDays = demo.caculateDelayDays(date);
        assertEquals(0, delayDays);

        Demo demo2 = new Demo() {
            @Override
            protected boolean isAfterNow(Date dueTime, long currentTimestamp) {
                return false;
            }
        };
        Date date2 = new Date(System.currentTimeMillis() - 86400 * 3);
        long delayDays2 = demo2.caculateDelayDays(date2);
        assertEquals(3, delayDays2);
    }

}