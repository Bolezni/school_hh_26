package com.example.Algorithms_tbank.contest_1;

import java.util.Arrays;

public class Task_9 {
    public static void main(String[] args) {
        int[] arr = {1,3,4,2};
        System.out.println(Arrays.toString(bubbleSort(arr)));
    }


    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        int count = 0;
        for (int i = 0; i < n + 1; ++i) {
            int r = n - 1;
            count = 0;
            while(r >= 0 && arr[r] != 0){
                r--;
                count++;
            }

            if(i == n){
                break;
            }
            res[i] = count;
        }
        return res;
    }
}
