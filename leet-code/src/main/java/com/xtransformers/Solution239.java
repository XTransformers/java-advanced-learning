package com.xtransformers;

import java.util.*;

/**
 * @author daniel
 * @date 2022-03-20
 */
public class Solution239 {

    public static void main(String[] args) {
        int[] result = new Solution239().maxSlidingWindow(new int[]{1, 3, 1, 2, 0, 5}, 3);
        System.out.println(Arrays.toString(result));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null) {
            return new int[]{};
        }
        // 存储最大数下标，first 存储的就是最大数下标
        Deque<Integer> window = new LinkedList<>();
        // 存储最终结果
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];

            // 当滑动窗口左侧划过数组0位后，左侧划过最大数下标时，从队首移除
            if (i >= k && window.getFirst() <= i - k) {
                window.removeFirst();
            }

            // 如果 x 比队列中下标对应数大，从队尾依次移除
            while (!window.isEmpty() && nums[window.getLast()] <= x) {
                window.removeLast();
            }

            // 往队尾追加
            window.addLast(i);

            // 往结果中存储
            if (i >= k - 1) {
                res[i - k + 1] = (nums[window.getFirst()]);
            }
        }
        return res;
    }

}
