package com.xtransformers.sorts_38;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeapSortTest {

    private int[] a;
    private int[] b;
    private int[] sorted;
    private HeapSort heapSort;

    @Before
    public void setUp() {
        a = new int[]{2, 1, 5, 6, 8, 4, 12, 11, 13, 15, 7, 9, 0, -1};
        b = new int[]{9};
        sorted = new int[]{-1, 0, 1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 15};
        heapSort = new HeapSort();
    }

    @Test
    public void sort() {
        heapSort.sort(a);
        assertArrayEquals(sorted, a);

        heapSort.sort(b);
        assertArrayEquals(b, b);
    }
}