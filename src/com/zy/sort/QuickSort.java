package com.zy.sort;

public class QuickSort {
    public static void main(String[] args) {
        //{-9, 78,77, -100, 21,23, -567, 70};

        //{-567, 78,77, -100, 21,23, -9, 70};
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quick(arr, 0, arr.length - 1);
    }

    public static void quick(int[] arr, int left, int right) {
        int center = arr[(left + right) / 2];

        int l = left;
        int r = right;

        while (l < r) {
            //找到左边比中间值大的
            while (arr[l] < center) {
                l++;
            }
            //找到右边比中间值小的
            while (arr[r] > center) {
                r--;
            }
            //设置判断条件 防止 左边和右边的指针越界
            if( l >= r) {
                break;
            }

            //{-9, 78, 0, 23, -567, 70}; 此数组第一次会找到 78 和 -567 让其交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值 相等 r--， 前移
            if(arr[l] == center) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if(arr[r] == center) {
                l += 1;
            }

        }

        // 如果 l == r, 必须l++, r--, 否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if(left < r) {
            quick(arr, left, r);
        }
        //向右递归
        if(right > l) {
            quick(arr, l, right);
        }

    }
}
