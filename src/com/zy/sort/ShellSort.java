package com.zy.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        //int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        // 创建要给80000个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        shell(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);


    }

    public static void shell(int[] arr) {
        for (int i = arr.length / 2; i > 0; i /= 2) {
            for (int j = 0; j < i; j++) {
                //定义有序表中的第一个元素
                int value = 0;
                //定义无序表中的元素
                int valueIndex = 0;
                for (int k = j; k < arr.length; k += i) {
                    value = arr[k];
                    valueIndex = k - i;
                    //valueIndex >=0 保证数组不会下标越界
                    //value < arr[valueIndex] 保证此元素小于无序表中的元素，满足则要交换
                    while (valueIndex >= 0 && value < arr[valueIndex]) {
                        arr[valueIndex + i] = arr[valueIndex];
                        valueIndex -= i;
                    }
                    if (valueIndex + i != k) {
                        arr[valueIndex + i] = value;
                    }
                }

            }

            //System.out.println(Arrays.toString(arr));

        }

//        第一轮 分成10/2 5组 [8,3] [9,5] [1,4] [7,6] [2,0]
//        for (int j = 0; j < arr.length / 2; j++) {
//            //定义有序表中的第一个元素
//            int value = 0;
//            //定义无序表中的元素
//            int valueIndex = 0;
//            for (int k = j; k < arr.length; k += 5) {
//                value = arr[k];
//                valueIndex = k - 5;
//                //valueIndex >=0 保证数组不会下标越界
//                //value < arr[valueIndex] 保证此元素小于无序表中的元素，满足则要交换
//                while (valueIndex >= 0 && value < arr[valueIndex]) {
//                    arr[valueIndex + 5] = arr[valueIndex];
//                    valueIndex -= 5;
//                }
//                if (valueIndex + 5 != k) {
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
//        for (int j = 0; j < arr.length / 2 / 2; j++) {
//            //定义有序表中的第一个元素
//            int value = 0;
//            //定义无序表中的元素
//            int valueIndex = 0;
//            for (int k = j; k < arr.length; k += 2) {
//                value = arr[k];
//                valueIndex = k - 2;
//                //valueIndex >=0 保证数组不会下标越界
//                //value < arr[valueIndex] 保证此元素小于无序表中的元素，满足则要交换
//                while (valueIndex >= 0 && value < arr[valueIndex]) {
//                    arr[valueIndex + 2] = arr[valueIndex];
//                    valueIndex -= 2;
//                }
//                if (valueIndex + 2 != k) {
//                    arr[valueIndex + 2] = value;
//                }
//            }
//        }
//        System.out.println("第二轮" + Arrays.toString(arr));//[0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
//
//        //第三轮
//        for (int j = 0; j < arr.length / 2 / 2 / 2; j++) {
//            //定义有序表中的第一个元素
//            int value = 0;
//            //定义无序表中的元素
//            int valueIndex = 0;
//            for (int k = j; k < arr.length; k += 1) {
//                value = arr[k];
//                valueIndex = k - 1;
//                //valueIndex >=0 保证数组不会下标越界
//                //value < arr[valueIndex] 保证此元素小于无序表中的元素，满足则要交换
//                while (valueIndex >= 0 && value < arr[valueIndex]) {
//                    arr[valueIndex + 1] = arr[valueIndex];
//                    valueIndex -= 1;
//                }
//                if (valueIndex + 1 != k) {
//                    arr[valueIndex + 1] = value;
//                }
//            }
//        }
//
//        System.out.println("第三轮" + Arrays.toString(arr));//[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]


    }
}
