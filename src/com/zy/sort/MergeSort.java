package com.zy.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        merge(arr);
    }

    public static void merge(int[] arr) {
        //设置一个临时指针
        int tempIndex = 0;

        for (int k = 2; k < arr.length; k *= 2) {// 2 4
            for (int i = 0; i < arr.length; i += k) {

                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }

        }


        //第一步
        for (int i = 0; i < arr.length; i += 2) {


            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));//[4, 8, 5, 7, 1, 3, 2, 6]
        //第二步
        for (int i = 0; i < arr.length; i += 4) {

            for (int j = i + 1; j < (i + 1) + 2; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

        }
        System.out.println(Arrays.toString(arr));//[4, 5, 7, 8, 1, 2, 3, 6]

        //第三步
        for (int i = 0; i < arr.length; i += 4) {
            for (int j = 0; j < arr.length / 2; j++) {

            }
        }

    }
}
