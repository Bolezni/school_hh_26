package com.example.yandexContest;

import java.io.*;
import java.util.StringTokenizer;

public class OfflineFormat {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = reader.readLine();
        if (line == null) {
            writer.write("0");
            writer.flush();
            return;
        }
        int n = Integer.parseInt(line.trim());

        int[] coordination = new int[n];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            coordination[i] = Integer.parseInt(st.nextToken());
        }

        int[] type = new int[n];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            type[i] = Integer.parseInt(st.nextToken());
        }

        int[] len = new int[n];
        int[] reward = new int[n];
        for (int i = 0; i < n; i++) {
            if (type[i] == 1) {
                len[i] = 1;
                reward[i] = 1;
            } else if (type[i] == 2) {
                len[i] = 2;
                reward[i] = 3;
            } else if (type[i] == 3) {
                len[i] = 4;
                reward[i] = 5;
            } else {
                writer.write("0");
                writer.flush();
                return;
            }
        }

        for (int i = 1; i < n; i++) {
            long prevEnd = (long) coordination[i - 1] + len[i - 1];
            if (coordination[i] <= prevEnd) {
                writer.write("0");
                writer.flush();
                return;
            }
        }

        int m = Integer.parseInt(reader.readLine().trim());

        long[] X = new long[m];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            X[i] = Long.parseLong(st.nextToken());
        }

        int[] Y = new int[m];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            Y[i] = Integer.parseInt(st.nextToken());
        }

        long points = 0;

        int j = 0;
        long maxJumpEnd = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            long obsStart = coordination[i];
            long obsEnd = obsStart + len[i];

            while (j < m && X[j] <= obsStart) {
                long end = X[j] + Y[j];
                if (end > maxJumpEnd) {
                    maxJumpEnd = end;
                }
                j++;
            }

            boolean ok = maxJumpEnd >= obsEnd;
            if (ok) {
                points += reward[i];
            } else {
                points -= 1;
            }
        }

        if (points < 0) points = 0;
        writer.write((int) points);
        writer.flush();
        writer.close();
        reader.close();
    }
}

