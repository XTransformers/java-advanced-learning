package com.xtransformers.a.annotation.normal;

import java.lang.annotation.*;

/**
 * @author daniel
 * @date 2021-05-17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Author {
    String name();
}
