package com.xtransformers.graph_30;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * 图
 */
public class Graph {

    private int capacity;

    // 邻接表
    private LinkedList<Integer>[] adj;

    public Graph(int capacity) {
        this.capacity = capacity;
        adj = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 添加边
     * 无向图，一条边添加 2 次
     *
     * @param s 源顶点
     * @param t 目标顶点
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 图的广度优先搜索 Breadth-First-Search
     * 搜索一条从 s 到 t 的路径，从 s 到 t 的最短路径
     *
     * @param s 起始顶点
     * @param t 终止顶点
     */
    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }

        // visited 用来记录已经被访问的顶点，用来避免顶点被重复访问
        boolean[] visited = new boolean[capacity];
        visited[s] = true;
        // queue 队列，用来存储已经被访问到、但相连的顶点还没有被访问的顶点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        // prev 用来记录搜索路径
//        int[] prev = new int[capacity];
//        for (int i = 0; i < capacity; i++) {
//            prev[i] = -1;
//        }
        int[] prev = IntStream.generate(() -> -1).limit(capacity).toArray();
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    // 全局变量或类成员变量
    boolean found = false;

    /**
     * 图的深度优先搜索
     *
     * @param s 起始顶点
     * @param t 终点顶点
     */
    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[capacity];
        int[] prev = IntStream.generate(() -> -1).limit(capacity).toArray();
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found) {
            return;
        }
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); i++) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }

}
