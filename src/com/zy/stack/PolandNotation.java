package com.zy.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式
 */
public class PolandNotation {
    public static void main(String[] args) {
        //String suffixExpression = "3 4 + 5 * 6 -";
        //String suffixExpression = "30 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        List<String> list = getList(suffixExpression);
        System.out.println(list);

        //System.out.println(cal(list));


        String infixExpression = "1 + ( ( 2 + 3 )* 4) - 5 ";
        //中缀转后缀
        List<String> list_1 = getList_1(infixExpression);
        System.out.println(list_1);
        List<String> strings = infixToSuffix(list_1);
        System.out.println(strings);//[10, 22, 33, +, 4, *, +, 5, -]
       System.out.println(cal(strings));


    }

    /**
     * 1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2) 从左至右扫描中缀表达式；
     * 3) 遇到操作数时，将其压s2；
     * 4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
     * 1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     * 2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     * 3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
     * 5) 遇到括号时：  (1) 如果是左括号“(”，则直接压入s1 (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6) 重复步骤2至5，直到表达式的最右边
     * 7) 将s1中剩余的运算符依次弹出并压入s2
     * 8)  依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     */
    public static List<String> infixToSuffix(List<String> list) {
        //1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String item : list) {
            //3) 遇到操作数时，将其压s2；
            if (item.matches("\\d+")) {
                s2.add(item);
                //如果是左括号“(”，则直接压入s1
            } else if ("(".equals(item)) {
                s1.push(item);
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
            } else if (")".equals(item)) {
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();//将 "(" 弹出s1  消除小括号
            } else {
                //将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
                while (s1.size() != 0 && getLvl(s1.peek()) >= getLvl(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入s1
                s1.push(item);
            }

        }

        //将s1中剩余的运算符一次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }


        return s2;
    }


    /**
     * 字符串转数组，无要求格式
     *
     * @param str
     * @return
     */
    public static List<String> getList_1(String str) {
        List<String> list = new ArrayList<>();
        //先去空格
        String[] strs = str.trim().split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strs) {
            stringBuilder.append(s);
        }
        String str1 = stringBuilder.toString();
        //定义扫描指针
        int index = 0;
        boolean flag = false;


        //截取每个字符
        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.substring(index, index + 1).charAt(0);
            index++;

            //判断当前字符是不是数字
            if (ch >= 48 && ch <= 57) {
                //如果是数字,还需要判断是不是多位数字
                if (!flag) {
                    //如果是多位数字则要拼接
                    list.add("" + ch);
                } else {
                    list.set(list.size() - 1, list.get(list.size() - 1) + ch);
                }
                flag = true;
            } else {
                //如果是符号
                list.add("" + ch);
                flag = false;
            }
        }
        return list;
    }


    /**
     * 字符串转数组（需要以空格间隔）
     *
     * @param str
     * @return
     */
    public static List<String> getList(String str) {
        String[] strs = str.split(" ");
        ArrayList<String> arrayList = new ArrayList<String>(strs.length);
        Collections.addAll(arrayList, strs);
        return arrayList;
    }

    /**
     * 1)从左至右扫描，将3和4压入堆栈；
     * 2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 3)将5入栈；
     * 4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
     * 5)将6入栈；
     * 6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     *
     * @param list
     * @return
     */
    public static int cal(List<String> list) {
        Stack<Integer> stack = new Stack<>();

        int num1 = 0;
        int num2 = 0;


        for (String ele : list) {
            if (ele.equals("+")) {
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 + num1);

            } else if (ele.equals("-")) {
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 - num1);

            } else if (ele.equals("*")) {
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 * num1);
            } else if (ele.equals("/")) {
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 / num1);
            } else if (ele.matches("\\d+")) {
                stack.push(Integer.valueOf(ele));
            } else {
                throw new RuntimeException("输入有误！");
            }
        }

        return stack.pop();


    }

    public static int getLvl(String s) {
        if ("+".equals(s) || "-".equals(s)) {
            return 1;
        } else if ("*".equals(s) || "/".equals(s)) {
            return 2;
        } else {
            return 0;
        }
    }


}
