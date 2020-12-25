package com.xtransformers.hashtable_18;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashTableTest {

    HashTable<String, String> hashTable;

    @Before
    public void setUp() throws Exception {
        hashTable = new HashTable<>();
        hashTable.put("a", "1");
        hashTable.put("b", "2");
        hashTable.put("c", "3");
        hashTable.put("d", "4");
    }

    @Test
    public void put() {
        hashTable.put("e", "5");
        hashTable.put("f", "6");
        hashTable.put("g", "7");
        hashTable.put("a", "11");
        Assert.assertEquals(7, hashTable.getSize());
    }

    @Test
    public void remove() {
        hashTable.put("i", "9");
        hashTable.remove("a");
        Assert.assertEquals(4, hashTable.getSize());
        hashTable.remove("i");
        Assert.assertEquals(3, hashTable.getSize());
        hashTable.remove("e");
        Assert.assertEquals(3, hashTable.getSize());
    }

    @Test
    public void get() {
        String result = hashTable.get("a");
        Assert.assertEquals("1", result);
        result = hashTable.get("e");
        Assert.assertNull(result);
        result = hashTable.get("i");
        Assert.assertNull(result);

    }

    @Test
    public void getSize() {
        int size = hashTable.getSize();
        Assert.assertEquals(4, size);
    }
}