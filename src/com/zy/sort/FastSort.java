package com.zy.sort;

public class FastSort {

    public static void main(String[] args) {
        int[] arr = {2, 10, 8, 22, 34, 5, 12, 28, 21, 11};
    }

    public static void fast(int[] arr) {
        //得到最后一个数
        int temp = arr[arr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < temp) {
                int temp1 = arr[i];
                int tempIndex = i-1;
                while (tempIndex >= 0 && arr[i] < arr[tempIndex]) {
                    arr[i] = arr[tempIndex];
                    tempIndex--;
                }
                if (tempIndex+1 != i) {

                }

            }
        }
    }
}
