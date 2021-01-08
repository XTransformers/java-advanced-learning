package com.xtransformers;

public class Solution26 {
    /**
     * 删除排序数组中的重复项
     * 使用快慢指针
     *
     * @param nums 数组
     * @return 不重复的数据个数
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }
}
