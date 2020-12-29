package com.xtransformers.sorts_12;

/**
 * 快速排序
 * 简称 快排
 */
public class QuickSort {

    public void quickSort(int[] arr, int n) {
        quickSortInternally(arr, 0, n - 1);
    }

    /**
     * 快排递归方法
     *
     * @param arr 数组
     * @param p   数组开始下标
     * @param r   数组结束下标
     */
    private void quickSortInternally(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = partition(arr, p, r);
        quickSortInternally(arr, p, q - 1);
        quickSortInternally(arr, q + 1, r);
    }

    /**
     * 获取分区点
     * 一般情况下，可以选择 p 到 r 区间的最后一个元素
     *
     * @param arr 数组
     * @param p   数组起始下标
     * @param r   数组结束下标
     * @return 分区点
     */
    private int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (arr[j] < pivot) {
                if (i != j) {
                    swap(arr, i, j);
                }
                i++;
            }
        }
        swap(arr, i, r);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
