package com.example;

import java.io.*;
import java.util.StringTokenizer;

public class TBankExam {

    private static final int MOD = 1_000_000_007;

    private static int squaresOnDiagonal(int i) {
        if ((i & 1) != 0) {
            return i / 4 * 2 + 1;
        }
        return (i - 1) / 4 * 2 + 2;
    }

    private static long[][] computeD(int n, int kMax) {
        int rows = 2 * n;
        long[][] d = new long[rows][kMax + 1];
        for (int i = 0; i < rows; i++) {
            d[i][0] = 1;
        }
        if (kMax >= 1 && rows > 1) {
            d[1][1] = 1;
        }
        for (int i = 2; i < rows; i++) {
            for (int j = 1; j <= kMax; j++) {
                long ways = d[i - 2][j];
                int free = squaresOnDiagonal(i) - j + 1;
                if (free > 0) {
                    ways = (ways + d[i - 2][j - 1] * free) % MOD;
                }
                d[i][j] = ways;
            }
        }
        return d;
    }

    private static int maxNonAttackingBishops(int n) {
        return n == 1 ? 1 : 2 * n - 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int cap = maxNonAttackingBishops(n);
        long ans;
        if (k > cap) {
            ans = 0;
        } else {
            long[][] d = computeD(n, k);
            int lastBlack = 2 * n - 1;
            int lastWhite = 2 * n - 2;
            ans = 0;
            for (int i = 0; i <= k; i++) {
                ans = (ans + d[lastBlack][i] * d[lastWhite][k - i]) % MOD;
            }
        }

        writer.write(String.valueOf(ans));
        reader.close();
        writer.flush();
        writer.close();
    }
}
