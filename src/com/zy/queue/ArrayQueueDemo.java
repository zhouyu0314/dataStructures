package com.zy.queue;


/**
 * 数组实现队列  ------ 存在问题 一次性 需要环形队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args)throws Exception {
        ArrayQueue<String> arrayQueue = new ArrayQueue<>(3);

        arrayQueue.addQueue("A");
        arrayQueue.addQueue("B");
        arrayQueue.addQueue("C");
        arrayQueue.showQueue();//A	B	C
        System.out.println(arrayQueue.getQueue());//A
        System.out.println(arrayQueue.getQueue());//B
        System.out.println(arrayQueue.getQueue());//C
        //此时队列时空的 但是不能添加
        arrayQueue.addQueue("A");//Exception in thread "main" java.lang.Exception: 队列满，不能加入数据！

    }

}


class ArrayQueue<E> {
    private int maxSize;//队列(数组)的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private E[] arr;//存放数据，模拟队列


    /**
     * 创建队列
     *
     * @param maxSize
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = (E[]) new Object[maxSize];
        this.front = -1;//指向队列头部的前一个位置
        this.rear = -1;//指向队列尾,指向队列尾的数据(即就是队列最后一个数据)
    }

    /**
     * 判断队列是否满
     */
    public boolean isFull() {
        return rear == maxSize - 1;
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
    public void addQueue(E e) throws Exception {
        //判断队列是否满
        if (this.isFull()) {
            throw new Exception("队列满，不能加入数据！");
        }
        rear++;//让rear后移
        arr[rear] = e;
    }

    /**
     * 数据出队列
     */
    public E getQueue() throws Exception {
        //判断队列是否为空
        if (this.isEmpty()) {
            throw new Exception("队列空，不能获取数据！");
        }

        front++;//front后移

        return arr[front];
    }

    /**
     * 显示队列所有数据
     */
    public void showQueue() {

        if (this.isEmpty()) {
            System.out.println("[]");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");

        }
        System.out.println();
    }


    /**
     * 显示队列的头数据,注意不是取出数据
     */
    public E headQueue()throws Exception{
        //判断队列是否为空
        if (this.isFull()) {
            throw new Exception("队列空，不能获取数据！");
        }
        return arr[0];
    }
}
