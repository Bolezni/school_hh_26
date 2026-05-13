package com.example.belgu;

import java.util.Arrays;

public class Lab12 {

    private static final Integer token = generateToken();

    public static void main(String[] args) {
        System.out.println(token);

        int[] encrypt = encrypt("привет");
        System.out.println(Arrays.toString(encrypt));

        System.out.println(decrypt(encrypt));

        System.out.println("Криптоанализ");
        kriptoAnalyzer(encrypt);

    }

    private static void kriptoAnalyzer(int[] ciphertext) {
        System.out.println("Ищем токен для: " + Arrays.toString(ciphertext));

        String frequentRu = "оеаинтсрвлкмдпуяызьбгчйхжшюцщэфъё";
        String frequentRuUpper = frequentRu.toUpperCase();

        double threshold = frequentRuUpper.length() * 0.5;

        for (int token = 1000; token <= 9999; token++) {
            StringBuilder result = new StringBuilder();
            boolean valid = true;

            for (int num : ciphertext) {
                int code = num + token;
                boolean isCyrillic = (code >= 1040 && code <= 1103) || code == 1105 || code == 1025;
                if (!isCyrillic && code != 32) {
                    valid = false;
                    break;
                }
                result.append((char) code);
            }

            if (valid) {
                String text = result.toString();
                double score = scoreRussian(text.toLowerCase(), frequentRu);
                if (score >= threshold) {
                    System.out.println("TOKEN = " + token + " → " + text + " (score: " + String.format("%.1f", score) + ")");
                }
            }
        }
    }

    private static double scoreRussian(String text, String frequent) {
        double score = 0;
        for (char c : text.toCharArray()) {
            int idx = frequent.indexOf(c);
            if (idx != -1) {
                score += (frequent.length() - idx);
            }
        }
        return score / text.length();
    }

    private static int[] encrypt(String s ) {
        if (s == null || s.isEmpty()) return new int[0];

        int[] numbers = new int[s.length()];
        for (int i = 0; i < s.length(); i++)
            numbers[i] = s.charAt(i) - token;

        return numbers;
    }

    private static String decrypt(int[] numbers) {
        if (numbers == null || numbers.length == 0) return "";

        StringBuilder sb = new StringBuilder();
        for (int number : numbers)
            sb.append((char) (number + token));

        return sb.toString();
    }


    private static int generateToken() {
        return 1000 + (int) (Math.random() * 9000);
    }

}
class Caesar{

}