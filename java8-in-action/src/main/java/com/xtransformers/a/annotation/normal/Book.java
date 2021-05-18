package com.xtransformers.a.annotation.normal;

/**
 * Duplicate annotation.
 * The declaration of 'com.xtransformers.a.annotation.normal.Author'
 * does not have a valid java.lang.annotation.Repeatable annotation
 *
 * @author daniel
 * @date 2021-05-17
 */
//@Author(name = "Raoul")
//@Author(name = "Mario")
@Authors({@Author(name = "Raoul"), @Author(name = "Mario"), @Author(name = "Alan")})
public class Book {
}
