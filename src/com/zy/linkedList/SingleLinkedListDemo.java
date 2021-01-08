package com.zy.linkedList;

import jdk.nashorn.internal.ir.IfNode;
import sun.font.TrueTypeFont;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode0 = new HeroNode(0, "张三");
        HeroNode heroNode1 = new HeroNode(1, "李四");
        HeroNode heroNode2 = new HeroNode(2, "王五");
        HeroNode heroNode3 = new HeroNode(3, "赵六");
        HeroNode heroNode4 = new HeroNode(4, "田七");


        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(heroNode0);
//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode4);
//
//        singleLinkedList.list();
//        System.out.println("***************get***************");
//        System.out.println(singleLinkedList.get(3));
//        System.out.println(singleLinkedList.get(1));
//        System.out.println(singleLinkedList.get(4));
//        System.out.println(singleLinkedList.get(5));
//
//        singleLinkedList.list();
//        System.out.println("***************del***************");
//        System.out.println(singleLinkedList.del(3));
//        System.out.println("***************list***************");
//        singleLinkedList.list();

        singleLinkedList.addBuOrder(heroNode3);
        singleLinkedList.addBuOrder(heroNode4);
        singleLinkedList.addBuOrder(heroNode2);
        singleLinkedList.addBuOrder(heroNode1);
        singleLinkedList.addBuOrder(heroNode0);
        singleLinkedList.list();
        System.out.println("***************update***************");
        HeroNode updateHeroNode = new HeroNode(2, "田七1");
        singleLinkedList.update(updateHeroNode);
        singleLinkedList.del(2);
        singleLinkedList.list();
        System.out.println("size:\t" + singleLinkedList.getSize());
        System.out.println("***************reverse***************");
        singleLinkedList.reverse();
        singleLinkedList.list();
    }
}

//定义SingleLinkedList 管理英雄
class SingleLinkedList {
    //初始化一个头节点，不存放具体数据
    private HeroNode head = new HeroNode(0, "");
    private int size;

    public int getSize() {
        return size;
    }

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点(不考虑编号顺序)
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next 指向新的节点
     *
     * @param node
     */
    public void add(HeroNode node) {
        size++;
        //因为head不能动，因此需要一个辅助变量遍历
        HeroNode temp = head;

        while (true) {
            //如果找到了链表的最后就跳出循环
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }

        //最后一个节点添加新数据
        temp.next = node;
    }

    /**
     * 第二种方式添加节点，根据排名插入英雄到指定位置（如果此位置有数据则添加失败）
     * 因为是单链表，找的temp是指定位置的上一个节点
     */
    public void addBuOrder(HeroNode node) {
        size++;
        //因为head不能动，因此需要一个辅助变量遍历
        HeroNode temp = head;
        //标识添加的编号是否存在
        boolean flag = false;

        while (true) {
            //如果找到了链表的最后就跳出循环
            if (temp.next == null) {
                break;
                //如果当前遍历的节点的下一个节点>我们要插入的节点，那么此节点就应该位于temp 和 temp.next之间
            } else if (temp.next.no > node.no) {
                break;
                //如果节点冲突（编号存在）
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            } else {
                temp = temp.next;
            }
        }

        if (flag) {
            throw new RuntimeException("节点存在，无法添加!");
        } else {
            node.next = temp.next;
            temp.next = node;
        }

    }


    /**
     * 获取指定节点的node
     *
     * @param index
     * @return
     */
    public HeroNode get(int index) {
        //判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空，下标越界！");
        }
        HeroNode temp = head.next;

        int num = 0;
        while (true) {
            if (num == index) {
                return temp;
            }
            num++;
            temp = temp.next;
        }
    }

    /**
     * 根据编号修改 no必须存在，否在提示错误
     */
    public void update(HeroNode node) {
        //判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空，下标越界！");
        }
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//找到了最后
            } else if (temp.no == node.no) {//找到了此节点
                flag = true;
                break;
            } else {
                temp = temp.next;
            }
        }

        if (flag) {
            temp.name = node.name;
        } else {
            throw new RuntimeException("无此节点!");
        }

    }

    /**
     * 删除指定下标的node
     *
     * @param index
     * @return
     */
    public HeroNode del(int index) {
        size--;
        //判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空，下标越界！");
        }
        HeroNode temp = head;

        HeroNode delNode;

        int num = 0;
        while (true) {
            if (num == index) {
                //获取需要删除的节点
                delNode = temp.next;

                //让需要使出的节点的上一个节点的next指向它的下一个节点
                temp.next = temp.next.next;

                return delNode;
            }
            num++;
            temp = temp.next;
        }
    }

    /**
     * 反转
     */
    public void reverse() {
        //判断链表是否为空 或者只有一个节点
        if (head.next == null || head.next.next == null) {
            throw new RuntimeException("链表为空!");
        }

        ///定义一个临时节点用于遍历
        HeroNode temp = head.next;
        //定义一个临时节点充当当前节点的下一个节点
        HeroNode next = null;
        //定义一个head用来挂载反转数据，遍历之前的链表，每遍历一个节点，将其取出，放在reverseHead的next位置
        HeroNode reverseHead = new HeroNode(0, "");
        while (temp != null) {
            next = temp.next;//先暂时保存当前节点的下一个节点，后面需要使用
            /*
            reverseHead->null   temp->null   reverseHead->temp->null
             */
            temp.next = reverseHead.next;//temp.next指向新链表的最前端
            reverseHead.next = temp;
            temp = next;
        }


        head.next = reverseHead.next;

    }


    /**
     * 显示整个链表（遍历）
     */
    public void list() {
        //判断链表为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        //因为head不能动，因此需要一个辅助变量遍历
        HeroNode temp = head.next;
        while (true) {
            //输出节点的信息
            System.out.println(temp);
            if (temp.next == null) {
                break;
            }
            //后移
            temp = temp.next;
        }
    }

}


//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}