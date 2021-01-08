package com.xtransformers;

public class Solution9 {

    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        int a = 0, y = 0, b = x;
        while (b != 0) {
            a = b % 10;
            y = y * 10 + a;
            b = b / 10;
        }
        return x == y;
    }

}
