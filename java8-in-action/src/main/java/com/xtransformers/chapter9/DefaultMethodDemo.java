package com.xtransformers.chapter9;

import java.util.*;

public class DefaultMethodDemo {

    public void test1() {
        List<Integer> numbers = Arrays.asList(3, 5, 2, 7, 1, 10);
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);
    }

    public static void main(String[] args) {
        DefaultMethodDemo defaultMethodDemo = new DefaultMethodDemo();
        defaultMethodDemo.test1();
        InterfaceStaticMethod.test();
        defaultMethodDemo.test2();
    }

    public void test2() {
        Arrays.asList(1, 3, 4, 2, 9);
        List<Integer> numbers = new ArrayList<>();
        numbers.add(9);
        numbers.add(2);
        numbers.add(5);
        numbers.add(3);
        numbers.add(1);
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (Objects.equals(next, 2)) {
                iterator.remove();
            }
        }
        System.out.println(numbers);
    }

}
