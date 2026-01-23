package com.example.book;

import java.util.Arrays;

public class NonTrivialSorting {
    public static void main(String[] args) {
        long[] array = new long[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = Math.round(Math.random() * 100);
        }
        System.out.println(Arrays.toString(array));

        long start = System.currentTimeMillis();
        //quickSort(array,0,array.length-1);
        sort2(array);
        System.out.println(Arrays.toString(array));

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static int partition(long[] array, int left, int right) {
        long pivot = array[right]; //опорный элемент
        int i = left - 1; //индекс меньшего элемента

        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) { //если текущий элемент меньше опорного
                i++;    //увеличиваем индекс меньшего элемента
                long temp = array[i];   //Меняем местами
                array[i] = array[j];
                array[j] = temp;
            }
        }
        long temp = array[i + 1]; //Меняем местами опорный элемент следующим последним меньшим элементом
        array[i + 1] = array[right];
        array[right] = temp;

        return i + 1; //Индекс опорного элемента
    }

    public static void quickSort(long[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);

            quickSort(array, left, pivot - 1);
            quickSort(array, pivot + 1, right);
        }
    }

    public static void sort(long[] array) {
        for (int i = 0; i < array.length; i++) {
            long min = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > min) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = min;
        }
    }

    public static void sort2(long[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            long temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }
}