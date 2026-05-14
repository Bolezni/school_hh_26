package com.example.belgu.diskert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Resheto {
    // Решето Эратосфена
    static List<Integer> eratosthenes(int n) {
        boolean[] sieve = new boolean[n + 1];
        for (int i = 2; i <= n; i++) sieve[i] = true;

        for (int i = 2; (long) i * i <= n; i++) {
            if (sieve[i]) {
                for (int j = i * i; j <= n; j += i) sieve[j] = false;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= n; i++) if (sieve[i]) result.add(i);
        return result;
    }

    // Решето Сундарама
    static List<Integer> sundaram(int n) {
        int k = (n - 2) / 2;
        boolean[] sieve = new boolean[k + 1];
        for (int i = 0; i <= k; i++) sieve[i] = true;

        for (int i = 1; i <= k; i++) {
            for (int j = i; i + j + 2 * i * j <= k; j++) {
                sieve[i + j + 2 * i * j] = false;
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(2);
        for (int i = 1; i <= k; i++) {
            int p = 2 * i + 1;
            if (sieve[i] && p <= n) result.add(p);
        }
        return result;
    }

    // Решето Аткина
//    static List<Integer> atkin(int n) {
//        if (n < 2) return new ArrayList<>();
//        boolean[] sieve = new boolean[n + 1];
//        int r = (int) Math.sqrt(n) + 1;
//
//        for (int x = 1; x < r; x++) {
//            for (int y = 1; y < r; y++) {
//                int z;
//
//                z = 4 * x * x + y * y;
//                if (z <= n && (z % 12 == 1 || z % 12 == 5)) sieve[z] ^= true;
//
//                z = 3 * x * x + y * y;
//                if (z <= n && z % 12 == 7) sieve[z] ^= true;
//
//                z = 3 * x * x - y * y;
//                if (x > y && z <= n && z % 12 == 11) sieve[z] ^= true;
//            }
//        }
//
//        for (int i = 5; i < r; i++) {
//            if (sieve[i]) {
//                int sq = i * i;
//                for (int j = sq; j <= n; j += sq) sieve[j] = false;
//            }
//        }
//
//        List<Integer> result = new ArrayList<>();
//        if (n >= 2) result.add(2);
//        if (n >= 3) result.add(3);
//        for (int i = 5; i <= n; i++) if (sieve[i]) result.add(i);
//        return result;
//    }

    static void printMenu() {
        System.out.println();
        System.out.println("=== Решёта простых чисел ===");
        System.out.println("1 — Решето Эратосфена");
        System.out.println("2 — Решето Сундарама");
        System.out.println("0 — Выход");
        System.out.print("Выбор: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            if (choice.equals("0")) {
                System.out.println("Выход.");
                return;
            }

            if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
                System.out.println("Неизвестная команда. Введите 0–3.");
                continue;
            }

            System.out.print("Введите n: ");
            int n;
            try {
                n = Integer.parseInt(scanner.nextLine().trim());
                if (n < 2) {
                    System.out.println("Ошибка: n должно быть >= 2.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
                continue;
            }

            List<Integer> primes = new ArrayList<>();
            String algName = "";

            switch (choice) {
                case "1": primes = eratosthenes(n); algName = "Эратосфена"; break;
                case "2": primes = sundaram(n);     algName = "Сундарама";  break;
                //default:  primes = atkin(n);         algName = "Аткина";    break;
            }

            System.out.println("\nРешето " + algName + ", n = " + n);
            System.out.println("Найдено простых чисел: " + primes.size());
            System.out.println(primes);
        }
    }
}
