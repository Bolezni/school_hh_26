package com.example.book;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Исходный массив: " + Arrays.toString(array));
        recMergeSort(array);
        System.out.println("Отсортированный массив: " + Arrays.toString(array));
    }

    private static void recMergeSort(int... src) {
        if (src.length == 1) return;

        int mid = src.length / 2;
        int[] left = Arrays.copyOfRange(src, 0, mid);
        int[] right = Arrays.copyOfRange(src, mid, src.length);

        recMergeSort(left);
        recMergeSort(right);

        mergeSort(src, left, right);
    }

    private static void mergeSort(int[] array, int[] left, int[] right){
        int i = 0, j = 0, k =0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j])
                array[k++] = left[i++];
            else
                array[k++] = right[j++];
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }

    }

        private static int[] mergeSort ( int[] arrA, int[] arrB){
            int sizeA = arrA.length;
            int sizeB = arrB.length;
            int[] arrC = new int[sizeA + sizeB];

            int aDex = 0, bDex = 0, cDex = 0;

            while (aDex < sizeA && bDex < sizeB) {
                if (arrA[aDex] < arrB[bDex])
                    arrC[cDex++] = arrA[aDex++];
                else
                    arrC[cDex++] = arrB[bDex++];
            }
            while (aDex < sizeA)
                arrC[cDex++] = arrA[aDex++];
            while (bDex < sizeB)
                arrC[cDex++] = arrB[bDex++];

            return arrC;
        }

        public static void display ( int[] theArray, int size){
            for (int j = 0; j < size; j++)
                System.out.print(theArray[j] + " ");
            System.out.println("");
        }
    }
