package com.example.Algorithms_tbank.contest_1;

import java.util.Scanner;

public class Task_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double C = sc.nextDouble();

        double left = 0;
        double right = Math.sqrt(C) + 10;

        for (int iter = 0; iter < 100; iter++) {
            double mid = (left + right) / 2;
            double value = mid * mid + Math.sqrt(mid + 1);

            if (value < C) {
                left = mid;
            } else {
                right = mid;
            }
        }

        System.out.println((left + right) / 2);
    }
}
