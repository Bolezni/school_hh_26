package com.example.yandexContest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<List<Integer>> graph;
    private static long[] treasures;
    private static boolean[] visited;
    private static long maxTreasure;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        long[] treasures = new long[n];
        for (int i = 0; i < n; i++) {
            treasures[i] = scanner.nextLong();
        }

        int[][] tunnels = new int[m][2];
        for (int i = 0; i < m; i++) {
            tunnels[i][0] = scanner.nextInt();
            tunnels[i][1] = scanner.nextInt();
        }

        long result = findMaxTreasure(n, treasures, tunnels);
        System.out.println(result);

        scanner.close();
    }

    public static long findMaxTreasure(int n, long[] treasureValues, int[][] tunnels) {
        treasures = treasureValues;
        maxTreasure = treasures[0];

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] tunnel : tunnels) {
            int u = tunnel[0] - 1;
            int v = tunnel[1] - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        visited = new boolean[n];
        dfs(0, treasures[0]);

        return maxTreasure;
    }

    private static void dfs(int island, long currentSum) {
        visited[island] = true;
        maxTreasure = Math.max(maxTreasure, currentSum);

        for (int next : graph.get(island)) {
            if (!visited[next]) {
                dfs(next, currentSum + treasures[next]);
            }
        }

        visited[island] = false;
    }
}
