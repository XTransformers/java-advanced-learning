package com.xtransformers.a.annotation.repeatable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;

/**
 * 1. 将注解标记为 @Repeatable
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Repeatable(Authors.class)
public @interface Author {
    String name();
}
