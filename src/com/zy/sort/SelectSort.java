package com.zy.sort;

import java.nio.channels.SelectionKey;
import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {1,10,-2,5,-4,30};



        select(arr);

        System.out.println(Arrays.toString(arr));

    }

    public static void select(int[] arr){
        int k = 0;
        int temp = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0+i; j < arr.length-1; j++) {

                if (arr[j] > arr[j+1]) {
                    k = j+1;
                }
            }
            temp = arr[k];
            arr[k] = arr[i];

            arr[i] = temp;

        }

    }
}

