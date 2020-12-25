package com.xtransformers.hashtable_20;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

// 按照名称字母升序执行
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LRUBaseHashTableTest {

    LRUBaseHashTable<String, String> lruBaseHashTable;

    @Before
    public void before() {
        lruBaseHashTable = new LRUBaseHashTable<>();
        lruBaseHashTable.add("a", "1");
        lruBaseHashTable.add("b", "2");
        lruBaseHashTable.add("c", "3");
        lruBaseHashTable.add("d", "4");
    }

    @Test
    public void add() {
        lruBaseHashTable.add("e", "5");
        lruBaseHashTable.add("f", "6");
        lruBaseHashTable.add("g", "7");
        lruBaseHashTable.add("h", "8");
        lruBaseHashTable.add("i", "9");
        lruBaseHashTable.add("j", "10");
        lruBaseHashTable.add("k", "11");
        String result = lruBaseHashTable.printAll();
        Assert.assertEquals("11,10,9,8,7,6,5,4,3,2", result);
        lruBaseHashTable.add("b", "2");
        result = lruBaseHashTable.printAll();
        Assert.assertEquals("2,11,10,9,8,7,6,5,4,3", result);
    }

    @Test
    public void get() {
        String result = lruBaseHashTable.get("b");
        Assert.assertEquals("2", result);
        result = lruBaseHashTable.get("z");
        Assert.assertNull(result);
    }

    @Test
    public void printAll() {
        String result = lruBaseHashTable.printAll();
        Assert.assertEquals("4,3,2,1", result);
    }

    @Test
    public void remove() {
        lruBaseHashTable.remove("c");
        String result = lruBaseHashTable.printAll();
        Assert.assertEquals("4,2,1", result);
        lruBaseHashTable.remove("x");
        result = lruBaseHashTable.printAll();
        Assert.assertEquals("4,2,1", result);
    }
}