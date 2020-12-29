package com.xtransformers.sorts_12;

/**
 * 归并排序
 */
public class MergeSort {

    public void mergeSort(int[] arr, int n) {
        mergeSortInternally(arr, 0, n - 1);
    }

    /**
     * 递归调用函数
     *
     * @param arr 待排序数组
     * @param p   数组起始下标
     * @param r   数组结束下标
     */
    private void mergeSortInternally(int[] arr, int p, int r) {
        // 递归终止条件
        if (p >= r) {
            return;
        }
        // 取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值
        int q = p + (r - p) / 2;
        // 分治递归
        mergeSortInternally(arr, p, q);
        mergeSortInternally(arr, q + 1, r);

        // 将A[p...q]和A[q+1...r]合并为A[p...r]
        mergeBySentry(arr, p, q, r);
    }

    /**
     * 合并，使用哨兵节点
     *
     * @param arr 待合并数组
     * @param p   数组起始下标
     * @param q   数组中点下标
     * @param r   数组结束下标
     */
    private void mergeBySentry(int[] arr, int p, int q, int r) {
        int[] leftArr = new int[q - p + 1 + 1];
        int[] rightArr = new int[r - (q + 1) + 1 + 1];
        // 第一个数组添加哨兵（最大值）
        leftArr[q - p + 1] = Integer.MAX_VALUE;
        // 第二个数组添加哨兵（最大值）
        rightArr[r - q] = Integer.MAX_VALUE;
        for (int i = 0; i < leftArr.length - 1; i++) {
            leftArr[i] = arr[p + i];
        }
        for (int i = 0; i < rightArr.length - 1; i++) {
            rightArr[i] = arr[q + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = p;
        while (k <= r) {
            // 当左边数组到达哨兵值时，i不再增加，直到右边数组读取完剩余值，同理右边数组也一样
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
    }

}
