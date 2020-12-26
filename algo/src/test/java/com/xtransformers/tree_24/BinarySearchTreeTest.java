package com.xtransformers.tree_24;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BinarySearchTreeTest {

    private BinarySearchTree<String> tree;

    @Before
    public void setUp() {
        tree = new BinarySearchTree<>();
        tree.insert("b");
        tree.insert("a");
        tree.insert("d");
        tree.insert("c");
    }

    @Test
    public void find() {
        BinarySearchTree.Node<String> result = tree.find("c");
        assertNotNull(result);
        assertEquals("c", result.getData());

        result = tree.find("z");
        assertNull(result);
    }

    @Test
    public void insert() {
        tree.insert("g");
        tree.insert("f");
        tree.insert("e");
        tree.insert("h");
        tree.insert("i");

        BinarySearchTree.Node<String> result = tree.find("z");
        assertNull(result);

        tree.insert("z");

        result = tree.find("z");

        assertNotNull(result);
        assertEquals("z", result.getData());
    }

    @Test
    public void delete() {
        tree.insert("g");
        tree.insert("f");
        tree.insert("e");
        tree.insert("h");
        tree.insert("i");

        tree.delete("b");
        BinarySearchTree.Node<String> result = tree.find("b");
        assertNull(result);

        result = tree.find("c");
        assertNotNull(result);
        assertEquals("c", result.getData());

        tree.delete("c");

        result = tree.find("c");
        assertNull(result);

        tree.delete("c");
        result = tree.find("c");
        assertNull(result);
    }

    @Test
    public void findMin() {
        BinarySearchTree.Node<String> result = tree.findMin();
        assertNotNull(result);
        assertEquals("a", result.getData());

        tree = new BinarySearchTree<>();
        result = tree.findMin();
        assertNull(result);
    }

    @Test
    public void findMax() {
        BinarySearchTree.Node<String> result = tree.findMax();
        assertNotNull(result);
        assertEquals("d", result.getData());

        tree = new BinarySearchTree<>();
        result = tree.findMax();
        assertNull(result);
    }

    @Test
    public void printInOrder() {
        String result = tree.printInOrder();
        assertEquals("a,b,c,d", result);
    }
}