package com.xtransformers.sorts_11;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class InsertionSortTest {

    private int[] a;
    private int[] b;
    private int[] sorted;
    private InsertionSort insertionSort;

    @Before
    public void setUp() {
        a = new int[]{3, 4, 2, 1, 7, 8, 5, 6};
        b = new int[]{9};
        sorted = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        insertionSort = new InsertionSort();
    }

    @Test
    public void insertionSort() {
        insertionSort.insertionSort(a, a.length);
        assertArrayEquals(sorted, a);

        insertionSort.insertionSort(b, b.length);
        assertArrayEquals(b, b);
    }

    @Test
    public void insertionSort2() {
        insertionSort.insertionSort2(a, a.length);
        assertArrayEquals(sorted, a);

        insertionSort.insertionSort2(b, b.length);
        assertArrayEquals(b, b);
    }

}