package com.zy.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};
        bubble(arr);
        Arrays.toString(arr);

    }

    public static void bubble(int[] arr){
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            if (flag) {
                break;
            }
            flag = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int k = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = k;
                    flag = false;
                }
            }

        }

    }
}
