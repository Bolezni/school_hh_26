package com.example.yandexContest;

import java.io.*;

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] dimensions = br.readLine().split(" ");
        int n = Integer.parseInt(dimensions[0]);
        int m = Integer.parseInt(dimensions[1]);

        long[][] height = new long[n][m];
        long[][] result = new long[n][m];

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                height[i][j] = Long.parseLong(row[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (height[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = height[i][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(result[i][j] + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
