package com.xtransformers;

/**
 * 罗马数字转整数
 */
public class Solution13 {

    public int romanToInt(String s) {
        s = s.toUpperCase().replaceAll("IV", "a")
                .replaceAll("IX", "b")
                .replaceAll("XL", "c")
                .replaceAll("XC", "d")
                .replaceAll("CD", "e")
                .replaceAll("CM", "f");
        int result = 0;
        for (char c : s.toCharArray()) {
            result += getValue(c);
        }
        return result;
    }

    private int getValue(char key) {
        switch (key) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            case 'a':
                return 4;
            case 'b':
                return 9;
            case 'c':
                return 40;
            case 'd':
                return 90;
            case 'e':
                return 400;
            case 'f':
                return 900;
            default:
                return 0;
        }
    }

    public int romanToInt2(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case 'I':
                    if (i + 1 < chars.length && (chars[i + 1] == 'V' || chars[i + 1] == 'X')) {
                        result -= 1;
                    } else {
                        result += 1;
                    }
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    if (i + 1 < chars.length && (chars[i + 1] == 'L' || chars[i + 1] == 'C')) {
                        result -= 10;
                    } else {
                        result += 10;
                    }
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    if (i + 1 < chars.length && (chars[i + 1] == 'D' || chars[i + 1] == 'M')) {
                        result -= 100;
                    } else {
                        result += 100;
                    }
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
                default:
                    throw new IllegalArgumentException("");
            }
        }
        return result;
    }

}
