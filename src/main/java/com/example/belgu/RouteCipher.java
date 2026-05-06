package com.example.belgu;

import java.util.*;

/**
 * Исследование шифра маршрутной перестановки
 * Шифр записывает текст в матрицу по строкам, а считывает по столбцам
 * (или наоборот, в зависимости от режима)
 */
public class RouteCipher {

    // Режимы обхода
    public enum Route {
        ROWS_TO_COLS,      // запись по строкам, чтение по столбцам
        COLS_TO_ROWS       // запись по столбцам, чтение по строкам
    }

    private int rows;
    private int cols;
    private Route route;

    public RouteCipher(int rows, int cols, Route route) {
        this.rows = rows;
        this.cols = cols;
        this.route = route;
    }

    // ========== 1. Шифрование ==========
    public String encrypt(String plaintext) {
        // Убираем пробелы и приводим к верхнему регистру для единообразия
        String text = plaintext.replaceAll("\\s+", "").toUpperCase();
        int len = text.length();
        int matrixSize = rows * cols;

        if (len > matrixSize) {
            throw new IllegalArgumentException("Текст слишком длинный для матрицы " + rows + "x" + cols);
        }

        // Заполняем матрицу символами + дополняем 'X' при необходимости
        char[][] matrix = new char[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (index < len) {
                    matrix[i][j] = text.charAt(index++);
                } else {
                    matrix[i][j] = 'X'; // padding
                }
            }
        }

        // Читаем в соответствии с маршрутом
        StringBuilder ciphertext = new StringBuilder();

