package com.example.graphs;

import java.util.*;

public class Main {

    static class Graph{
        public int v;
        public List<List<Integer>> adj;

        public Graph(int v) {
            this.v = v;
            adj = new ArrayList<>(v);
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<>());
            }
        }

        public void addEdge(int v, int w) {
            adj.get(v).add(w);
        }
    }

    //Поиск циклов с помощью DFS
    public static boolean hasCycle(Graph graph) {
        int n = graph.v;
        boolean[] visited = new boolean[n];
        boolean[] recursionStack = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                if(hashCycleUtil(graph, i, visited, recursionStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hashCycleUtil(Graph graph, int v,
                                         boolean[] visited,
                                         boolean[] recursionStack) {
        visited[v] = true;
        recursionStack[v] = true;

        for(int w : graph.adj.get(v)) {
            if(!visited[w]) {
                if(hashCycleUtil(graph, w, visited, recursionStack)) {
                    return true;
                }
            }else if(recursionStack[w]) {
                return true;
            }
        }
        recursionStack[v] = false;
        return false;
    }

    //Поиск циклов с помощью BFS (топологическая сортировка)

    public static boolean hasCycleBFS(Graph graph) {
        int n = graph.v;
        int[] inDegree = new int[n];

        for(int i = 0; i < n; i++) {
            for(int w : graph.adj.get(i)) {
                inDegree[w]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        while(!queue.isEmpty()) {
            int v = queue.poll();
            count++;
            for(int w : graph.adj.get(v)) {
                if(--inDegree[w] == 0) {
                    queue.add(w);
                }
            }
        }
        // Если количество обработанных вершин меньше общего числа вершин, есть цикл
        return count != n;
    }

    //Поиск всех циклов в графе

    public static List<List<Integer>> findAllCycle(Graph graph) {
        int n = graph.v;
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[n];
        boolean[] inStack = new boolean[n];

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                findAllCyclesUtil(graph, i, visited, inStack, parent, res);
            }
        }
        return res;
    }
    private static void findAllCyclesUtil(Graph graph, int v,
                                          boolean[] visited,
                                          boolean[] inStack,
                                          int[] parent,
                                          List<List<Integer>> cycles) {
        visited[v] = true;
        inStack[v] = true;

        for (Integer neighbor : graph.adj.get(v)) {
            if (!visited[neighbor]) {
                parent[neighbor] = v;
                findAllCyclesUtil(graph, neighbor, visited, inStack, parent, cycles);
            } else if (inStack[neighbor]) {
                // Найден цикл
                List<Integer> cycle = new ArrayList<>();
                int current = v;
                while (current != neighbor) {
                    cycle.add(current);
                    current = parent[current];
                }
                cycle.add(neighbor);
                cycle.add(v); // Замыкаем цикл
                Collections.reverse(cycle);
                cycles.add(cycle);
            }
        }

        inStack[v] = false;
    }
}
