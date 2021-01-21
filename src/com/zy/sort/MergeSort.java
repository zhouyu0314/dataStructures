package com.zy.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        //int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] arr = {4, 5, 7, 8, 1, 2, 3, 6};

        int[] temp = new int[arr.length];

        Merge(arr, 0, 4,7, temp, 0);
        System.out.println(Arrays.toString(temp));
    }

    public static void split(int[] arr,int left,int right,int center){
        int l = left;
        int r = right;
        int c = center;
        while (l == r) {
            r = c-1;
            c = (l+r)/2;
        }

    }


    public static void Merge(int[] arr, int left, int right,int end, int[] temp, int tempIndex) {
        //定义左右指针，和临时数组的指针
        int l = left;
        int r = right;
        int tIndex = tempIndex;


        while (l <= right - 1 && r <= end) {
            //如果左边的大，将对应的值加入临时数组，同时左边的指针移动一位
            while (arr[l] <= arr[r]) {
                temp[tIndex] = arr[l];

                l++;
                tIndex++;

                //说明左侧的数组已经扫描完毕
                if (l > right - 1) {
                    while (r < end) {
                        temp[tIndex] = arr[r];
                        r++;
                        tIndex++;
                    }
                    return;
                }
            }

            //如果右边的大，将对应的值加入临时数组，同时右边的指针移动一位
            while (arr[r] <= arr[l]) {
                temp[tIndex] = arr[r];

                r++;
                tIndex++;

                //说明左侧的数组已经扫描完毕
                if (r > end) {
                    while (l < right) {
                        temp[tIndex] = arr[l];
                        l++;
                        tIndex++;
                    }
                    return;
                }
            }
        }


    }


}
