package com.xtransformers.tree_24;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> tree;

    /**
     * 查找数据
     *
     * @param data 数据值
     * @return 查找到节点，如果没有查找到返回null
     */
    public Node<T> find(T data) {
        Node<T> p = tree;
        while (p != null) {
            if (p.data.compareTo(data) > 0) {
                p = p.left;
            } else if (p.data.compareTo(data) < 0) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    /**
     * 插入数据
     *
     * @param data 数据值
     */
    public void insert(T data) {
        if (tree == null) {
            tree = new Node<>(data);
            return;
        }
        Node<T> p = tree;
        while (p != null) {
            if (p.data.compareTo(data) < 0) {
                if (p.right == null) {
                    p.right = new Node<>(data);
                    return;
                }
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new Node<>(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    /**
     * 删除节点
     *
     * @param data 删除值
     */
    public void delete(T data) {
        // p指向要删除的节点，初始化指向根节点
        Node<T> p = tree;
        // pp记录的是p的父节点
        Node<T> pp = null;
        // 查找data节点是否存在
        while (p != null && !Objects.equals(data, p.data)) {
            pp = p;
            if (p.data.compareTo(data) < 0) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        // 没有找到
        if (p == null) {
            return;
        }

        // 要删除的节点有两个子节点
        if (p.left != null && p.right != null) {
            // 查找右子树中最小节点
            Node<T> minP = p.right;
            // minPP表示minP的父节点
            Node<T> minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            // 将minP的数据替换到p中
            p.data = minP.data;
            // 下面就变成了删除minP了
            p = minP;
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        // p的子节点
        Node<T> child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        // 删除的是根节点
        if (pp == null) {
            tree = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else if (pp.right == p) {
            pp.right = child;
        }
    }

    /**
     * 查找最小节点
     *
     * @return 最小节点
     */
    public Node<T> findMin() {
        if (tree == null) {
            return null;
        }
        Node<T> p = tree;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    /**
     * 查找最大节点
     *
     * @return 最大节点
     */
    public Node<T> findMax() {
        if (tree == null) {
            return null;
        }
        Node<T> p = tree;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    /**
     * 打印所有节点
     *
     * @return 所有节点使用, 拼接
     */
    public String printInOrder() {
        List<T> list = new ArrayList<>();
        inOrder(tree, list);
        return list.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    private void inOrder(Node<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.data);
        inOrder(node.right, list);
    }

    public static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

}
