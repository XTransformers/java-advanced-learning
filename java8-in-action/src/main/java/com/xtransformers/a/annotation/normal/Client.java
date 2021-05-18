package com.xtransformers.a.annotation.normal;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @author daniel
 * @date 2021-05-18
 */
public class Client {
    public static void main(String[] args) {
        Authors[] authors = Book.class.getAnnotationsByType(Authors.class);
        Arrays.asList(authors).forEach(System.out::println);

        System.out.println("************");

        Annotation[] annotations = Book.class.getAnnotations();
        Arrays.asList(annotations).forEach(System.out::println);
    }
}
