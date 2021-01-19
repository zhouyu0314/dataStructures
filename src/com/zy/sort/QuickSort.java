package com.zy.sort;

public class QuickSort {
    public static void main(String[] args) {
        //{-9, 78,77, -100, 21,23, -567, 70};

        //{-567, 78,77, -100, 21,23, -9, 70};
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quick(arr,0, arr.length - 1,(arr.length - 1) / 2);
    }

    public static void quick(int[] arr,int left,int right,int center) {


        //循环左边部分
        while (arr[left] > arr[center] && left<center) {
            left++;
        }


        //循环右边部分
        while (arr[right] < arr[center] && right>center) {
            right--;
        }

        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;


    }
}
