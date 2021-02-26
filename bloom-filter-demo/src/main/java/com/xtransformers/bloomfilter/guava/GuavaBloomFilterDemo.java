package com.xtransformers.bloomfilter.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

public class GuavaBloomFilterDemo {

    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = BloomFilter.create(
                Funnels.stringFunnel(Charset.defaultCharset()),
                10_000,
                0.001
        );
        
        /**
         * 当 mightContain() 方法
         * 返回 true 时，我们可以 99.9％ 确定该元素在过滤器中；
         * 返回 false 时，我们可以 100％ 确定该元素不存在于过滤器中；
         */
        String url = "https://github.com/XTransformers/java-advanced-learning";
        System.out.println(bloomFilter.mightContain(url));
        bloomFilter.put(url);
        System.out.println(bloomFilter.mightContain(url));
    }
}
