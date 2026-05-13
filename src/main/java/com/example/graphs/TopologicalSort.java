package com.example.graphs;

import java.io.*;
import java.util.*;

public class TopologicalSort {
    private final int vertices;

    private final List<List<Integer>> adj;

    public TopologicalSort(int vertices) {
        this.vertices = vertices;
        this.adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to) {
        adj.get(from).add(to);
    }

    public List<Integer> topologicalSort() {
        int[] state = new int[vertices];
        Deque<Integer> res = new LinkedList<>();

        for (int i = 0; i < vertices; i++) {
            if (state[i] != 0) continue;

            Deque<int[]> stack = new ArrayDeque<>();
            stack.push(new int[]{i, 0});
            state[i] = 1;

            while (!stack.isEmpty()) {
                int[] top = stack.peek();
                int v = top[0];
                int idx = top[1]; //сосед для обхода

                if (idx < adj.get(v).size()) {
                    top[1]++; //двигаем итератор
                    int neighbor = adj.get(v).get(idx);

                    if (state[neighbor] == 1) {
                        throw new RuntimeException("Граф содержит цикл — топосортировка невозможна");
                    }
                    if (state[neighbor] == 0) {
                        state[neighbor] = 1;
                        stack.push(new int[]{neighbor, 0});
                    }
                } else {
                    // Все соседи обработаны — кладём в результат
                    stack.pop();
                    state[v] = 2;
                    res.push(v);// prepend через стек
                }
            }
        }
        return new ArrayList<>(res);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int V = scanner.nextInt(); // кол-во вершин
        int E = scanner.nextInt(); // кол-во рёбер

        TopologicalSort g = new TopologicalSort(V);

        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt() - 1; // переводим в 0-индексацию
            int v = scanner.nextInt() - 1;
            g.addEdge(u, v);
        }

        List<Integer> order = g.topologicalSort();

        StringBuilder sb = new StringBuilder();
        for (int node : order) {
            sb.append(node + 1).append(" "); // обратно в 1-индексацию
        }
        System.out.println(sb.toString().trim());
    }
}
