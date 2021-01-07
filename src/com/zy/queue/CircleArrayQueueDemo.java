package com.zy.queue;


import java.util.Scanner;

/**
 * 数组实现环形队列
 * <p>
 * 1.  front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
 * front 的初始值 = 0
 * 2.  rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
 * rear 的初始值 = 0
 * 3. 当队列满时，条件是  (rear  + 1) % maxSize == front 【满】
 * 4. 对队列为空的条件， rear == front 空
 * 5. 当我们这样分析， 队列中有效的数据的个数   (rear + maxSize - front) % maxSize   // rear = 1 front = 0
 * 6. 我们就可以在原来的队列上修改得到，一个环形队列
 * |🟡|🟡|🟡|  | f=0 r=3
 *
 * |  |🟡|🟡|  | f=1 r=3
 *
 * |  |🟡|🟡|🟡| f=1 r=4
 *
 * |  |  |🟡|🟡| f=2 r=4
 *
 * |🟡|  |🟡|🟡| f=2 r=1
 *
 * |🟡|  |  |🟡| f=3 r=1
 *
 * |🟡|🟡|  |🟡| f=3 r=2
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) throws RuntimeException {
        //测试一把
        System.out.println("测试数组模拟环形队列的案例~~~");

        // 创建一个环形队列
        CircleArrayQueue<Integer> queue = new CircleArrayQueue(4); //说明设置4, 其队列的有效数据最大是3

        queue.addQueue(0);
        queue.addQueue(1);
        queue.addQueue(2);
        queue.getQueue();
        queue.addQueue(3);
        queue.getQueue();
        queue.addQueue(4);
        queue.getQueue();
        queue.addQueue(5);
        queue.getQueue();
        queue.addQueue(6);



//        char key = ' '; // 接收用户输入
//        Scanner scanner = new Scanner(System.in);//
//        boolean loop = true;
//        // 输出一个菜单
//        while (loop) {
//            System.out.println("s(show): 显示队列");
//            System.out.println("e(exit): 退出程序");
//            System.out.println("a(add): 添加数据到队列");
//            System.out.println("g(get): 从队列取出数据");
//            System.out.println("h(head): 查看队列头的数据");
//            key = scanner.next().charAt(0);// 接收一个字符
//            switch (key) {
//                case 's':
//                    queue.showQueue();
//                    break;
//                case 'a':
//                    System.out.println("输出一个数");
//                    int value = scanner.nextInt();
//                    queue.addQueue(value);
//                    break;
//                case 'g': // 取出数据
//                    try {
//                        int res = queue.getQueue();
//                        System.out.printf("取出的数据是%d\n", res);
//                    } catch (RuntimeException e) {
//                        // TODO: handle RuntimeException
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case 'h': // 查看队列头的数据
//                    try {
//                        int res = queue.headQueue();
//                        System.out.printf("队列头的数据是%d\n", res);
//                    } catch (RuntimeException e) {
//                        // TODO: handle RuntimeException
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case 'e': // 退出
//                    scanner.close();
//                    loop = false;
//                    break;
//                default:
//                    break;
//            }
//        }
//        System.out.println("程序退出~~");


    }

}


class CircleArrayQueue<E> {
    private int maxSize;//队列(数组)的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private E[] arr;//存放数据，模拟队列


    /**
     * 创建队列
     *
     * @param maxSize
     */
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = (E[]) new Object[maxSize];
    }

    /**
     * 判断队列是否满
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据
     */
    public void addQueue(E e) throws RuntimeException {
        //判断队列是否满
        if (this.isFull()) {
            throw new RuntimeException("队列满，不能加入数据！");
        }
        arr[rear] = e;
        /*
        数组的最后一个元素不存数据，作为约定 所有我们要在0-3 这个四个slot里面存（假设maxSize=5）
        0+1%5->1 1+1%5->2 2+1%5->3 3+1%5->4 当real=4的时候 数组就满了 4+1 % 5 = 0
         */
        rear = (rear + 1) % maxSize;
    }

    /**
     * 数据出队列
     */
    public E getQueue() throws RuntimeException {
        //判断队列是否为空
        if (this.isEmpty()) {
            throw new RuntimeException("队列空，不能获取数据！");
        }

        /*
        front是指向队列的第一个元素
        1.先把front对应的值保存到临时变量
        2.front后移，要考虑如果front当前处于最后一个元素再++会下标越界
         */
        E value = arr[front];

        //因为可用的slot只有0 1 2 3 4（假设maxSize=5）
        front = (front + 1) % maxSize; //0+1%5->1 1+1%5->2 2+1%5->3 3+1%5->4 当front=4的时候 数组就空了 4+1%5->0

        return value;
    }

    /**
     * 显示队列所有数据
     */
    public void showQueue() {

        if (this.isEmpty()) {
            System.out.println("[]");
            return;
        }

        for (int i = front; i < front + this.size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);

        }
        System.out.println();


    }

    /**
     * 求出当前队列的有效个数
     * 此时分两种情况 rear在front右边 即rear > front 结果可直接 rear - front
     * rear 在 front 左边 即 rear + front 如果此时用rear-front 则得到的时空闲slot的个数的相反数 再 +maxSize 则可得到剩余元素个数
     * 为了适配两种情况，即不让两种情况使用一种算法得到一个超过maxSize的数 所有要取模
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }


    /**
     * 显示队列的头数据,注意不是取出数据
     */
    public E headQueue() throws RuntimeException {
        //判断队列是否为空
        if (this.isFull()) {
            throw new RuntimeException("队列空，不能获取数据！");
        }
        return arr[front];
    }
}
