package com.xtransformers;

import org.junit.Test;

import static org.junit.Assert.*;

public class Solution13Test {

    private Solution13 solution = new Solution13();

    @Test
    public void romanToInt() {
        assertEquals(3, solution.romanToInt("III"));
        assertEquals(4, solution.romanToInt("IV"));
        assertEquals(9, solution.romanToInt("IX"));
        assertEquals(58, solution.romanToInt("LVIII"));
        assertEquals(1994, solution.romanToInt("MCMXCIV"));
    }

    @Test
    public void romanToInt2() {
        assertEquals(3, solution.romanToInt2("III"));
        assertEquals(4, solution.romanToInt2("IV"));
        assertEquals(9, solution.romanToInt2("IX"));
        assertEquals(58, solution.romanToInt2("LVIII"));
        assertEquals(1994, solution.romanToInt2("MCMXCIV"));
    }
}