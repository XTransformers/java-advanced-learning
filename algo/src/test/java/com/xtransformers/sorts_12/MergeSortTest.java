package com.xtransformers.sorts_12;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortTest {

    private int[] a;
    private int[] b;
    private int[] sorted;
    private MergeSort mergeSort;

    @Before
    public void setUp() {
        a = new int[]{3, 4, 2, 1, 7, 8, 5, 6};
        b = new int[]{9};
        sorted = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        mergeSort = new MergeSort();
    }

    @Test
    public void mergeSort() {
        mergeSort.mergeSort(a, a.length);
        assertArrayEquals(sorted, a);

        mergeSort.mergeSort(b, b.length);
        assertArrayEquals(b, b);
    }
}
