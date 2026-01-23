package com.example.yandexContest;

// N - количество запросов от пользователя


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution3 {
    static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] wordCount;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            wordCount = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (size[rootX] < size[rootY]) {
                    parent[rootX] = rootY;
                    size[rootY] += size[rootX];
                    wordCount[rootY] = Math.max(wordCount[rootY], wordCount[rootX]);
                } else {
                    parent[rootY] = rootX;
                    size[rootX] += size[rootY];
                    wordCount[rootX] = Math.max(wordCount[rootX], wordCount[rootY]);
                }
            }
        }

        public void setWordCount(int x, int count) {
            wordCount[find(x)] = count;
        }

        public int getWordCount(int x) {
            return wordCount[find(x)];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Set<String>> queries = new ArrayList<>();

        // Чтение запросов
        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(br.readLine());
            String[] words = br.readLine().split(" ");
            Set<String> wordSet = new HashSet<>(Arrays.asList(words));
            queries.add(wordSet);
        }

        UnionFind uf = new UnionFind(N);

        for (int i = 0; i < N; i++) {
            uf.setWordCount(i, queries.get(i).size());
        }

        Map<String, Integer> wordToQuery = new HashMap<>();

        for (int i = 0; i < N; i++) {
            Set<String> currentWords = queries.get(i);

            for (String word : currentWords) {
                if (wordToQuery.containsKey(word)) {
                    int prevQuery = wordToQuery.get(word);
                    uf.union(prevQuery, i);
                } else {
                    wordToQuery.put(word, i);
                }
            }
        }

        Map<Integer, Set<String>> contextWords = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int root = uf.find(i);
            Set<String> currentWords = queries.get(i);

            if (!contextWords.containsKey(root)) {
                contextWords.put(root, new HashSet<>());
            }
            contextWords.get(root).addAll(currentWords);
        }

        for (Map.Entry<Integer, Set<String>> entry : contextWords.entrySet()) {
            uf.setWordCount(entry.getKey(), entry.getValue().size());
        }

        Set<Integer> uniqueContexts = new HashSet<>();
        int maxSize = 0;

        for (int i = 0; i < N; i++) {
            int root = uf.find(i);
            uniqueContexts.add(root);
            maxSize = Math.max(maxSize, uf.getWordCount(root));
        }

        System.out.println(uniqueContexts.size() + " " + maxSize);
    }

}

