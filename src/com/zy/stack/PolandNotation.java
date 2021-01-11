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
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
//        List<String> list = getList(suffixExpression);
//        System.out.println(list);
//
//        System.out.println(cal(list));


        String infixExpression = "10 + ( ( 22 + 33 )× 4) - 5 ";
        //中缀转后缀
        List<String> list_1 = getList_1(infixExpression);
        System.out.println(list_1);


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
        Stack<Integer> stack = new Stack<Integer>();

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


}
