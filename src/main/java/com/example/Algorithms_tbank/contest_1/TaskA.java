package com.example.Algorithms_tbank.contest_1;

public class TaskA {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        int target = 8;
        System.out.println(binarySearch(arr, target));
    }

    public static String binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(array[mid] == target) {
                return "YES";
            }
            else if(array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return "NO";
    }
}
