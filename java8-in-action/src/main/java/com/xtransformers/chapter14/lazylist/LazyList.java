package com.xtransformers.chapter14.lazylist;

import java.util.function.Supplier;

/**
 * @author daniel
 * @date 2021-05-07
 */
public class LazyList<T> implements MyList<T> {

    private final T head;

    private final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        // tail使用了一个 Supplier 方法提供了延迟性
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
