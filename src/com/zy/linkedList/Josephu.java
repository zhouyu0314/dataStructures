package com.zy.linkedList;

/**
 * Josephu  问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
 * 数到m 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，
 * 由此产生一个出队编号的序列。
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
//        circleSingleLinkedList.addBoy(5);
//        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1,2,5);
    }
}


//创建环形的单项链表
class CircleSingleLinkedList {
    //创建一个 first节点，当前没有编号
    private Boy first = new Boy(-1);
    //添加boy节点，构建环形链表

    /**
     * 添加小孩节点，构建环形链表
     *
     * @param nums 要添加的boy节点个数
     */
    public void addBoy(int nums) {
        //check nums
        if (nums < 1) {
            System.out.println("nums值错误！");
            return;
        }
        //辅助变量，帮助构建环形链表
        Boy currentBoy = null;
        for (int i = 1; i <= nums; i++) {
            //根据编号创建boy节点
            Boy boy = new Boy(i);
            //如果是第一个boy
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                currentBoy = first;//让currentBoy指向第一个小孩
            } else {
                currentBoy.setNext(boy);
                boy.setNext(first);
                currentBoy = boy;
            }
        }
    }

    /**
     * 辊距用户输入，计算boy出圈顺序
     * <p>
     * 根据用户的输入，生成一个小孩出圈的顺序
     * n = 5 , 即有5个人
     * k = 1, 从第一个人开始报数
     * m = 2, 数2下
     * <p>
     * 1.  需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点.
     * 补充： 小孩报数前，先让 first 和  helper 移动 k - 1次
     * 2.  当小孩报数时，让first 和 helper 指针同时 的移动  m  - 1 次
     * 3.  这时就可以将first 指向的小孩节点 出圈
     * first = first .next
     * helper.next = first
     * 原来first 指向的节点就没有任何引用，就会被回收
     * <p>
     * 出圈的顺序
     * 2->4->1->5->3
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //check
        if (first == null || startNo < 1 || startNo > nums || nums < 1) {
            System.out.println("参数有误，请重新输入！");
            return;
        }
        this.addBoy(nums);
        //创建辅助指针
        Boy helper = first;
        while (true) {
            //说明helper指向了最后的boy节点
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //boy报数前，先让first和helper移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当boy报数时，让first和helper指针同时的移动countNum-1次，然后出圈，直到只剩下最后一个节点
        while (true) {
            if (helper == first) {//说明只有一个节点
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //boy节点出圈
            System.out.println("boy节点出圈：" + first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后一个节点的no：" + first.getNo());

    }

    /**
     * 显示所有链表
     */
    public void showBoy() {
        //判断是否为空
        if (first == null) {
            System.out.println("链表为空！");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy currentBoy = first;
        while (true) {
            System.out.println("编号：" + currentBoy.getNo());
            //最后一个
            if (currentBoy.getNext() == first) {
                break;
            }
            currentBoy = currentBoy.getNext();
        }
    }
}


//创建一个Boy类，表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//指向下一个节点 默认为null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}