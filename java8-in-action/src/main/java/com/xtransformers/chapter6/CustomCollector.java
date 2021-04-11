package com.xtransformers.chapter6;

import com.xtransformers.chapter2.abstr.Predicate;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class CustomCollector {

    // 优化点：只判断是不是能被质数整除
    public boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return takeWhile(primes, item -> item <= candidateRoot)
                .stream()
                .noneMatch(i -> candidate % i == 0);
    }

    /**
     * 返回满足谓词的最长前缀
     *
     * @param list      排序列表
     * @param predicate 谓词
     * @param <T>       泛型
     * @return 满足谓词的最长前缀
     */
    public static <T> List<T> takeWhile(List<T> list, Predicate<T> predicate) {
        int index = 0;
        for (T each : list) {
            if (!predicate.test(each)) {
                return list.subList(0, index);
            }
            index++;
        }
        return list;
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n)
                .boxed()
                .collect(new PrimeNumbersCollector());
    }

    public Map<Boolean, List<Integer>> partitionPrimes2(int n) {
        return IntStream.rangeClosed(2, n)
                .boxed()
                .collect(
                        () -> new HashMap<Boolean, List<Integer>>() {{
                            put(true, new ArrayList<>());
                            put(false, new ArrayList<>());
                        }},
                        (acc, candidate) -> {
                            acc.get(isPrime(acc.get(true), candidate))
                                    .add(candidate);
                        },
                        (map1, map2) -> {
                            map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));
                        }
                );
    }

    public static void main(String[] args) {
        // 293 447
        // 287 460
        CustomCollector cc = new CustomCollector();
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            cc.partitionPrimes(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        System.out.println("PrimeNumbersCollector Fastest execution done in " + fastest + " msecs");

        PartitionDemo pd = new PartitionDemo();
        fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            pd.partitionPrimes(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        System.out.println("partitioningBy Fastest execution done in " + fastest + " msecs");
    }

    private static void test() {
        // 123 153 131 135 156 137
        long start = System.currentTimeMillis();
        System.out.println(new CustomCollector().partitionPrimes(100));
        System.out.println(System.currentTimeMillis() - start);
    }

    public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> new HashMap<Boolean, List<Integer>>() {{
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }};
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (acc, candidate) ->
                    acc.get(isPrime(acc.get(true), candidate))
                            .add(candidate);
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
//            throw new UnsupportedOperationException();
            return (map1, map2) -> {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }
    }

}
