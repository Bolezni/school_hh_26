package com.example.belgu;

import java.util.*;

/**
 * ============================================================
 * Lab: Classical Ciphers
 *
 * Task 1: Simple Monoalphabetic Substitution Cipher
 * Task 2: Caesar Cipher
 * Task 3: Keyword (Slogan) Cipher
 *
 * Compile: javac CipherLab.java
 * Run:     java CipherLab
 * ============================================================
 */
public class CipherLab {

    // =========================================================
    // TASK 1: SIMPLE MONOALPHABETIC SUBSTITUTION
    // =========================================================

    /**
     * Generates a random substitution key (shuffled A-Z alphabet).
     * Uses Fisher-Yates shuffle.
     *
     * @return char[26] where key[i] is the cipher letter for plain letter (A+i)
     */
    public static char[] generateRandomKey() {
        char[] key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Random rnd = new Random();
        for (int i = key.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            char tmp = key[i]; key[i] = key[j]; key[j] = tmp;
        }
        return key;
    }

    /**
     * Encrypts text using simple monoalphabetic substitution.
     * Each letter is replaced by the corresponding key letter.
     * Case is preserved; non-letter characters are unchanged.
     *
     * @param text  plaintext
     * @param key   substitution alphabet (char[26])
     * @return ciphertext
     */
    public static String monoEncrypt(String text, char[] key) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                int idx = Character.toUpperCase(c) - 'A';
                char enc = key[idx];
                sb.append(isUpper ? enc : Character.toLowerCase(enc));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Decrypts text using simple monoalphabetic substitution.
     * Builds the inverse key and applies monoEncrypt.
     *
     * @param text  ciphertext
     * @param key   same key used during encryption
     * @return plaintext
     */
    public static String monoDecrypt(String text, char[] key) {
        char[] reverseKey = new char[26];
        for (int i = 0; i < 26; i++) {
            reverseKey[key[i] - 'A'] = (char) ('A' + i);
        }
        return monoEncrypt(text, reverseKey);
    }

