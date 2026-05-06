package com.example.yandexContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class OfflineFormatTaskB {

    static int N;
    static long[] w;
    static long[] pref;
    static long[][] dp;
    static int[][] root;
    @SuppressWarnings("unchecked")
    static List<int[]>[][] memo = new List[0][0];
    static int[][][] bestRoots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.isEmpty()) return;
        N = Integer.parseInt(line.trim());

        w = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            while (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            w[i] = Long.parseLong(st.nextToken());
        }

        pref = new long[N + 1];
        for (int i = 0; i < N; i++) {
            pref[i + 1] = pref[i] + w[i];
        }

        dp = new long[N][N];
        root = new int[N][N];

        // base cases
        for (int i = 0; i < N; i++) {
            dp[i][i] = w[i];
            root[i][i] = i;
        }

        // Knuth optimization for optimal binary search tree
        for (int len = 2; len <= N; len++) {
            for (int l = 0; l + len - 1 < N; l++) {
                int r = l + len - 1;
                long best = Long.MAX_VALUE;
                int bestK = -1;
                int start = root[l][r - 1];
                int end = root[l + 1][r];
                if (start < l) start = l;
                if (end > r) end = r;
                long sum = pref[r + 1] - pref[l];
                for (int k = start; k <= end; k++) {
                    long left = (k > l) ? dp[l][k - 1] : 0;
                    long right = (k < r) ? dp[k + 1][r] : 0;
                    long val = left + right + sum;
                    if (val < best) {
                        best = val;
                        bestK = k;
                    }
                }
                dp[l][r] = best;
                root[l][r] = bestK;
            }
        }

        // prepare memo structures
        //noinspection unchecked
        memo = new List[N][N];
        bestRoots = new int[N][N][];

        List<int[]> all = build(0, N - 1);

        StringBuilder out = new StringBuilder();
        out.append(all.size()).append('\n');
        for (int[] arr : all) {
            for (int i = 0; i < N; i++) {
                out.append(arr[i]);
                if (i + 1 < N) out.append(' ');
            }
            out.append('\n');
        }
        System.out.print(out);
    }

    static int[] getRoots(int l, int r) {
        int[] cached = bestRoots[l][r];
        if (cached != null) return cached;

        long best = dp[l][r];
        long segmentSum = pref[r + 1] - pref[l];
        List<Integer> tmp = new ArrayList<>();
        for (int k = l; k <= r; k++) {
            long left = (k > l) ? dp[l][k - 1] : 0;
            long right = (k < r) ? dp[k + 1][r] : 0;
            long val = left + right + segmentSum;
            if (val == best) {
                tmp.add(k);
            }
        }
        int[] rootsArr = new int[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) rootsArr[i] = tmp.get(i);
        bestRoots[l][r] = rootsArr;
        return rootsArr;
    }

    static List<int[]> build(int l, int r) {
        if (l > r) {
            List<int[]> base = new ArrayList<>();
            base.add(new int[0]);
            return base;
        }
        List<int[]> cached = memo[l][r];
        if (cached != null) return cached;

        List<int[]> res = new ArrayList<>();
        int[] rootsHere = getRoots(l, r);
        int len = r - l + 1;

        for (int rootIdx : rootsHere) {
            List<int[]> leftList = build(l, rootIdx - 1);
            List<int[]> rightList = build(rootIdx + 1, r);

            int leftLen = rootIdx - l;
            int rightLen = r - rootIdx;

            for (int[] left : leftList) {
                for (int[] right : rightList) {
                    int[] arr = new int[len];
                    arr[0] = rootIdx + 1; // convert to 1-based index
                    if (leftLen > 0) {
                        System.arraycopy(left, 0, arr, 1, leftLen);
                    }
                    if (rightLen > 0) {
                        System.arraycopy(right, 0, arr, 1 + leftLen, rightLen);
                    }
                    res.add(arr);
                }
            }
        }

        memo[l][r] = res;
        return res;
    }
}
