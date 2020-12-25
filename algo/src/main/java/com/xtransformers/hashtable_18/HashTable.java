package com.xtransformers.hashtable_18;

import java.util.Objects;

/**
 * 散列表实现
 *
 * @param <K>
 * @param <V>
 */
public class HashTable<K, V> {

    /**
     * 散列表默认长度
     */
    private static final int DEFAULT_CAPACITY = 8;

    /**
     * 装载因子
     */
    private static final float LOAD_FACTOR = 0.75F;

    /**
     * 实际元素数量
     */
    private int size;

    /**
     * 散列表索引数量
     */
    private int use;

    /**
     * 初始化散列表数组
     */
    private Entry<K, V>[] table;

    @SuppressWarnings(value = "unchecked")
    public HashTable() {
        table = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
    }

    /**
     * 新增
     *
     * @param key   键
     * @param value 值
     */
    public void put(K key, V value) {
        int index = hash(key);
        // 位置未被引用，创建哨兵节点
        if (table[index] == null) {
            table[index] = new Entry<>(null, null, null);
        }

        Entry<K, V> tmp = table[index];
        if (tmp.next == null) {
            // 新增节点
            tmp.next = new Entry<>(key, value, null);
            size++;
            use++;
            // 动态扩容
            if (use >= table.length * LOAD_FACTOR) {
                resize();
            }
        } else {
            // 使用链表法，解决散列冲突
            do {
                tmp = tmp.next;
                // key相同，覆盖
                if (Objects.equals(tmp.key, key)) {
                    tmp.value = value;
                    return;
                }
            } while (tmp.next != null);
            Entry<K, V> next = table[index].next;
            table[index].next = new Entry<>(key, value, next);
            size++;
        }

    }

    /**
     * 散列函数
     * 参考hashmap散列函数
     *
     * @param key 键
     * @return 散列值
     */
    private int hash(K key) {
        int h;
        return (key == null) ? 0 : ((h = key.hashCode()) ^ (h >>> 16)) % table.length;
    }

    /**
     * 扩容
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[]) new Entry[table.length * 2];
        use = 0;
        for (Entry<K, V> each : oldTable) {
            if (each == null || each.next == null) {
                continue;
            }
            Entry<K, V> e = each;
            while (e.next != null) {
                e = e.next;
                int index = hash(e.key);
                if (table[index] == null) {
                    use++;
                    table[index] = new Entry<>(null, null, null);
                }
                table[index].next = new Entry<>(e.key, e.value, table[index].next);
            }
        }

    }

    /**
     * 删除
     *
     * @param key 键
     */
    public void remove(K key) {
        int index = hash(key);
        Entry<K, V> e = table[index];
        if (e == null || e.next == null) {
            return;
        }
        Entry<K, V> pre;
        Entry<K, V> headNode = table[index];
        do {
            pre = e;
            e = e.next;
            if (Objects.equals(key, e.key)) {
                pre.next = e.next;
                size--;
                if (headNode.next == null) {
                    use--;
                }
                return;
            }
        } while (e.next != null);
    }

    /**
     * 获取
     *
     * @param key 键
     * @return 查找到的值
     */
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> e = table[index];
        if (e == null || e.next == null) {
            return null;
        }
        while (e.next != null) {
            e = e.next;
            if (Objects.equals(key, e.key)) {
                return e.value;
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    static class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


}
