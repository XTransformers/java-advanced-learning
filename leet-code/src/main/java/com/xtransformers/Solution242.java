package com.xtransformers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author daniel
 * @date 2022-03-22
 */
public class Solution242 {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        Map<Character, Integer> sMap = getMap(s);
        Map<Character, Integer> tMap = getMap(t);
        return sMap.equals(tMap);
    }

    private Map<Character, Integer> getMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char each : s.toCharArray()) {
            map.merge(each, 1, Integer::sum);
        }
        return map;
    }

    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char each : s.toCharArray()) {
            map.put(each, map.getOrDefault(each, 0) + 1);
        }
        for (char each : t.toCharArray()) {
            map.put(each, map.getOrDefault(each, 0) - 1);
            if (map.get(each) < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] map = new int[26];
        for (char each : s.toCharArray()) {
            map[each - 'a']++;
        }
        for (char each : t.toCharArray()) {
            map[each - 'a']--;
            if (map[each - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
