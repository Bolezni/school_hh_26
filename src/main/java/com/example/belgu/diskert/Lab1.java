package com.example.belgu.diskert;

import java.util.Scanner;

public class Lab1 {
    static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите элементы массива (через пробел, от 4 до 10): ");
        String line = scanner.nextLine().trim();
        String[] elements = line.split("\\s+");

        int n = elements.length;

        if (n < 4 || n > 10) {
            System.out.println("Ошибка: количество элементов должно быть от 4 до 10.");
            return;
        }

        // Проверка на уникальность
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (elements[i].equals(elements[j])) {
                    System.out.println("Ошибка: элементы должны быть различными.");
                    return;
                }
            }
        }

        System.out.print("Введите длину размещения k (от 1 до " + n + "): ");
        int k;
        try {
            k = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: k должно быть целым числом.");
            return;
        }

        if (k < 1 || k > n) {
            System.out.println("Ошибка: k должно быть от 1 до " + n + ".");
            return;
        }

        count = 0;
        System.out.println("\nРазмещения длины " + k + ":");
        generate(new String[0], elements, k);
        System.out.println("\nКоличество размещений: " + count);
    }

    static void generate(String[] prefix, String[] available, int k) {
        if (prefix.length == k) {
            System.out.println(String.join(" ", prefix));
            count++;
            return;
        }
        for (int i = 0; i < available.length; i++) {
            String[] newPrefix = new String[prefix.length + 1];
            System.arraycopy(prefix, 0, newPrefix, 0, prefix.length);
            newPrefix[prefix.length] = available[i];

            String[] newAvailable = new String[available.length - 1];
            System.arraycopy(available, 0, newAvailable, 0, i);
            System.arraycopy(available, i + 1, newAvailable, i, available.length - i - 1);

            generate(newPrefix, newAvailable, k);
        }
    }
}
