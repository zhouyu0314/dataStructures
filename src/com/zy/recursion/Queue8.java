package com.zy.recursion;

import java.util.Arrays;

public class Queue8 {
    //定义一个max 共多少个皇后
    int max = 8;

    //定义数组arr，保存皇后放置位置的结果

    int[] arr = new int[max];

    public static void main(String[] args) {

    }

    /**
     * 检测该皇后和前面摆放的是否冲突
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            /*
            arr[i] == arr[n] 表示判断第n个皇后是否和前面的皇后在同一列
             */

            /*
               Math.abs(n - i) == Math.abs(arr[n] - arr[i]) 目前来说 写成 n - i == Math.abs(arr[n] - arr[i]) 就可以
               假设 arr[0] = 4,则arr[1] = 3 / arr[1] = 5,arr[2] = 2 / arr[2] = 6,arr[3] = 1 / arr[3] = 7,arr[4] = 0 这几个值会导致失败
             */
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }


}
