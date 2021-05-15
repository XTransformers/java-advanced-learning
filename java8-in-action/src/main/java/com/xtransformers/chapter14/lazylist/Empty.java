package com.xtransformers.chapter14.lazylist;

/**
 * @author daniel
 * @date 2021-05-07
 */
public class Empty<T> implements MyList<T> {

    @Override
    public T head() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> tail() {
        throw new UnsupportedOperationException();
    }
}
