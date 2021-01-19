package com.zy.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，
 * 无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，
 * 将它插入到有序表中的适当位置，使之成为新的有序表。
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {10, 3, 5, 1};
//        insert(arr);
//        System.out.println(Arrays.toString(arr));

        // 创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        insert(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);

    }

    public static void insert(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            //取出当前元素保存到临时变量
            insertVal = arr[i];
            //得到当前index的前一个index
            insertIndex = i - 1;
            //insertIndex >= 0 可以保证数组不越界
            // insertVal < arr[insertIndex] 保证当前数组的元素小于前一个元素
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {//改成 > 倒序排
                // {10, 10, 5, 1};
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //insertIndex + 1 = i 说明 没有走while循环 没有发生交换 当前的元素比之前的元素大
            if (insertIndex + 1 != i) {
                // {3, 10, 5, 1}; 经过--之后tempIndex已经来到的-1
                arr[insertIndex + 1] = insertVal;
            }
        }

    }
}
