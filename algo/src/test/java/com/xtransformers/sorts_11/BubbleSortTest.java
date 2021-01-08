package com.xtransformers.sorts_11;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BubbleSortTest {

    private int[] a;
    private int[] b;
    private int[] sorted;
    private BubbleSort bubbleSort;

    @Before
    public void setUp() {
        a = new int[]{3, 4, 2, 1, 7, 8, 5, 6};
        b = new int[]{9};
        sorted = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        bubbleSort = new BubbleSort();
    }

    @Test
    public void bubbleSort() {
        bubbleSort.bubbleSort(a, a.length);
        assertArrayEquals(sorted, a);

        bubbleSort.bubbleSort(b, b.length);
        assertArrayEquals(b, b);
    }

    @Test
    public void bubbleSortAdvanced() {
        bubbleSort.bubbleSortAdvanced(a, a.length);
        assertArrayEquals(sorted, a);

        bubbleSort.bubbleSortAdvanced(b, b.length);
        assertArrayEquals(b, b);
    }
}