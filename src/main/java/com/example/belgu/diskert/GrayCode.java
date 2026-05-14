package com.example.belgu.diskert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GrayCode {
    static List<String> grayCode(int n) {
        if (n == 1) {
            List<String> base = new ArrayList<>();
            base.add("0");
            base.add("1");
            return base;
        }

        List<String> prev = grayCode(n - 1);
        List<String> result = new ArrayList<>();

        for (String s : prev) {
            result.add("0" + s);
        }

        for (int i = prev.size() - 1; i >= 0; i--) {
            result.add("1" + prev.get(i));
        }

        return result;
    }

    static String grayToBinary(String gray) {
        StringBuilder binary = new StringBuilder();
        binary.append(gray.charAt(0));

        for (int i = 1; i < gray.length(); i++) {
            int prevBit = binary.charAt(i - 1) - '0';
            int grayBit = gray.charAt(i) - '0';
            binary.append(prevBit ^ grayBit);
        }

        return binary.toString();
    }

    static String binaryToGray(String binary) {
        StringBuilder gray = new StringBuilder();
        gray.append(binary.charAt(0));

        for (int i = 1; i < binary.length(); i++) {
            int prevBit = binary.charAt(i - 1) - '0';
            int curBit  = binary.charAt(i) - '0';
            gray.append(prevBit ^ curBit);
        }

        return gray.toString();
    }

    static boolean isBinary(String s) {
        if (s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (c != '0' && c != '1') return false;
        }
        return true;
    }

    static void printMenu() {
        System.out.println();
        System.out.println("=== Перевод кода Грея ===");
        System.out.println("1 — Сгенерировать все коды Грея для n бит");
        System.out.println("2 — Код Грея → двоичный");
        System.out.println("3 — Двоичный → код Грея");
        System.out.println("0 — Выход");
        System.out.print("Выбор: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": {
                    System.out.print("Введите n: ");
                    String input = scanner.nextLine().trim();
                    try {
                        int n = Integer.parseInt(input);
                        if (n < 1 || n > 16) {
                            System.out.println("Ошибка: n должно быть от 1 до 16.");
                            break;
                        }
                        List<String> codes = grayCode(n);
                        System.out.println("Коды Грея для n = " + n + ":");
                        for (int i = 0; i < codes.size(); i++) {
                            System.out.printf("  %3d  →  %s%n", i, codes.get(i));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введите целое число.");
                    }
                    break;
                }

                case "2": {
                    System.out.print("Введите код Грея: ");
                    String gray = scanner.nextLine().trim();
                    if (!isBinary(gray)) {
                        System.out.println("Ошибка: введите строку из 0 и 1.");
                        break;
                    }
                    System.out.println("Двоичный: " + grayToBinary(gray));
                    break;
                }

                case "3": {
                    System.out.print("Введите двоичный код: ");
                    String binary = scanner.nextLine().trim();
                    if (!isBinary(binary)) {
                        System.out.println("Ошибка: введите строку из 0 и 1.");
                        break;
                    }
                    System.out.println("Код Грея: " + binaryToGray(binary));
                    break;
                }

                case "0":
                    System.out.println("Выход.");
                    return;

                default:
                    System.out.println("Неизвестная команда. Введите 0–3.");
            }
        }
    }
}
