package com.xtransformers.bloomfilter.custom;

import java.util.BitSet;

/**
 * 自定义布隆过滤器 Bloom Filter
 * 为了更加清楚的明白原理
 *
 * @author hellboy0621
 */
public class CustomBloomFilter {

    /**
     * 位数组默认大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;

    /**
     * 位数组
     */
    private BitSet bits = new BitSet(DEFAULT_SIZE);

    /**
     * 种子数组，用于初始化创建哈希函数
     */
    private static final int[] SEEDS = new int[]{3, 5, 13, 53, 61, 136};

    /**
     * 存放 Hash 函数的数组
     */
    private SimpleHashFunction[] functions = new SimpleHashFunction[SEEDS.length];

    public CustomBloomFilter() {
        // 初始化 hash 函数
        for (int i = 0; i < SEEDS.length; i++) {
            functions[i] = new SimpleHashFunction(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组
     *
     * @param obj 元素对象
     */
    public void add(Object obj) {
        for (SimpleHashFunction func : functions) {
            bits.set(func.hash(obj), true);
        }
    }

    /**
     * 判断指定元素是否存在于位数组中
     *
     * @param obj 元素对象
     * @return 存在返回 true，不存在返回 false
     */
    public boolean contains(Object obj) {
        boolean result = true;
        for (SimpleHashFunction function : functions) {
            result = result && bits.get(function.hash(obj));
        }
        return result;
    }

    private static class SimpleHashFunction {
        private int capacity;
        private int seed;

        private SimpleHashFunction(int capacity, int seed) {
            this.capacity = capacity;
            this.seed = seed;
        }

        /**
         * 计算 hash 值
         *
         * @param obj 需要计算 hash 值的对象
         * @return hash 值
         */
        private int hash(Object obj) {
            int h;
            return obj == null ? 0 : Math.abs(seed * (capacity - 1)) & ((h = obj.hashCode()) ^ (h >>> 16));
        }
    }

}
