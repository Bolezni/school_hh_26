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
    private static final String ALPHABET_RU = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String ALPHABET_EN = "abcdefghijklmnopqrstuvwxyz";

    //Частота букв
    private static final double[] FREQ_RU = {
            8.01, 1.59, 4.54, 1.70, 2.98, 8.45, 0.24, 0.94, 1.65, 7.35,
            1.21, 3.49, 4.40, 3.21, 6.70, 10.97, 2.81, 4.73, 5.47, 6.26,
            2.62, 0.26, 0.97, 0.48, 1.44, 0.73, 0.04, 1.90, 0.74, 0.32,
            0.64, 0.15, 0.07
    };

    private static final double[] FREQ_EN = {
            8.17, 1.49, 2.78, 4.25, 12.70, 2.23, 2.02, 6.09, 6.97, 0.15,
            0.77, 4.03, 2.41, 6.75, 7.51, 1.93, 0.10, 5.99, 6.33, 9.06,
            2.76, 0.98, 2.36, 0.15, 1.97, 0.07
    };


    public static void main(String[] args) {
        System.out.println("=== Шифр Цезаря ===\n");

        // --- Английский пример ---
        String originalEn = "Hello World! This is a Caesar cipher example.";
        int keyEn = 7;

        String encryptedEn = encrypt(originalEn, keyEn);
        String decryptedEn = decrypt(encryptedEn, keyEn);

        System.out.println("[EN] Исходный текст : " + originalEn);
        System.out.println("[EN] Ключ           : " + keyEn);
        System.out.println("[EN] Зашифрован     : " + encryptedEn);
        System.out.println("[EN] Расшифрован    : " + decryptedEn);

        // --- Русский пример ---
        String originalRu = "Привет мир! Это шифр Цезаря на русском языке.";
        int keyRu = 5;

        String encryptedRu = encrypt(originalRu, keyRu);
        String decryptedRu = decrypt(encryptedRu, keyRu);

        System.out.println("\n[RU] Исходный текст : " + originalRu);
        System.out.println("[RU] Ключ           : " + keyRu);
        System.out.println("[RU] Зашифрован     : " + encryptedRu);
        System.out.println("[RU] Расшифрован    : " + decryptedRu);

        // --- Криптоанализ (без знания ключа) ---
        System.out.println("\n=== Криптоанализ (ключ неизвестен) ===");

        String longText = "The quick brown fox jumps over the lazy dog. "
                + "Cryptography is the practice of securing communications. "
                + "Frequency analysis exploits the fact that each language "
                + "has predictable letter distributions in typical text.";
        int secretKey = 11;
        String cipher = encrypt(longText, secretKey);

        System.out.println("Зашифрованный текст (первые 80 симв.):");
        System.out.println(cipher.substring(0, 80) + "...");

        String bestLanguage = detectLanguage(cipher);
        System.out.println("\nНа каком языке  : " + bestLanguage);
    }


    public static String encrypt(String text, int shift) {
        return process(text, shift);
    }

    public static String decrypt(String text, int shift) {
        return process(text, -shift);
    }

    private static String process(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            char lower = Character.toLowerCase(ch);
            boolean isUpper = Character.isUpperCase(ch);

            if (ALPHABET_RU.indexOf(lower) != -1) {
                int idx = ALPHABET_RU.indexOf(lower);
                int newIdx = Math.floorMod(idx + shift, ALPHABET_RU.length());
                char shifted = ALPHABET_RU.charAt(newIdx);
                result.append(isUpper ? Character.toUpperCase(shifted) : shifted);

            } else if (ALPHABET_EN.indexOf(lower) != -1) {
                int idx = ALPHABET_EN.indexOf(lower);
                int newIdx = Math.floorMod(idx + shift, ALPHABET_EN.length());
                char shifted = ALPHABET_EN.charAt(newIdx);
                result.append(isUpper ? Character.toUpperCase(shifted) : shifted);

            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    private static String detectLanguage(String text) {
        String lower = text.toLowerCase();
        long ruCount = lower.chars().filter(c -> ALPHABET_RU.indexOf(c) != -1).count();
        long enCount = lower.chars().filter(c -> ALPHABET_EN.indexOf(c) != -1).count();
        return ruCount >= enCount ? "ru" : "en";
    }


}

class KeywordCipher {
    private static final String ALPHABET_EN = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHABET_RU = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private static final double[] FREQ_EN = {
            8.17, 1.49, 2.78, 4.25, 12.70, 2.23, 2.02, 6.09, 6.97, 0.15,
            0.77, 4.03, 2.41, 6.75, 7.51, 1.93, 0.10, 5.99, 6.33, 9.06,
            2.76, 0.98, 2.36, 0.15, 1.97, 0.07
    };

    private static final double[] FREQ_RU = {
            8.01, 1.59, 4.54, 1.70, 2.98, 8.45, 0.24, 0.94, 1.65, 7.35,
            1.21, 3.49, 4.40, 3.21, 6.70, 10.97, 2.81, 4.73, 5.47, 6.26,
            2.62, 0.26, 0.97, 0.48, 1.44, 0.73, 0.04, 1.90, 0.74, 0.32,
            0.64, 0.15, 0.07
    };

    public static void main(String[] args) {

        System.out.println("Keyword");

        String keywordEn  = "SECURITY";
        String plainEn    = "The quick brown fox jumps over the lazy dog";

        String subAlphaEn = buildSubstitutionAlphabet(keywordEn, ALPHABET_EN);
        System.out.println("=== Английский ===");
        System.out.println("Лозунг   : " + keywordEn);
        printTable(ALPHABET_EN, subAlphaEn);

        String cipherEn   = encrypt(plainEn, keywordEn);
        String decryptEn  = decrypt(cipherEn, keywordEn);
        System.out.println("Открытый : " + plainEn);
        System.out.println("Шифр     : " + cipherEn);
        System.out.println("Дешифр.  : " + decryptEn);
        System.out.println("Верно?   : " + plainEn.equalsIgnoreCase(decryptEn));

        String keywordRu  = "ЛОЗУНГ";
        String plainRu    = "Съешь же ещё этих мягких французских булок да выпей чаю";

        String subAlphaRu = buildSubstitutionAlphabet(keywordRu, ALPHABET_RU);
        System.out.println("\n=== Русский ===");
        System.out.println("Лозунг   : " + keywordRu);
        printTable(ALPHABET_RU, subAlphaRu);

        String cipherRu   = encrypt(plainRu, keywordRu);
        String decryptRu  = decrypt(cipherRu, keywordRu);
        System.out.println("Открытый : " + plainRu);
        System.out.println("Шифр     : " + cipherRu);
        System.out.println("Дешифр.  : " + decryptRu);
        System.out.println("Верно?   : " + plainRu.equalsIgnoreCase(decryptRu));

        System.out.println("\n=== Криптоанализ (ключ неизвестен) ===");

        String longText =
                "Cryptography is the practice and study of techniques for secure communication " +
                        "in the presence of adversarial behavior. More generally cryptography is about " +
                        "constructing and analyzing protocols that prevent third parties from reading " +
                        "private messages. Modern cryptography exists at the intersection of the disciplines " +
                        "of mathematics computer science information security electrical engineering and physics. " +
                        "Applications of cryptography include electronic commerce chip-based payment cards " +
                        "digital currencies computer passwords and military communications.";

        String secretKeyword = "MYSTERY";
        String encryptedLong = encrypt(longText, secretKeyword);

        System.out.println("Лозунг (скрыт) : " + secretKeyword);
        System.out.println("Шифртекст      : " + encryptedLong.substring(0, 80) + "...");
    }

    //Построить подстановочный алфавит на основе лозунга.
    public static String buildSubstitutionAlphabet(String keyword, String alphabet) {
        keyword = keyword.toLowerCase();
        StringBuilder sub = new StringBuilder();

        for (char ch : keyword.toCharArray()) {
            if (alphabet.indexOf(ch) != -1 && sub.indexOf(String.valueOf(ch)) == -1) {
                sub.append(ch);
            }
        }

        for (char ch : alphabet.toCharArray()) {
            if (sub.indexOf(String.valueOf(ch)) == -1) {
                sub.append(ch);
            }
        }

        return sub.toString();
    }


    public static String encrypt(String text, String keyword) {
        String lang     = detectLanguage(text);
        String alphabet = lang.equals("ru") ? ALPHABET_RU : ALPHABET_EN;
        String subAlpha = buildSubstitutionAlphabet(keyword, alphabet);

        return substitute(text, alphabet, subAlpha);
    }

    public static String decrypt(String text, String keyword) {
        String lang     = detectLanguage(text);
        String alphabet = lang.equals("ru") ? ALPHABET_RU : ALPHABET_EN;
        String subAlpha = buildSubstitutionAlphabet(keyword, alphabet);

        return substitute(text, subAlpha, alphabet);
    }

    private static String substitute(String text, String from, String to) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            char lower    = Character.toLowerCase(ch);
            boolean upper = Character.isUpperCase(ch);
            int idx       = from.indexOf(lower);

            if (idx != -1) {
                char replaced = to.charAt(idx);
                result.append(upper ? Character.toUpperCase(replaced) : replaced);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    private static String detectLanguage(String text) {
        String lower  = text.toLowerCase();
        long ruCount  = lower.chars().filter(c -> ALPHABET_RU.indexOf(c) != -1).count();
        long enCount  = lower.chars().filter(c -> ALPHABET_EN.indexOf(c) != -1).count();
        return ruCount >= enCount ? "ru" : "en";
    }

    private static void printTable(String alphabet, String subAlpha) {
        System.out.println("  Алфавит : " + String.join(" ", alphabet.split("")));
        System.out.println("  Замена  : " + String.join(" ", subAlpha.split("")));
    }

}