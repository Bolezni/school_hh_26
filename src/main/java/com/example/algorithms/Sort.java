package com.example.algorithms;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] arr = new int[100_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 99);
        }
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));

        shellSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));
        System.out.println(end - start);
    }

    private static void shellSort(int[] arr) {
        int inner, outer;
        int temp;
        int h = 1;
        while (h <= arr.length / 3) {
            h = 3 * h + 1;
        }
        while (h > 0) {
            for (outer = h; outer < arr.length; outer++) {
                temp = arr[outer];
                inner = outer;
                while (inner > h - 1 && arr[inner - h] >= temp) {
                    arr[inner] = arr[inner - h];
                    inner -= h;
                }
                arr[inner] = temp;
            }
            h = (h - 1) / 3;
        }
    }

    private static void shellSort2(int[] array){
        int n = array.length;

        // Генерация последовательности Хиббарда
        int gap = 1;
        while(gap < n /2 ){
            gap = gap * 2 + 1;
        }

        while(gap > 0){
            for(int i = gap; i < n; i++){
                int temp = array[i];
                int j;

                for(j = i; j >= gap && array[j - gap] > temp; j-= gap){
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
            gap = (gap - 1) / 2;
        }
    }

    private static void buble(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    private static void inSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int min = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > min) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = min;
        }
    }

    private static void shell(int[] arr) {
        int n = arr.length;
        int gap = 2;

        for (int i = gap; i < n; i++) {
            int temp = arr[i];
            int j;

            for (j = i; j >= gap && arr[j - gap] > temp; j-= gap) {
                arr[j] = arr[j - gap];
            }
            arr[j] = temp;
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);


            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;


        return i + 1;
    }
}
