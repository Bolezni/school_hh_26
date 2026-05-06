package com.example.Algorithms_tbank.contest_1;

import java.util.Arrays;

public class TaskB {


    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        System.out.println(nearby(arr, 2));
    }

    public static int nearby(int[] arr, int x) {
        int n = arr.length;

        if(x <= arr[0]) return arr[0];

        if(x >= arr[n-1]) return arr[n-1];

        int left = 0, right = n-1;

        while(left < right) {
            int mid = left + (right - left)/2;
            if(arr[mid] < x){
                left = mid + 1;
            }else
                right = mid;

        }


        int candidateRight = arr[left];
        int candidateLeft = arr[left - 1];

        long diffRight = Math.abs(candidateRight - x);
        long diffLeft = Math.abs(candidateLeft - x);

        if(diffRight < diffLeft) {
            return candidateRight;
        }else if(diffLeft < diffRight) {
            return candidateLeft;
        }else{
            return Math.min(candidateRight, candidateLeft);
        }
    }
}
