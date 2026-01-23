package com.example.yandexContest;

import java.io.*;
import java.util.*;

public class DataCenters {
    static int n, k;
    static List<Integer>[] g;
    static int[] tin, low, comp;
    static int timer = 0;
    static List<int[]> edges = new ArrayList<>();
    static List<int[]> bridges = new ArrayList<>();
    static Set<Long> bridgeSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges.add(new int[]{u, v});
            g[u].add(v);
            g[v].add(u);
        }

        tin = new int[n + 1];
        low = new int[n + 1];
        Arrays.fill(tin, -1);

        for (int i = 1; i <= n; i++) {
            if (tin[i] == -1) dfsBridge(i, -1);
        }

        for (int[] e : bridges) {
            long key1 = ((long) e[0]) * n + e[1];
            long key2 = ((long) e[1]) * n + e[0];
            bridgeSet.add(key1);
            bridgeSet.add(key2);
        }

        comp = new int[n + 1];
        Arrays.fill(comp, -1);
        int compCnt = 0;
        for (int i = 1; i <= n; i++) {
            if (comp[i] == -1) {
                compCnt++;
                dfsComp(i, compCnt);
            }
        }

        List<Integer>[] tree = new ArrayList[compCnt + 1];
        for (int i = 1; i <= compCnt; i++) tree[i] = new ArrayList<>();
        for (int[] e : bridges) {
            int u = comp[e[0]], v = comp[e[1]];
            tree[u].add(v);
            tree[v].add(u);
        }

        int leafCount = 0;
        for (int i = 1; i <= compCnt; i++) {
            if (tree[i].size() == 1) leafCount++;
        }

        int m = (leafCount + 1) / 2;
        System.out.println(m);

        List<Integer> leaves = new ArrayList<>();
        for (int i = 1; i <= compCnt; i++) {
            if (tree[i].size() == 1) leaves.add(i);
        }

        for (int i = 0; i < leaves.size() / 2; i++) {
            int c1 = leaves.get(2 * i);
            int c2 = leaves.get(2 * i + 1);
            System.out.println(getAnyVertex(c1) + " " + getAnyVertex(c2));
        }

        if (leaves.size() % 2 == 1 && leaves.size() > 0) {
            int c1 = leaves.get(leaves.size() - 1);
            int c2 = leaves.get(0);
            System.out.println(getAnyVertex(c1) + " " + getAnyVertex(c2));
        }
    }

    static void dfsBridge(int v, int p) {
        tin[v] = low[v] = ++timer;
        for (int to : g[v]) {
            if (to == p) continue;
            if (tin[to] != -1) {
                low[v] = Math.min(low[v], tin[to]);
            } else {
                dfsBridge(to, v);
                low[v] = Math.min(low[v], low[to]);
                if (low[to] > tin[v]) {
                    bridges.add(new int[]{v, to});
                }
            }
        }
    }

    static void dfsComp(int v, int cid) {
        comp[v] = cid;
        for (int to : g[v]) {
            if (comp[to] != -1) continue;
            long key = ((long) v) * n + to;
            if (bridgeSet.contains(key)) continue;
            dfsComp(to, cid);
        }
    }

    static int getAnyVertex(int compId) {
        for (int i = 1; i <= n; i++)
            if (comp[i] == compId)
                return i;
        return -1;
    }
}
