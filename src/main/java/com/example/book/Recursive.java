package com.example.book;

import java.util.Objects;

public class Recursive {
    public static void main(String[] args) throws InterruptedException {
    }


    private static int triangle(int n) {
        if (n == 1)
            return 1;
        return n + triangle(n - 1);
    }

    private static int factorial(int n) {
        if (n == 1)
            return 1;
        return n * factorial(n - 1);
    }

    private static int find(int[] arr, int left, int right, int target) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return find(arr, left, mid - 1, target);
        } else
            return find(arr, mid + 1, right, target);
    }

}

class AnagramApp {
    static int size;
    static int count = 0;
    static char[] arrChars = new char[100];

    public static void put(String s) {
        size = s.length();
        for (int i = 0; i < size; i++) {
            arrChars[i] = s.charAt(i);
        }
    }

    public static void doAnagram(int newSize) {
        if (newSize == 1)
            return;
        for (int i = 0; i < newSize; i++) {
            doAnagram(newSize - 1);
            if (newSize == 2)
                displayWorld();
            rotate(newSize);
        }
    }

    public static void displayWorld() {
        if (count < 99)
            System.out.print(" ");
        if (count < 9)
            System.out.print(" ");
        System.out.print(++count + " ");
        for (int j = 0; j < size; j++)
            System.out.print(arrChars[j]);
        System.out.print("   ");
        System.out.flush();
        if (count % 6 == 0)
            System.out.println();
    }

    public static void rotate(int newSize) {
        int i;
        int position = size - newSize;
        char temp = arrChars[position];
        for (i = position + 1; i < size; i++) {
            arrChars[i - 1] = arrChars[i];
        }
        arrChars[i - 1] = temp;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        AnagramApp anagramApp = (AnagramApp) obj;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        return Objects.equals(size, anagramApp.size);
    }
}
