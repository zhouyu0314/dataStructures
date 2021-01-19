package com.zy.sort;

import java.util.Arrays;

/**
 * swap
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shell(arr);
    }

    public static void shell(int[] arr) {
        for (int k = arr.length / 2; k > 0; k /= 2) {
            for (int i = 0; i < k; i++) {
                //定义有序表中的第一个元素
                int value = 0;
                //定义无序表中的元素
                int valueIndex = 0;
                for (int j = i; j < arr.length; j += k) {
                    value = arr[j];
                    valueIndex = j - k;
                    //valueIndex >=0 保证数组不会下标越界
                    //value < arr[valueIndex] 保证此元素小于无序表中的元素，满足则要交换
                    while (valueIndex >= 0 && value < arr[valueIndex]) {
                        arr[valueIndex + k] = arr[valueIndex];
                        valueIndex -= k;
                    }
                    if (valueIndex + k != j) {
                        arr[valueIndex + k] = value;
                    }
                }

            }

            System.out.println(Arrays.toString(arr));

        }

//        第一轮 分成10/2 5组 [8,3] [9,5] [1,4] [7,6] [2,0]
//        for (int i = 0; i < arr.length / 2; i++) {
//            //定义有序表中的第一个元素
//            int value = 0;
//            //定义无序表中的元素
//            int valueIndex = 0;
//            for (int j = i; j < arr.length; j += 5) {
//                value = arr[j];
//                valueIndex = j - 5;
//                //valueIndex >=0 保证数组不会下标越界
//                //value < arr[valueIndex] 保证此元素小于无序表中的元素，满足则要交换
//                while (valueIndex >= 0 && value < arr[valueIndex]) {
//                    arr[valueIndex + 5] = arr[valueIndex];
//                    valueIndex -= 5;
//                }
//                if (valueIndex + 5 != j) {
//                    arr[valueIndex + 5] = value;
//                }
//            }
//
//
//        }
//
//        System.out.println("第一轮" + Arrays.toString(arr));//[3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
//
//        //第二轮 分成 10/2/2 2组 [3,1,0,9,7]  [5,6,8,4,2]
//        for (int i = 0; i < arr.length / 2 / 2; i++) {
//            //定义有序表中的第一个元素
//            int value = 0;
//            //定义无序表中的元素
//            int valueIndex = 0;
//            for (int j = i; j < arr.length; j += 2) {
//                value = arr[j];
//                valueIndex = j - 2;
//                //valueIndex >=0 保证数组不会下标越界
//                //value < arr[valueIndex] 保证此元素小于无序表中的元素，满足则要交换
//                while (valueIndex >= 0 && value < arr[valueIndex]) {
//                    arr[valueIndex + 2] = arr[valueIndex];
//                    valueIndex -= 2;
//                }
//                if (valueIndex + 2 != j) {
//                    arr[valueIndex + 2] = value;
//                }
//            }
//        }
//        System.out.println("第二轮" + Arrays.toString(arr));//[0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
//
//        //第三轮
//        for (int i = 0; i < arr.length / 2 / 2 / 2; i++) {
//            //定义有序表中的第一个元素
//            int value = 0;
//            //定义无序表中的元素
//            int valueIndex = 0;
//            for (int j = i; j < arr.length; j += 1) {
//                value = arr[j];
//                valueIndex = j - 1;
//                //valueIndex >=0 保证数组不会下标越界
//                //value < arr[valueIndex] 保证此元素小于无序表中的元素，满足则要交换
//                while (valueIndex >= 0 && value < arr[valueIndex]) {
//                    arr[valueIndex + 1] = arr[valueIndex];
//                    valueIndex -= 1;
//                }
//                if (valueIndex + 1 != j) {
//                    arr[valueIndex + 1] = value;
//                }
//            }
//        }
//
//        System.out.println("第三轮" + Arrays.toString(arr));//[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]


    }
}
