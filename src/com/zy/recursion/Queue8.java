package com.zy.recursion;

import java.util.Arrays;

/**
 * 1) 第一个皇后先放第一行第一列
 * 2) 第二个皇后放在第二行第一列、然后判断是否OK[即判断是冲突]， 如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 * 3) 继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 4) 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
 * 5) 然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤
 *
 *
 *
 * 把棋盘看成一个数组 arr[8]
 * 初始放置
 */
public class Queue8 {
    //定义一个max 共多少个皇后
    private static int count = 0;
    int max = 8;

    //定义数组arr，保存皇后放置位置的结果

    int[] arr = new int[max];

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("总计:\t" + count);

    }

    private void check(int n) {
        if (n == max) {//n==8 则是放第九个皇后
            count++;
            System.out.println(Arrays.toString(arr));
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n，放到该行的第一列
            arr[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (this.judge(n)) {//不冲突
                //接着放n+1个皇后，开始递归
                this.check(n + 1);
            }
            //如果冲突，就继续执行array[n] = i;即将第n个皇后防止在本行的后移一个位置
        }

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
               所以可以总结出 n-1 == arr[n] - arr[i]的绝对值
             */
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }


}
