package com.example.graphs;

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
            if(state[i] != 0) continue;

            Deque<int[]> stack = new ArrayDeque<>();
            stack.push(new int[] {i, 0});
            state[i] = 1;

            while (!stack.isEmpty()) {
                int[] top = stack.pop();
                int v = top[0];
                int idx = top[1]; //сосед для обхода

                if(idx < adj.get(v).size()){
                    top[1]++; //двигаем итератор
                    int neighbor = adj.get(v).get(idx);

                    if(state[neighbor] == 1){
                        throw new RuntimeException("Граф содержит цикл — топосортировка невозможна");
                    }
                    if(state[neighbor] == 0){
                        state[neighbor] = 1;
                        stack.push(new int[] {neighbor, 0});
                    }
                }else{
                    // Все соседи обработаны — кладём в результат
                    stack.pop();
                    state[v] = 2;
                    res.push(v);// prepend через стек
                }
            }
        }
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        // A=0, B=1, C=2, D=3, E=4, F=5
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(0, 1); // A→B
        g.addEdge(0, 2); // A→C
        g.addEdge(1, 3); // B→D
        g.addEdge(2, 4); // C→E
        g.addEdge(3, 5); // D→F
        g.addEdge(4, 5); // E→F

        List<Integer> order = g.topologicalSort();
        System.out.println(order); // [0, 2, 4, 1, 3, 5] или аналог
    }
}
