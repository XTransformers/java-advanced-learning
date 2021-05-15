package com.xtransformers.chapter14.lazylist;

import java.util.function.Predicate;

/**
 * @author daniel
 * @date 2021-05-07
 */
public interface MyList<T> {
    T head();

    MyList<T> tail();

    default boolean isEmpty() {
        return true;
    }

    default MyList<T> filter(Predicate<T> predicate) {
        return isEmpty() ? this :
                predicate.test(head()) ?
                        new LazyList<>(head(), () -> tail().filter(predicate)) :
                        tail().filter(predicate);
    }
}
