package com.xtransformers.bloomfilter.custom;

import org.junit.Assert;
import org.junit.Test;

public class CustomBloomFilterTest {

    @Test
    public void test() {
        CustomBloomFilter bloomFilter = new CustomBloomFilter();
        String url = "https://github.com/XTransformers/java-advanced-learning";
        Assert.assertFalse(bloomFilter.contains(url));
        bloomFilter.add(url);
        Assert.assertTrue(bloomFilter.contains(url));
    }

}