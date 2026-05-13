package com.example.probnik;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] arr = new int[n];


    }




























    private static int numberOfOccurrences(String j, String s) {
        Set<Character> jSet = new HashSet<>();
        for (char ch : j.toCharArray()) {
            jSet.add(ch);
        }

        int count = 0;
        for (char ch : s.toCharArray()) {
            if (jSet.contains(ch)) {
                count++;
            }
        }
        return count;
    }
}
