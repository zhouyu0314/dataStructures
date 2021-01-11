package com.zy.stack;

/**
 * 根据字符串实现计算
 * <p>
 * 使用栈完成表达式的计算 思路
 * 1. 通过一个 index  值（索引），来遍历我们的表达式
 * 2. 如果我们发现是一个数字, 就直接入数栈
 * 3. 如果发现扫描到是一个符号,  就分如下情况
 * 3.1 如果发现当前的符号栈为 空，就直接入栈
 * 3.2 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈， 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
 * 4. 当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.最后在数栈只有一个数字，就是表达式的结果
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "1+2*4+5";
        //创建两个栈 数栈 和 符号栈
        ArrayStack2<Integer> numStack = new ArrayStack2<>(10);
        ArrayStack2<Character> operStack = new ArrayStack2<>(10);
        //定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//每次扫描的结果保存到此
        while (true) {
            //一次得到expression的每个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)) {//如果是元素符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    /*
                      如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符，
                      就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，
                      然后将当前的操作符入符号栈， 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                     */
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();

                        res = numStack.cal(num1, num2, oper);

                        //结果入数栈
                        numStack.push(res);
                    }
                }
                //如果为空直接入栈
                operStack.push(ch);

            } else {//如果是数 则直接入 数栈
                numStack.push(ch - 48);
            }
            //让index+1 并判断是否扫描到expression的最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.最后在数栈只有一个数字，就是表达式的结果
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈只有一个数字
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }

        System.out.println(numStack.pop());

    }
}

//定义栈 将前面的拿过来用 添加新功能

class ArrayStack2<E> {
    private int maxSize;
    private int top;
    private E[] stack;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        stack = (E[]) new Object[maxSize];
    }

    public boolean isFull() {
        if (top == maxSize - 1) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (top == -1) {
            return true;
        }
        return false;
    }

    public void push(E v) {
        if (this.isFull()) {
            throw new RuntimeException("栈满！");
        }
        top++;
        stack[top] = v;
    }

    public E pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("栈空！");
        }
        E v = stack[top];
        top--;
        return v;
    }

    public void ergodic() {
        if (this.isEmpty()) {
            throw new RuntimeException("栈空！");
        }

        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + "\t");

        }
        System.out.println();
    }

    /**
     * 返回运算符的优先级，数字越大优先级越高
     *
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前的表达式只有 + - * /
        }
    }

    /**
     * 查看栈顶数据
     */
    public E peek() {
        return stack[top];
    }


    /**
     * 判断是否是运算符
     */
    public boolean isOper(char v) {
        return v == '+' || v == '-' || v == '*' | v == '/';
    }

    /**
     * 计算方法
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;//用于存放计算结果
        switch (oper) {
            case '+':
                return num1 + num2;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
            default:
                break;
        }
        return res;
    }


}