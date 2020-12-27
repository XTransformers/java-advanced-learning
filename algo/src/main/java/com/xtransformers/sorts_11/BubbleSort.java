package com.xtransformers.sorts_11;

/**
 * 冒泡排序
 */
public class BubbleSort {

    /**
     * 冒泡排序
     *
     * @param a 数组
     * @param n 数组大小
     */
    public void bubbleSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            // 提前退出冒泡循环的标志位
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    // 表示有数据交换
                    swapped = true;
                }
            }
            // 没有数据交换，提前退出
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * 交换数组指定下标数据
     *
     * @param a 数组
     * @param i 下标1
     * @param j 下标2
     */
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void bubbleSortAdvanced(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        // 最后一次交换的位置
        int lastExchange = 0;
        // 无序数据的边界,每次只需要比较到这里即可退出
        int board = n - 1;
        for (int i = 0; i < n; i++) {
            // 提前退出标志位
            boolean swapped = false;
            for (int j = 0; j < board; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    // 更新最后一次交换的位置
                    lastExchange = j;
                    // 此次冒泡有数据交换
                    swapped = true;
                }
            }
            board = lastExchange;
            // 没有数据交换，提前退出
            if (!swapped) {
                break;
            }
        }
    }

}
