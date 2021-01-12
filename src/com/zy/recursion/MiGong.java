package com.zy.recursion;

public class MiGong {

    public static void main(String[] args) {
        //创建地图
        int[][] map = new int[8][7];
        map[3][1] = 1;
        map[3][2] = 1;

    /*
    1表示墙
    上下（0 7）置为1
     */
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //遍历
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


        setWay(map, 1, 1);
        System.out.println("*******************************");
        //遍历
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 使用递归回溯来给小球找路
     * 从i,j 开始找  6,5为找到
     * 当map[i][j] = 0时，表示该点没有走过;
     * 当map[i][j] = 1时表示墙不能走;
     * 当map[i][j] = 2表示可以走;
     * 当map[i][j] = 3表示已经走过但是走不通
     * 制定一个策略 下 -> 右 -> 上 -> 左，如果该点走不通，再回溯
     *
     * @param map 地图
     * @param i   从哪个位置开始找
     * @param j   从哪个位置开始找
     * @return 找到t
     */
    public static boolean setWay(int[][] map, int i, int j) {
        //如果初始的落脚点为0
        if (map[i][j] == 0) {
            map[i][j] = 2;
            //先假设下一步可以走
            if (setWay(map, i + 1, j)) {//下可以走
                return true;
            } else if (setWay(map, i, j + 1)) {//如果是墙 右
                return true;
            } else if (setWay(map, i - 1, j)) {//如果是墙 上
                return true;
            } else if (setWay(map, i, j - 1)) {//如果是墙 左
                return true;
            } else {
                //说明该点是走不通，是死路
                map[i][j] = 3;
                return false;
            }
        } else if (map[6][5] == 2) {
            return true;
        } else {
            return false;
        }
    }
}
