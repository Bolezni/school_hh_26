package com.example.java;

public class JavaBigDecimal {
    public static void main(String[] args) throws Exception {
        System.out.println(power(2, 3));
    }

    public static long power(int x, int y) throws Exception {
        if(x < 0 || y < 0)
            throw new Exception("n and p should not be zero.");
        if(x == 0 || y == 0)
            throw new Exception("n or p should not be negative.");
        long result = 1;
        for (int i = 0; i < y; i++) {
            result *= x;
        }
        return result;
    }
}
