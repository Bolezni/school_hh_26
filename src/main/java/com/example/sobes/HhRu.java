package com.example.sobes;

import java.util.HashMap;
import java.util.Map;

public class HhRu {

    public static void main(String[] args) {
        char[][] matrix = {
                {'A', 'F', 'R', 'D', 'H'},
                {'O', 'L', 'M', 'O', 'E'},
                {'L', 'M', 'Q', 'L', 'L'}
        };
        String word = "HELLO";

        boolean res = isWordPresentInMatrix(matrix, word);
        System.out.println(res);
    }

    public static boolean isWordPresentInMatrix(char[][] matrix, String word) {
        Map<Character, Integer> map = new HashMap<>();

        for (char[] row : matrix) {
            for (char ch : row) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
        }

        for (char ch : word.toCharArray()) {
            int count = map.getOrDefault(ch, 0);
            if (count <= 0) {
                return false;
            }
            map.put(ch, count - 1);
        }

        return true;
    }
}
