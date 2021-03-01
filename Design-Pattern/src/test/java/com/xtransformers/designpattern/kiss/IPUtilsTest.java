package com.xtransformers.designpattern.kiss;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IPUtilsTest {

    private static final String EMPTY_STR = "";
    private static final String RIGHT_IP_STR = "172.68.3.5";
    private static final String WRONG_IP_STR = "0.68.3.7";
    private static final String WRONG_IP_STR2 = "6.0.a.3";
    private static final String WRONG_IP_STR3 = "5.0.266.3";
    private static final String WRONG_IP_STR4 = "5.9.3";

    @org.junit.Test
    public void isValidIpAddressV1() {
        assertFalse(IPUtils.isValidIpAddressV1(EMPTY_STR));
        assertTrue(IPUtils.isValidIpAddressV1(RIGHT_IP_STR));
        assertFalse(IPUtils.isValidIpAddressV1(WRONG_IP_STR));
        assertFalse(IPUtils.isValidIpAddressV1(WRONG_IP_STR2));
    }

    @org.junit.Test
    public void isValidIpAddressV2() {
        assertFalse(IPUtils.isValidIpAddressV2(EMPTY_STR));
        assertTrue(IPUtils.isValidIpAddressV2(RIGHT_IP_STR));
        assertFalse(IPUtils.isValidIpAddressV2(WRONG_IP_STR));
        assertFalse(IPUtils.isValidIpAddressV2(WRONG_IP_STR2));
        assertFalse(IPUtils.isValidIpAddressV2(WRONG_IP_STR3));
        assertFalse(IPUtils.isValidIpAddressV2(WRONG_IP_STR4));
    }

    @org.junit.Test
    public void isValidIpAddressV3() {
        assertFalse(IPUtils.isValidIpAddressV3(EMPTY_STR));
        assertTrue(IPUtils.isValidIpAddressV3(RIGHT_IP_STR));
        assertFalse(IPUtils.isValidIpAddressV3(WRONG_IP_STR));
        assertFalse(IPUtils.isValidIpAddressV3(WRONG_IP_STR2));
        assertFalse(IPUtils.isValidIpAddressV3(WRONG_IP_STR3));
        assertFalse(IPUtils.isValidIpAddressV3(WRONG_IP_STR4));
    }
}