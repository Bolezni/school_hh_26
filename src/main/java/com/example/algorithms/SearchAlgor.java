package com.example.algorithms;

public class SearchAlgor {
    public static void main(String[] args) {
        int[] arr = {3, 8, 10, 14, 20, 100};
        int target = binarySearch(arr, 3);
        System.out.println(target);
    }

    private static int linear_search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private static int binary_search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int binary_search_recursive(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                return binary_search_recursive(arr, left, mid - 1, target);
            } else if (arr[mid] < target) {
                return binary_search_recursive(arr, mid + 1, right, target);
            }
        }
        return -1;
    }

    //Бинарный поиск по ответу

    private static int binarySearch(int[] stalls, int n) {
        int left = 1; // минимальное расстояние
        int right = stalls[stalls.length - 1] - stalls[0]; // максимальное расстояние
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Среднее значение

            if (canPlaceCows(stalls, n, mid)) {
                answer = mid; // Если можно разместить коров, запоминаем ответ
                left = mid + 1; // Ищем большее расстояние
            } else {
                right = mid - 1; // Ищем меньшее расстояние
            }
        }

        return answer; // Возвращаем максимальное минимальное расстояние
    }

    private static boolean canPlaceCows(int[] stalls, int n, int minDist) {
        int count = 1; // Первая корова помещена в первую стойло
        int lastPosition = stalls[0]; // Позиция последней размещенной коровы

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPosition >= minDist) {
                count++; // Размещаем корову
                lastPosition = stalls[i]; // Обновляем позицию последней коровы
                if (count == n) {
                    return true; // Все коровы размещены
                }
            }
        }
        return false; // Не удалось разместить всех коров
    }

}
