package com.example.Algorithms_tbank.contest_1;

import java.util.Scanner;

public class Sol4 {
    static double a, b, c, d;
    static double f(double x) {
        return a*x*x*x + b *x*x + c *x + d;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextDouble();
        b = scanner.nextDouble();
        c = scanner.nextDouble();
        d = scanner.nextDouble();

        double left = -1e9;
        double right = 1e9;

        for(int i = 0; i < 200; i++){
            double mid = left + (right - left)/2;
            if (f(mid) * f(right) <= 0) {
                left = mid;
            }else{
                right = mid;
            }
        }

        System.out.println((left + right) / 2);
        scanner.close();
    }
}
