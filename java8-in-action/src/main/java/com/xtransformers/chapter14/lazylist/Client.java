package com.xtransformers.chapter14.lazylist;

import org.junit.Test;

/**
 * @author daniel
 * @date 2021-05-07
 */
public class Client {

    public void test1() {
        MyLinkedList<Integer> list = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    @Test
    public void test2() {
        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();
        System.out.println(two + " " + three + " " + four);
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(),
                () -> primes(
                        numbers.tail().filter(n -> n % numbers.head() != 0)
                ));
    }

    @Test
    public void test3() {
        LazyList<Integer> numbers = from(2);
        int two = primes(numbers).head();
        int three = primes(numbers).tail().head();
        int five = primes(numbers).tail().tail().head();
        System.out.println(two + " " + three + " " + five);
    }

    public static <T> void printAll(MyList<T> list) {
        while (list != null) {
            System.out.println(list.head());
            list = list.tail();
        }
    }

    @Test
    public void test4() {
        /**
         * printAll 方法会递归地打印输出列表的头尾元素，这个程序会永久地运行下去
         */
        printAll(from(2));
    }

    public static <T> void printAll2(MyList<T> list) {
        if (list == null) return;
        System.out.println(list.head());
        printAll2(list.tail());
    }

    @Test
    public void test5() {
        /**
         * java.lang.StackOverflowError
         * 这个程序不会永久地运行下去；
         * 它最终会由于栈溢出而失效，因为Java不支持尾部调用消除（tail callelimination）
         *
         */
        printAll2(from(2));
    }

    /**
     * 打印所有素数
     * 错误姿势
     * java.lang.StackOverflowError
     * 1201
     *
     * @param list
     */
    public static void printPrimes(MyList<Integer> list) {
        while (list != null) {
            System.out.println(list.head());
            list = primes(list.tail());
        }
    }

    @Test
    public void test6() {
        printPrimes(from(2));
    }

    /**
     * 打印所有素数
     * 正确姿势
     */
    @Test
    public void test7() {
        printAll(primes(from(2)));
    }
}
