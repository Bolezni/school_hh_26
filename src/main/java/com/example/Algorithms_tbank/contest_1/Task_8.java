package com.example.Algorithms_tbank.contest_1;

public class Task_8 {
    public static void main(String[] args) {

    }


    public static String binaryPalindrome(String s) {
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'A']++;
        }

        StringBuilder leftPart = new StringBuilder();
        Character middleChar = null;

        for (int i = 0; i < 26; i++) {
            char currentChar = (char) (i + 'a');
            int count = counts[i];

            int pairs = count / 2;
            for (int k = 0; k < pairs; k++) {
                leftPart.append(currentChar);
            }

            if (count % 2 != 0) {
                if (middleChar == null) {
                    middleChar = currentChar;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(leftPart);

        if (middleChar != null) {
            result.append(middleChar);
        }

        result.append(leftPart.reverse());
        return result.toString();
    }
}