    /**
     * Cryptanalysis of monoalphabetic substitution via frequency analysis.
     *
     * Algorithm:
     *   1. Count letter frequencies in ciphertext.
     *   2. Sort cipher letters by descending frequency.
     *   3. Map them to English letters sorted by expected frequency
     *      (E=12.7%, T=9.1%, A=8.2%, O=7.5%, I=7.0%, ...).
     *   4. Apply the guessed substitution to recover plaintext.
     *
     * Accuracy improves with longer ciphertext (200+ letters recommended).
     *
     * @param ciphertext ciphertext to analyse
     * @return guessed plaintext
     */
    public static String monoAnalyze(String ciphertext) {
        final String engByFreq = "ETAOINSHRDLCUMWFGYPBVKJXQZ";

        int[] freq = new int[26];
        for (char c : ciphertext.toUpperCase().toCharArray()) {
            if (c >= 'A' && c <= 'Z') freq[c - 'A']++;
        }

        Integer[] indices = new Integer[26];
        for (int i = 0; i < 26; i++) indices[i] = i;
        Arrays.sort(indices, (a, b) -> freq[b] - freq[a]);

        char[] guessKey = new char[26];
        for (int i = 0; i < 26; i++) {
            guessKey[indices[i]] = engByFreq.charAt(i);
        }

        StringBuilder sb = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                int idx = Character.toUpperCase(c) - 'A';
                char dec = guessKey[idx];
                sb.append(isUpper ? dec : Character.toLowerCase(dec));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    // =========================================================
    // TASK 2: CAESAR CIPHER
    // =========================================================

    /**
     * Encrypts text using the Caesar cipher.
     * Each letter is shifted forward by 'shift' positions in the alphabet.
     * Case is preserved; non-letter characters are unchanged.
     *
     * @param text  plaintext
     * @param shift key (1-25); negative values are handled correctly
     * @return ciphertext
     */
    public static String caesarEncrypt(String text, int shift) {
        shift = ((shift % 26) + 26) % 26;
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                sb.append((char) ((c - base + shift) % 26 + base));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Decrypts text using the Caesar cipher.
     * Equivalent to encrypting with the negative shift.
     *
     * @param text  ciphertext
     * @param shift same key used during encryption
     * @return plaintext
     */
    public static String caesarDecrypt(String text, int shift) {
        return caesarEncrypt(text, -shift);
    }

    /**
     * Cryptanalysis of the Caesar cipher using the Index of Coincidence (IC).
     *
     * Algorithm:
     *   1. Try all 25 possible shifts.
     *   2. For each candidate, compute IC = sum(fi*(fi-1)) / (n*(n-1)).
     *   3. The shift whose IC is closest to 0.065 (English IC) is the key.
     *
     * @param ciphertext ciphertext to break
     * @return Object[] { bestShift (int), decryptedText (String) }
     */
    public static Object[] caesarAnalyze(String ciphertext) {
        double bestIC = -1;
        int bestShift = 0;

        for (int s = 0; s < 26; s++) {
            double ic = calcIC(caesarDecrypt(ciphertext, s));
            if (ic > bestIC) { bestIC = ic; bestShift = s; }
        }
        return new Object[]{ bestShift, caesarDecrypt(ciphertext, bestShift) };
    }

    /**
     * Computes the Index of Coincidence for a text.
     * English: ~0.065 | Random: ~0.038
     */
    private static double calcIC(String text) {
        int[] freq = new int[26];
        int n = 0;
        for (char c : text.toUpperCase().toCharArray()) {
            if (c >= 'A' && c <= 'Z') { freq[c - 'A']++; n++; }
        }
        if (n < 2) return 0;
        double sum = 0;
        for (int f : freq) sum += (double) f * (f - 1);
        return sum / ((double) n * (n - 1));
    }


    // =========================================================
    // TASK 3: KEYWORD (SLOGAN) CIPHER
    // =========================================================

    /**
     * Builds a substitution alphabet from a keyword.
     *
     * Algorithm:
     *   1. Write the keyword letters (uppercase, duplicates removed).
     *   2. Append remaining A-Z letters not already present.
     *
     * Example: "SECRET" -> "SECRTABDFGHIJKLMNOPQUVWXYZ"
     *
     * @param keyword the keyword (case-insensitive)
     * @return 26-character substitution alphabet
     */
    public static String buildSloganAlphabet(String keyword) {
        LinkedHashSet<Character> seen = new LinkedHashSet<>();
        for (char c : (keyword.toUpperCase() + "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray()) {
            if (c >= 'A' && c <= 'Z') seen.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : seen) sb.append(c);
        return sb.toString();
    }

    /**
     * Encrypts text using the keyword cipher.
     * A -> sloganAlpha[0], B -> sloganAlpha[1], etc.
     *
     * @param text    plaintext
     * @param keyword encryption keyword
     * @return ciphertext
     */
    public static String sloganEncrypt(String text, String keyword) {
        return monoEncrypt(text, buildSloganAlphabet(keyword).toCharArray());
    }

    /**
     * Decrypts text using the keyword cipher.
     *
     * @param text    ciphertext
     * @param keyword same keyword used during encryption
     * @return plaintext
     */
    public static String sloganDecrypt(String text, String keyword) {
        return monoDecrypt(text, buildSloganAlphabet(keyword).toCharArray());
    }

    /**
     * Cryptanalysis of the keyword cipher.
     * The keyword cipher is a special case of simple substitution,
     * so the same frequency analysis attack applies.
     *
     * @param ciphertext ciphertext to analyse
     * @return guessed plaintext
     */
    public static String sloganAnalyze(String ciphertext) {
        return monoAnalyze(ciphertext);
    }


    // =========================================================
    // ENTRY POINT - DEMONSTRATION
    // =========================================================

    private static void sep(String title) {
        System.out.println("\n" + "=".repeat(62));
        System.out.println("  " + title);
        System.out.println("=".repeat(62));
    }

    private static void row(String label, String value) {
        System.out.printf("  %-26s %s%n", label + ":", value);
    }

    public static void main(String[] args) {

        // --- Task 1: Simple Monoalphabetic Substitution ---------------
        sep("TASK 1 -- Simple Monoalphabetic Substitution Cipher");

        String pt1  = "Hello World! This is a secret message.";
        char[] key1 = generateRandomKey();

        System.out.println("  Key (plain -> cipher alphabet):");
        System.out.println("    ABCDEFGHIJKLMNOPQRSTUVWXYZ  (plain)");
        System.out.println("    " + new String(key1) + "  (cipher)");

        String enc1 = monoEncrypt(pt1, key1);
        String dec1 = monoDecrypt(enc1, key1);
        String cry1 = monoAnalyze(enc1);

        System.out.println();
        row("Plaintext",                pt1);
        row("Ciphertext",               enc1);
        row("Decrypted (key)",          dec1);
        row("Cryptanalysis (freq.)",    cry1);
        row("Decryption correct",       String.valueOf(pt1.equals(dec1)));

        // --- Task 2: Caesar Cipher ------------------------------------
        sep("TASK 2 -- Caesar Cipher");

        String pt2    = "The quick brown fox jumps over the lazy dog.";
        int    shift2 = 13;

        String   enc2  = caesarEncrypt(pt2, shift2);
        String   dec2  = caesarDecrypt(enc2, shift2);
        Object[] res2  = caesarAnalyze(enc2);
        int      found = (int)    res2[0];
        String   cry2  = (String) res2[1];

        row("Plaintext",                pt2);
        row("Key (shift)",              String.valueOf(shift2));
        row("Ciphertext",               enc2);
        row("Decrypted (key)",          dec2);
        row("Found shift (IC method)",  String.valueOf(found));
        row("Cryptanalysis result",     cry2);
        row("Shift found correctly",    String.valueOf(found == shift2));

        System.out.println("\n  All 26 brute-force candidates:");
        for (int s = 0; s < 26; s++) {
            String cand = caesarDecrypt(enc2, s);
            double ic   = calcIC(cand);
            String mark = (s == found) ? " <-- BEST IC=" + String.format("%.4f", ic) : "";
            System.out.printf("  [%02d] IC=%.4f  %s%s%n",
                    s, ic, cand.substring(0, Math.min(40, cand.length())), mark);
        }

        // --- Task 3: Keyword (Slogan) Cipher --------------------------
        sep("TASK 3 -- Keyword (Slogan) Cipher");

        String pt3  = "Cryptography is the practice of secure communication.";
        String kw3  = "SECRET";
        String alp3 = buildSloganAlphabet(kw3);

        System.out.println("  Keyword : " + kw3);
        System.out.println("  Alphabet construction:");
        System.out.println("    plain : ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println("    cipher: " + alp3);

        String enc3 = sloganEncrypt(pt3, kw3);
        String dec3 = sloganDecrypt(enc3, kw3);
        String cry3 = sloganAnalyze(enc3);

        System.out.println();
        row("Plaintext",             pt3);
        row("Ciphertext",            enc3);
        row("Decrypted (key)",       dec3);
        row("Cryptanalysis (freq.)", cry3);
        row("Decryption correct",    String.valueOf(pt3.equals(dec3)));

        // --- Summary Table --------------------------------------------
        sep("SUMMARY");
        System.out.printf("  %-30s %-18s %-18s%n", "Cipher", "Key space", "Attack");
        System.out.println("  " + "-".repeat(68));
        System.out.printf("  %-30s %-18s %-18s%n",
                "Simple substitution", "26! ~ 4x10^26", "Frequency analysis");
        System.out.printf("  %-30s %-18s %-18s%n",
                "Caesar cipher",       "25 values",     "Brute-force + IC");
        System.out.printf("  %-30s %-18s %-18s%n",
                "Keyword (slogan) cipher", "26! (like subst.)", "Frequency analysis");
        System.out.println();
    }
}
