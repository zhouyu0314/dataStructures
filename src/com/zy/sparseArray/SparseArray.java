package com.zy.sparseArray;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * 稀疏数组
 * 二维数组 转 稀疏数组的思路
 * 1. 遍历  原始的二维数组，得到有效数据的个数 sum
 * 2. 根据sum 就可以创建 稀疏数组 sparseArr   int[sum + 1] [3]
 * 3. 将二维数组的有效数据数据存入到 稀疏数组
 * <p>
 * 稀疏数组转原始的二维数组的思路
 * <p>
 * 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr12 = int [11][11]
 * 2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
 */
public class SparseArray {
    public static void main(String[] args) {
        /*
        1.先创建一个原始的二维数组 11*11 0：表示没有棋子 1：表示黑子 2：表示蓝子
         */
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二位数组
        System.out.println("*****************原始的二维数组*****************");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        /*
        2.将二维数组转稀疏数组
         */
        //2.1.先便利二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //System.out.println("sum=" + sum); sum=2
        //2.2.创建对应的稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        //2.3.给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //2.4.便利原始数组，将非0值存入稀疏数组
        int count = 0;//count用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }
        //输入稀疏数组
        System.out.println("*****************得到的稀疏数组*****************");
        for (int[] row : sparseArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        
        
        
        /*
        3.稀疏数组还原成二维数组
         */
        int chessArr2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];//sparseArray[0][0] 第0行第0列
        //3.1.从稀疏数组的第二行开始遍历
        for (int i = 1; i < sparseArray.length; i++) {
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        //恢复后的二维数组
        System.out.println("*****************恢复后的二维数组*****************");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


        /*
        4.稀疏数组持久化
         */

        //转json
        String jsonStr = JSON.toJSONString(sparseArray);
        System.out.println(jsonStr);

        List<int[]> ints = JSONObject.parseArray(jsonStr, int[].class);
        for (int[] anInt : ints) {

            System.out.println(Arrays.toString(anInt) );
        }


    }
}
