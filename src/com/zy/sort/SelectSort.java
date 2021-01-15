package com.zy.sort;

/**
 * 选择排序（select sorting）也是一种简单的排序方法。它的基本思想是：
 * 第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，第二次从arr[1]~arr[n-1]中选取最小值，
 * 与arr[1]交换，第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…，第i次从arr[i-1]~arr[n-1]中选取最小值，
 * 与arr[i-1]交换，…, 第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次，得到一个按排序码从小到大排列的有序序列。
 */
public class SelectSort {
    public static void main(String[] args) {
        //int[] arr = {1, 10, -2, 5, -4, 30,-10,3,2};
        //select2(arr);
        //System.out.println(Arrays.toString(arr));


        //创建要给80000个的随机的数组
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        long start = System.currentTimeMillis();
        select2(arr1);
        long end = System.currentTimeMillis();

        System.out.println(end - start);


    }

    /**
     * 这是初始自己写的 原理类似冒泡，因为频繁发生交换 所以时间长 排序一个80000的数组大概9s
     * @param arr
     */
    public static void select(int[] arr) {
        int k = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1 + i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    k = arr[i];
                    arr[i] = arr[j];
                    arr[j] = k;
                }
            }
        }
    }

    /**
     * 这是老师的 大概1s 交换的次数少
     * @param arr
     */
    public static void select2(int[] arr) {
        int k = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];

            for (int j = 1 + i; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex =j;
                }
            }
            // 将最小值，放在arr[0], 即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }


        }
    }
}

