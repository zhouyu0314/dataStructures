package com.zy.sort;

import java.util.Arrays;

/**
 * 把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，
 * 无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，
 * 将它插入到有序表中的适当位置，使之成为新的有序表。
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {1, 10, -2, 5, -4, 30, -10, 3, 2};
        insert(arr);
        Arrays.toString(arr);


    }

    public static void insert(int[] arr) {
        int k = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            k = arr[i+1];

            while (i >= 0) {
                if (k < arr[i]) {
                    arr[i+1] = arr[i];
                }else{
                    arr[i+1] = k;
                }
            }



        }

    }
}
