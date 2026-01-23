package com.example.yandexContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int[] h = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);

        for (int parity = 0; parity <= 1; parity++) {
            Stack<Integer> stack = new Stack<>();

            for (int i = n - 1; i >= 0; i--) {
                if (i % 2 != parity) continue;

                while (!stack.isEmpty() && h[stack.peek()] <= h[i]) {
                    stack.pop();
                }

                if (!stack.isEmpty()) {
                    result[i] = stack.peek() - i;
                }

                stack.push(i);
            }
        }

        for (int i = 0; i < n; i++) {
            pw.print(result[i] + " ");
        }
        pw.println();
        pw.flush();
    }
}