        if (route == Route.ROWS_TO_COLS) {
            // Чтение по столбцам
            for (int j = 0; j < cols; j++) {
                for (int i = 0; i < rows; i++) {
                    ciphertext.append(matrix[i][j]);
                }
            }
        } else {
            // Чтение по строкам
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    ciphertext.append(matrix[i][j]);
                }
            }
        }

        return ciphertext.toString();
    }

    // ========== 2. Дешифрование ==========
    public String decrypt(String ciphertext) {
        String text = ciphertext.toUpperCase();
        int len = text.length();
        int matrixSize = rows * cols;

        if (len != matrixSize) {
            throw new IllegalArgumentException("Длина шифротекста должна быть " + matrixSize);
        }

        char[][] matrix = new char[rows][cols];
        int index = 0;

        // Заполняем матрицу в порядке считывания при шифровании
        if (route == Route.ROWS_TO_COLS) {
            // Заполняем по столбцам (так как при шифровании читали по столбцам)
            for (int j = 0; j < cols; j++) {
                for (int i = 0; i < rows; i++) {
                    matrix[i][j] = text.charAt(index++);
                }
            }
        } else {
            // Заполняем по строкам
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = text.charAt(index++);
                }
            }
        }

        // Читаем по строкам для восстановления исходного порядка
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                plaintext.append(matrix[i][j]);
            }
        }

        // Убираем padding (символы 'X' в конце, но аккуратно, т.к. 'X' может быть частью текста)
        String result = plaintext.toString().replaceAll("X+$", "");
        return result;
    }

    // ========== 3. Криптоанализ (перебор размеров матрицы) ==========
    public static class CryptanalysisResult {
        int rows, cols;
        String candidateText;
        double score; // оценка по частотному анализу

        public CryptanalysisResult(int rows, int cols, String candidateText, double score) {
            this.rows = rows;
            this.cols = cols;
            this.candidateText = candidateText;
            this.score = score;
        }

        @Override
        public String toString() {
            return String.format("Matrix %dx%d (score=%.4f): %s", rows, cols, score, candidateText);
        }
    }

    // Частоты букв в русском языке (для русскоязычных текстов)
    private static final Map<Character, Double> RUSSIAN_FREQ = new HashMap<>();
    static {
        double[] freqs = {
                0.0801, // А
                0.0159, // Б
                0.0454, // В
                0.0170, // Г
                0.0298, // Д
                0.0845, // Е
                0.0004, // Ё (редко)
                0.0072, // Ж
                0.0165, // З
                0.0750, // И
                0.0140, // Й
                0.0286, // К
                0.0435, // Л
                0.0320, // М
                0.0670, // Н
                0.1097, // О
                0.0280, // П
                0.0473, // Р
                0.0547, // С
                0.0626, // Т
                0.0262, // У
                0.0026, // Ф
                0.0097, // Х
                0.0048, // Ц
                0.0073, // Ч
                0.0067, // Ш
                0.0036, // Щ
                0.0062, // Ъ
                0.0180, // Ы
                0.0032, // Ь
                0.0201, // Э
                0.0072, // Ю
                0.0061  // Я
        };
        char c = 'А';
        for (double f : freqs) {
            RUSSIAN_FREQ.put(c++, f);
        }
        RUSSIAN_FREQ.put('Ё', 0.0004);
    }

    // Частоты букв в английском языке
    private static final Map<Character, Double> ENGLISH_FREQ = new HashMap<>();
    static {
        double[] freqs = {
                0.08167, // A
                0.01492, // B
                0.02782, // C
                0.04253, // D
                0.12702, // E
                0.02228, // F
                0.02015, // G
                0.06094, // H
                0.06966, // I
                0.00153, // J
                0.00772, // K
                0.04025, // L
                0.02406, // M
                0.06749, // N
                0.07507, // O
                0.01929, // P
                0.00095, // Q
                0.05987, // R
                0.06327, // S
                0.09056, // T
                0.02758, // U
                0.00978, // V
                0.02360, // W
                0.00150, // X
                0.01974, // Y
                0.00074  // Z
        };
        char c = 'A';
        for (double f : freqs) {
            ENGLISH_FREQ.put(c++, f);
        }
    }

    // Оценка текста на основе частотного анализа
    private static double scoreText(String text, Map<Character, Double> languageFreq) {
        text = text.toUpperCase().replaceAll("[^A-ZА-ЯЁ]", "");
        if (text.isEmpty()) return 0;

        Map<Character, Integer> observed = new HashMap<>();
        for (char ch : text.toCharArray()) {
            observed.put(ch, observed.getOrDefault(ch, 0) + 1);
        }

        double score = 0;
        for (Map.Entry<Character, Double> entry : languageFreq.entrySet()) {
            char ch = entry.getKey();
            double expectedFreq = entry.getValue();
            int count = observed.getOrDefault(ch, 0);
            double observedFreq = (double) count / text.length();
            score += Math.abs(expectedFreq - observedFreq);
        }
        // Чем меньше разница, тем лучше (возвращаем отрицательную разницу для сортировки по убыванию)
        return -score;
    }

    /**
     * Криптоанализ шифра маршрутной перестановки
     * Перебирает возможные размеры матрицы и оценивает расшифрованные тексты
     */
    public static List<CryptanalysisResult> cryptanalysis(String ciphertext, String language, int maxDimension) {
        List<CryptanalysisResult> results = new ArrayList<>();
        Map<Character, Double> freqMap = language.equalsIgnoreCase("RU") ? RUSSIAN_FREQ : ENGLISH_FREQ;
        int len = ciphertext.length();

        // Перебираем возможные комбинации rows и cols (rows * cols = len)
        for (int rows = 2; rows <= maxDimension && rows <= len; rows++) {
            if (len % rows != 0) continue;
            int cols = len / rows;
            if (cols > maxDimension) continue;

            // Пробуем оба направления обхода
            for (Route route : Route.values()) {
                try {
                    RouteCipher cipher = new RouteCipher(rows, cols, route);
                    String decrypted = cipher.decrypt(ciphertext);
                    double score = scoreText(decrypted, freqMap);
                    results.add(new CryptanalysisResult(rows, cols, decrypted, score));
                } catch (Exception e) {
                    // Пропускаем невалидные комбинации
                }
            }
        }

        // Сортируем по качеству расшифровки (чем выше score, тем лучше)
        results.sort((a, b) -> Double.compare(b.score, a.score));
        return results;
    }

    // ========== Демонстрация работы ==========
    public static void main(String[] args) {
        System.out.println("=== Исследование шифра маршрутной перестановки ===\n");

        // Пример 1: Английский текст
        demoEnglish();
        System.out.println("\n" + "=".repeat(60) + "\n");

        // Пример 2: Русский текст
        demoRussian();
    }

    private static void demoEnglish() {
        System.out.println("--- Пример на английском ---");
        String plaintext = "CRYPTOGRAPHY AND NETWORK SECURITY";
        System.out.println("Исходный текст: " + plaintext);

        // Шифрование
        RouteCipher cipher = new RouteCipher(5, 6, Route.ROWS_TO_COLS);
        String encrypted = cipher.encrypt(plaintext);
        System.out.println("Зашифровано (5x6, ROWS->COLS): " + encrypted);

        // Дешифрование
        String decrypted = cipher.decrypt(encrypted);
        System.out.println("Расшифровано: " + decrypted);

        // Криптоанализ (без знания ключа)
        System.out.println("\nКриптоанализ (перебор размеров матрицы до 20):");
        List<CryptanalysisResult> results = cryptanalysis(encrypted, "EN", 20);
        System.out.println("Топ-5 вариантов:");
        for (int i = 0; i < Math.min(5, results.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + results.get(i));
        }
    }

    private static void demoRussian() {
        System.out.println("--- Пример на русском ---");
        String plaintext = "КРИПТОГРАФИЯ ЭТО НАУКА О ШИФРАХ";
        System.out.println("Исходный текст: " + plaintext);

        // Шифрование
        RouteCipher cipher = new RouteCipher(4, 7, Route.COLS_TO_ROWS);
        String encrypted = cipher.encrypt(plaintext);
        System.out.println("Зашифровано (4x7, COLS->ROWS): " + encrypted);

        // Дешифрование
        String decrypted = cipher.decrypt(encrypted);
        System.out.println("Расшифровано: " + decrypted);

        // Криптоанализ
        System.out.println("\nКриптоанализ (перебор размеров матрицы до 20):");
        List<CryptanalysisResult> results = cryptanalysis(encrypted, "RU", 20);
        System.out.println("Топ-5 вариантов:");
        for (int i = 0; i < Math.min(5, results.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + results.get(i));
        }
    }
}
