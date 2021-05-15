package com.xtransformers.chapter14.tree;

/**
 * @author daniel
 * @date 2021-05-06
 */
public class TreeProcessor {

    public static int lookup(String key, int defaultVal, Tree t) {
        if (t == null) {
            return defaultVal;
        }
        if (key.equals(t.getKey())) {
            return t.getVal();
        }
        // return key.compareTo(t.getKey()) < 0 ? lookup(key, defaultVal, t.getLeft()) : lookup(key, defaultVal, t.getRight());
        return lookup(key, defaultVal, key.compareTo(t.getKey()) < 0 ? t.getLeft() : t.getRight());
    }

    public static void update(String key, int newVal, Tree t) {
        if (t == null) {
            // 新增节点
        } else if (t.getKey().equals(key)) {
            t.setVal(newVal);
        } else {
            update(key, newVal, key.compareTo(t.getKey()) < 0 ? t.getLeft() : t.getRight());
        }
    }

    public static Tree updateTree(String key, int newVal, Tree t) {
        if (t == null) {
            t = new Tree(key, newVal, null, null);
        } else if (key.equals(t.getKey())) {
            t.setVal(newVal);
        } else if (key.compareTo(t.getKey()) < 0) {
            t = updateTree(key, newVal, t.getLeft());
        } else {
            t = updateTree(key, newVal, t.getRight());
        }
        return t;
    }

    public static Tree functionUpdate(String key, int newVal, Tree t) {
        return t == null ? new Tree(key, newVal, null, null) :
                key.equals(t.getKey()) ? new Tree(key, newVal, t.getLeft(), t.getRight()) :
                        key.compareTo(t.getKey()) < 0 ?
                                new Tree(key, newVal, functionUpdate(key, newVal, t.getLeft()), t.getRight()) :
                                new Tree(key, newVal, t.getLeft(), functionUpdate(key, newVal, t.getRight()));
    }

}
