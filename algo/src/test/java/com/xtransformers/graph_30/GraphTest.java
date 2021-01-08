package com.xtransformers.graph_30;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GraphTest {

    private Graph graph;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));

        graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
    }

    @After
    public void cleanUpStream() {
        System.setOut(null);
    }

    @Test
    public void hello() {
        System.out.print("hello");
        assertEquals("hello", outputStream.toString());
    }

    @Test
    public void bfs() {
        graph.bfs(0, 6);
        assertEquals("0 1 4 6 ", outputStream.toString());
        graph.bfs(0, 0);
    }

    @Test
    public void dfs() {
        graph.dfs(0, 6);
        assertEquals("0 1 2 5 4 6 ", outputStream.toString());
    }
}