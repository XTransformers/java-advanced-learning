package com.xtransformers.chapter14.lazylist;

/**
 * @author daniel
 * @date 2021-05-07
 */
public class MyLinkedList<T> implements MyList<T> {

    private final T head;

    private final MyList<T> tail;

    public MyLinkedList(T head, MyList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
