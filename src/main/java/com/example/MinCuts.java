package com.example;

import java.util.ArrayList;
import java.util.List;

public class MinCuts {
    public static void main(String[] args) {
        String[] str = {"A", "B", "C", "D", "E"};
        int k = 3;
        List<String> combinations = new ArrayList<>();

        alpinist(str, str.length, k, 0, "", combinations);

        // Печать всех комбинаций
        for (String combination : combinations) {
            System.out.println(combination);
        }
    }

    public static boolean sumInBackpack(int[] arr, int target, int n) {
        if (target == 0) return true;

        if (n == 0) return false;

        if (arr[n - 1] > target)
            return sumInBackpack(arr, target, n - 1);

        return sumInBackpack(arr, target - arr[n - 1], n - 1) || sumInBackpack(arr, target, n - 1);
    }

    public static void alpinist(String[] str, int n, int k, int index,
                                String currentCombination, List<String> combinations) {
        if (k == 0) {
            combinations.add(currentCombination);
            return;
        }

        if (index >= n)
            return;
        // добавить в строку комбинации
        alpinist(str, n, k - 1, index + 1, currentCombination + str[index] + " ", combinations);
        // пропустить элемент
        alpinist(str, n, k, index + 1, currentCombination, combinations);
    }
}
