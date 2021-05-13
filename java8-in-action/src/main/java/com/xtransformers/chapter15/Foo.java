package com.xtransformers.chapter15;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author daniel
 * @date 2021-05-11
 */
public class Foo {

    public static void main(String[] args) {
        IntStream.rangeClosed(2, 6)
                .forEach(n -> System.out.println("Hello " + n + " bottles of bear"));

        Map<String, Integer> authorToAge = new HashMap<>();
        authorToAge.put("Raoul", 23);
        authorToAge.put("Mario", 40);
        authorToAge.put("Alan", 53);
    }
}
