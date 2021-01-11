package com.zy.stack;


public class StackDemo {

    public static void main(String[] args) {

        //String expression = "3+6*2+5+3*2-1-9/3";  //3+12+5+6-1-3 = 22
        String expression = "100+50*2-30-70";  //3+12+5+6-1-3 = 22
        int index = 0;
        boolean flag = false;
        boolean numFlag = false;
        StackDemo stackDemo = new StackDemo();
        ArrayStack<Character> operStack = new ArrayStack<>(10);
        ArrayStack<Integer> numStack = new ArrayStack<>(10);


        for (int i = 0; i < expression.length(); i++) {
            char res = expression.substring(index, index + 1).charAt(0);
            index++;
            //如果是操作符
            if (stackDemo.isOper(res)) {
                //则判断操作符栈里是否有数据
                if (!operStack.isEmpty()) {
                    if (flag) {
                        //如果当前的flag为true，则取出两个numStack的值和一个operStack的值进行计算然后将结果入numStack
                        numStack.push(stackDemo.cal(numStack.pop(), numStack.pop(), operStack.pop()));
                        //并将flag改成false
                        flag = false;
                    }

                    //则需要比较此次截取的字符和栈顶的字符哪个优先级别高
                    if (stackDemo.compare(res, operStack.peek())) {//为真 则是当前的oper比栈顶的优先级别高

                        //修改标记位
                        flag = true;

                    }
                }
                //如果当前的操作符栈里没有数据
                operStack.push(res);
                numFlag = false;

            } else {

                //判断当前是否是多位数
                if (numFlag) {
                    //如果为真 则是多位数
                    Integer pop = numStack.pop();
                    numStack.push(Integer.valueOf("" + pop + res));

                } else {
                    //如果不是操作符,入numStack
                    numStack.push(res - 48);

                    numFlag = true;
                }


            }

        }
        //当遍历完成之后
        while (true) {
            if (operStack.getTop() < 0) {
                break;
            }
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            char ch = operStack.pop();
            numStack.push(stackDemo.cal(num1, num2, ch, operStack));
        }

        System.out.println("结果:\t" + numStack.pop());
    }

    public boolean isOper(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public boolean compare(char v1, char v2) {

        int num1 = this.defineLevel(v1);
        int num2 = this.defineLevel(v2);

        return num1 > num2;

    }

    public int defineLevel(char v) {
        switch (v) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            default:
                break;

        }
        return 0;
    }

    public int cal(int num1, int num2, char ch) {
        switch (ch) {
            case '+':
                return num2 + num1;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
            default:
                break;

        }
        return 0;
    }

    public int cal(int num1, int num2, char ch, ArrayStack<Character> arrayStack) {


        if (arrayStack.isEmpty()) {
            return this.cal(num1, num2, ch);
        }
        char v = arrayStack.peek();

        switch (ch) {
            case '+':
                return num2 + num1;
            case '-':
                if (v == '-') {
                    return num2 + num1;
                }
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                if (v == '/') {
                    return num2 * num1;
                }
                return num2 / num1;
            default:
                break;
        }
        return 0;
    }


}

/**
 * 通过数组实现栈
 */
class ArrayStack<E> {
    private int maxSize;
    private int top;
    private E[] arr;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = (E[]) new Object[maxSize];
        top = -1;
    }

    /**
     * 当栈顶指针等于数组的最大长度-1时则满
     *
     * @return
     */
    public boolean isFull() {
        return top >= maxSize;
    }

    /**
     * 当top=0时 空
     *
     * @return
     */
    public boolean isEmpty() {
        return top < 0;
    }

    public void push(E v) {
        //判断是否满
        if (this.isFull()) {
            throw new RuntimeException("数组满！");
        }
        top++;
        arr[top] = v;


    }

    public E pop() {
        //判断是否空
        if (this.isEmpty()) {
            throw new RuntimeException("数组空！");
        }

        E v = arr[top];

        top--;
        return v;
    }


    public E peek() {
        return arr[top];
    }

    public int getTop() {
        return top;
    }

    public void ergodic() {
        if (this.isEmpty()) {
            System.out.println("[]");
            return;
        }
        for (int i = 0; i <= top; i++) {
            System.out.print(arr[i] + "\t");
        }

        System.out.println();


    }
}
