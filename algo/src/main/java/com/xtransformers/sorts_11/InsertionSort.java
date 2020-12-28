package com.xtransformers.sorts_11;

/**
 * 插入排序
 */
public class InsertionSort {

    /**
     * 插入排序
     *
     * @param arr 等待排序的数组
     * @param n   数组元素个数
     */
    public void insertionSort(int[] arr, int n) {
        if (arr == null || n <= 1) {
            return;
        }
        for (int i = 1; i < n; i++) {
            int tmp = arr[i];
            int j = i - 1;
            // 查找要插入的位置并移动数据
            // 每次倒退着比较，从大到小找到第一个比自己小的数
            for (; j >= 0; j--) {
                if (arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = tmp;
        }
    }

    /**
     * 插入排序
     *
     * @param arr 等待排序的数组
     * @param n   数组元素个数
     */
    public void insertionSort2(int[] arr, int n) {
        for (int i = 1; i < n; i++) {
            int current = arr[i];
            int preIndex = i - 1;
            // 从后向前比较，找到第一个比当前值小的值
            while (preIndex >= 0 && current < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

}
