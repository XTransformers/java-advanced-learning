package com.xtransformers.hashtable_20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基于散列表的LRU算法
 *
 * @param <K> 键
 * @param <V> 值
 */
public class LRUBaseHashTable<K, V> {

    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 头结点
     */
    private DNode<K, V> headNode;

    /**
     * 尾结点
     */
    private DNode<K, V> tailNode;

    /**
     * 链表长度
     */
    private int length;

    /**
     * 链表容量
     */
    private int capacity;

    /**
     * 散列表存储key
     */
    private HashMap<K, DNode<K, V>> table;

    public LRUBaseHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBaseHashTable(int capacity) {
        length = 0;
        this.capacity = capacity;

        headNode = new DNode<>();
        tailNode = new DNode<>();

        headNode.next = tailNode;
        tailNode.prev = headNode;

        this.table = new HashMap<>();
    }

    /**
     * 新增
     *
     * @param key   键
     * @param value 值
     */
    public void add(K key, V value) {
        DNode<K, V> node = table.get(key);
        if (node == null) {
            DNode<K, V> newNode = new DNode<>(key, value);
            table.put(key, newNode);
            addNodeToHead(newNode);

            if (++length > capacity) {
                DNode<K, V> tail = popTail();
                table.remove(tail.key);
                length--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 将新节点添加到头部
     * 使用哨兵节点
     *
     * @param newNode 新节点
     */
    private void addNodeToHead(DNode<K, V> newNode) {
        newNode.next = headNode.next;
        newNode.prev = headNode;

        headNode.next.prev = newNode;
        headNode.next = newNode;
    }

    /**
     * 弹出尾部数据节点
     *
     * @return 尾部节点
     */
    private DNode<K, V> popTail() {
        DNode<K, V> node = tailNode.prev;
        removeNode(node);
        return node;
    }

    /**
     * 移除节点
     *
     * @param node 将被移除的节点
     */
    private void removeNode(DNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 将节点移动到头部
     *
     * @param node 节点对象
     */
    private void moveToHead(DNode<K, V> node) {
        removeNode(node);
        addNodeToHead(node);
    }

    /**
     * 获取节点数据
     *
     * @param key 键
     * @return 获取到的值
     */
    public V get(K key) {
        DNode<K, V> node = table.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    /**
     * 移除节点数据
     *
     * @param key 键
     */
    public void remove(K key) {
        DNode<K, V> node = table.get(key);
        if (node == null) {
            return;
        }
        removeNode(node);
        length--;
        table.remove(node.key);
    }

    public String printAll() {
        List<V> list = new ArrayList<>();
        DNode<K, V> node = headNode.next;
        while (node.next != null) {
            list.add(node.value);
            node = node.next;
        }
        String result = list.stream().map(Object::toString)
                .collect(Collectors.joining(","));
        System.out.println(result);
        return result;
    }

    /**
     * 双向链表
     *
     * @param <K> 键
     * @param <V> 值
     */
    static class DNode<K, V> {

        private K key;

        private V value;

        /**
         * 前驱指针
         */
        private DNode<K, V> prev;

        /**
         * 后继指针
         */
        private DNode<K, V> next;

        DNode() {
        }

        DNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
