package com.xtransformers;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Solution9Test {

    @Test
    public void isPalindrome() {
        Solution9 solution = new Solution9();
        int x = 121;
        assertTrue(solution.isPalindrome(x));
        x = -121;
        assertFalse(solution.isPalindrome(x));
        x = 123;
        assertFalse(solution.isPalindrome(x));
    }

}