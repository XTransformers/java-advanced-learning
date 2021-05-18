package com.xtransformers.a.annotation.repeatable;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @author daniel
 * @date 2021-05-17
 */
public class Client {
    public static void main(String[] args) {
        Author[] author = Book.class.getAnnotationsByType(Author.class);
        Arrays.asList(author)
                .forEach(a -> System.out.println(a.name()));

        System.out.println("***************");

        Authors[] authors = Book.class.getAnnotationsByType(Authors.class);
        Arrays.asList(authors)
                .forEach(System.out::println);

        System.out.println("***************");

        Annotation[] anno = Book.class.getAnnotations();
        Arrays.asList(anno)
                .forEach(a -> System.out.println(a));

        /**
         * Raoul
         * Mario
         * Alan
         * ***************
         * @com.xtransformers.a.annotation.repeatable.Authors(value=[@com.xtransformers.a.annotation.repeatable.Author(name=Raoul), @com.xtransformers.a.annotation.repeatable.Author(name=Mario), @com.xtransformers.a.annotation.repeatable.Author(name=Alan)])
         * ***************
         * @com.xtransformers.a.annotation.repeatable.Authors(value=[@com.xtransformers.a.annotation.repeatable.Author(name=Raoul), @com.xtransformers.a.annotation.repeatable.Author(name=Mario), @com.xtransformers.a.annotation.repeatable.Author(name=Alan)])
         */
    }
}
