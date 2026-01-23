package com.example.book;

import java.util.Arrays;

public class HighArrays {
    private int[] arr;
    private int size;

    public HighArrays(int max) {
        arr = new int[max];
        size = 0;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getValueAt(int index) {
        return arr[index];
    }

    public void insert(int value) {
        arr[size] = value;
        size += 1;
    }

    public int find(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                return arr[i];
            }
        }
        return -1;
    }

    public boolean remove(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                for (int j = i; j < size - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                size -= 1;
                return true;
            }
        }
        return false;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "HighArrays{" + "arr=" + Arrays.toString(arr) + ", size=" + size + '}';
    }

    // 1 2 3 4 5  2 6 7 = 1 2 3 4 5 2 6 7
    public static int[] merge(int[] arrayA, int[] arrayB) {
        int lengthA = arrayA.length;
        int lengthB = arrayB.length;
        int[] mergedArray = new int[lengthA + lengthB];

        for (int i = 0; i < lengthA; i++) {
            mergedArray[i] = arrayA[i];
        }
        for (int i = 0; i < lengthB; i++) {
            mergedArray[lengthA + i] = arrayB[i];
        }

        return mergedArray;
    }

    public int[] mergeBinaryArray(int[] array) {
        int[] mergedArray = new int[size + array.length];

        for (int i = 0; i < size; i++) {
            mergedArray[i] = arr[i];
        }

        for (int i = 0; i < array.length; i++) {
            mergedArray[size + i] = array[i];
        }

        return mergedArray;
    }

    public static int[] mergeUnique(int[] arrayA, int[] arrayB) {
        int lengthA = arrayA.length;
        int lengthB = arrayB.length;
        int[] tempArray = new int[lengthA + lengthB];
        int index = 0;

        for (int num : arrayA) {
            if (!contains(tempArray, index, num)) {
                tempArray[index++] = num;
            }
        }

        for (int num : arrayB) {
            if (!contains(tempArray, index, num)) {
                tempArray[index++] = num;
            }
        }
        int[] mergedArray = new int[index];
        for (int i = 0; i < index; i++) {
            mergedArray[i] = tempArray[i];
        }

        return mergedArray;
    }

    private static boolean contains(int[] array, int length, int value) {
        for (int i = 0; i < length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public void noDups() {
        if (size == 0) {
            return;
        }

        int[] tempArray = new int[size];
        int index = 0;

        for (int i = 0; i < size; i++) {
            boolean isDup = false;
            for (int j = 0; j < index; j++) {
                if (arr[i] == tempArray[j]) {
                    isDup = true;
                    break;
                }
            }
            if (!isDup) {
                tempArray[index++] = arr[i];
            }
        }
        arr = new int[index];
        for (int i = 0; i < index; i++) {
            arr[i] = tempArray[i];
        }
        size = index;
    }

    public void bubble_sort_back() {
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void bubble_sort() {
        boolean swapped;
        for (int i = 0; i < size; i++) {
            swapped = false;
            for (int j = i + 1; j < size; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        for (int i = size - 1; i > 0; i++) {
            swapped = false;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    int temp = arr[j];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public double median() {
        for (int i = 1; i < size; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = temp;
        }

        if (size % 2 == 1) {
            return arr[size / 2];
        } else {
            return (arr[size / 2 - 1] + arr[size / 2]) / 2.0;
        }
    }

    public void sort() {
        int left = 0;
        int right = 0;

        while (right < size) {
            if (arr[right] % 2 == 0) {
                if (left != right) {
                    int temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                }
                left++;
            }
            right++;
        }

        sortSubArray(0, left - 1);
        sortSubArray(left, size - 1);
    }

    private void sortSubArray(int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= start && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}

class Main {
    public static void main(String[] args) {
        HighArrays highArrays = new HighArrays(10);
        for (int i = 0; i < 10; i++) {
            int n = (int) (Math.random() * (10 - 1));
            highArrays.insert(n);
        }

        highArrays.display();
        highArrays.sort();
        highArrays.display();
    }
}

class BinaryArray {
    private int[] arr;
    private int size;

    public BinaryArray(int max) {
        arr = new int[max];
        size = 0;
    }


    public int find(int value) {
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == value) {
                return mid;
            }
            if (arr[mid] < value) {
                left = mid + 1;
            } else right = mid - 1;
        }

        return left;
    }

    public void insert(int value) {
        int j = find(value);
        for (int k = size; k > j; k--) {
            arr[k] = arr[k - 1];
        }
        arr[j] = value;
        size++;
    }

    public boolean remove(int value) {
        int j = find(value);
        if (j == -1) return false;
        for (int i = j; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size -= 1;
        return true;
    }

    @Override
    public String toString() {
        return "BinaryArray{" + "arr=" + Arrays.toString(arr) + ", size=" + size + '}';
    }
}